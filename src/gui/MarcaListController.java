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
import model.entities.Marca;
import model.services.MarcaService;

public class MarcaListController implements Initializable {

	private MarcaService service;
	
	@FXML
	private TableView<Marca> tableViewMarca;
	
	@FXML
	private TableColumn<Marca, Long> tableColumnId;
	
	@FXML
	private TableColumn<Marca, String> tableColumnNome;
	
	@FXML
	private Button btNovoMarca;
	
	private ObservableList<Marca> obsList;
	
	@FXML
	private void onBtNovoMarcaAction() {
		System.out.println("onBtNovoMarcaAction");
	}
	
	public void setMarcaService(MarcaService service) {
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
		tableViewMarca.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Servi�o est� vazio");
		}
		List<Marca> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewMarca.setItems(obsList);
	}
}
