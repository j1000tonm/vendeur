package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.SubCategoria;

public class SubCategoriaListController implements Initializable {

	@FXML
	private TableView<SubCategoria> tableViewSubCategoria;
	
	@FXML
	private TableColumn<SubCategoria, Long> tableColumnId;
	
	@FXML
	private TableColumn<SubCategoria, String> tableColumnNome;
	
	@FXML
	private Button btNovoSubCategoria;
	
	@FXML
	private void onBtNovoSubCategoriaAction() {
		System.out.println("onBtNovoSubCategoriaAction");
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
}
