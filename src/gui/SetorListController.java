package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Setor;
import model.services.SetorService;

public class SetorListController implements Initializable {

	private SetorService service;

	@FXML
	private TableView<Setor> tableViewSetor;
	
	@FXML
	private TableColumn<Setor, Long> tableColumnId;
	
	@FXML
	private TableColumn<Setor, String> tableColumnNome;
	
	@FXML
	private Button btNovoSetor;
	
	private ObservableList<Setor> obsList;
	
	@FXML
	private void onBtNovoSetorAction() {
		System.out.println("onBtNovoSetorAction");
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
	}
}
