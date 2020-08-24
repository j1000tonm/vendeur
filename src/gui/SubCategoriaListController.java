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
import model.entities.SubCategoria;
import model.services.SubCategoriaService;

public class SubCategoriaListController implements Initializable {

	private SubCategoriaService service;
	
	@FXML
	private TableView<SubCategoria> tableViewSubCategoria;
	
	@FXML
	private TableColumn<SubCategoria, Long> tableColumnId;
	
	@FXML
	private TableColumn<SubCategoria, String> tableColumnNome;
	
	@FXML
	private Button btNovoSubCategoria;
	
	private ObservableList<SubCategoria> obsList;
	
	@FXML
	private void onBtNovoSubCategoriaAction() {
		System.out.println("onBtNovoSubCategoriaAction");
	}
	
	public void setSubCategoriaService(SubCategoriaService service) {
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
		tableViewSubCategoria.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		List<SubCategoria> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSubCategoria.setItems(obsList);
	}
}
