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
import model.entities.SubSetor;
import model.services.SubSetorService;

public class SubSetorListController implements Initializable {

	private SubSetorService service;
	
	@FXML
	private TableView<SubSetor> tableViewSubSetor;
	
	@FXML
	private TableColumn<SubSetor, Long> tableColumnId;
	
	@FXML
	private TableColumn<SubSetor, String> tableColumnNome;
	
	@FXML
	private Button btNovoSubSetor;
	
	private ObservableList<SubSetor> obsList;
	
	@FXML
	private void onBtNovoSubSetorAction() {
		System.out.println("onBtNovoSubSetorAction");
	}
	
	public void setSubSetorService(SubSetorService service) {
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
		tableViewSubSetor.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		List<SubSetor> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSubSetor.setItems(obsList);
	}
}
