package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Setor;
import model.services.SetorService;

public class SetorListController implements Initializable, DataChangeListener {

	private SetorService service;

	@FXML
	private TableView<Setor> tableViewSetor;
	
	@FXML
	private TableColumn<Setor, Long> tableColumnId;
	
	@FXML
	private TableColumn<Setor, String> tableColumnNome;
	
	@FXML
	private TableColumn<Setor, Setor> tableColumnEDIT;
	
	@FXML
	private TableColumn<Setor, Setor> tableColumnREMOVE;
	
	@FXML
	private Button btNovoSetor;
	
	private ObservableList<Setor> obsList;
	
	@FXML
	private void onBtNovoSetorAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Setor obj = new Setor();
		createDialogForm(obj, "/gui/SetorForm.fxml", parentStage);
	}
	
	public void setSetorService(SetorService service) {
		this.service = service;
	}
	@Override

	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		Stage  stage = (Stage) Main.getMainScene().getWindow();
		tableViewSetor.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		List<Setor> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSetor.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}
	
	private void createDialogForm(Setor obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			SetorFormController controller = loader.getController();
			controller.setSetor(obj);
			controller.setSetorService(new SetorService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre dados setor");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO exceção", "Erro carregando tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}
	
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Setor, Setor>() {
			private final Button button = new Button("edit");
			
			@Override
			protected void updateItem(Setor obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if (obj == null) {
						setGraphic(null);
						return;
				}
				
				setGraphic(button);
				button.setOnAction(
				event -> createDialogForm(
						obj, "/gui/SetorForm.fxml",Utils.currentStage(event)));
						
			}
		});
	}
	
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Setor, Setor>() {
			private final Button button = new Button("remove");
			
			@Override
			protected void updateItem(Setor obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if (obj == null) {
					setGraphic(null);
					return;
				}
				
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Setor obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Você tem certeza que quer deletar?");
		
		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Serviço está nulo");
			}
			try {
				service.remove(obj);
				updateTableView();				
			}
			catch (DbIntegrityException e) {
				Alerts.showAlert("Erro removendo objeto", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}
