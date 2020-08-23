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
import model.entities.Categoria;

public class CategoriaListController implements Initializable {

	@FXML
	private TableView<Categoria> tableViewCategoria;
	
	@FXML
	private TableColumn<Categoria, Long> tableColumnId;
	
	@FXML
	private TableColumn<Categoria, String> tableColumnNome;
	
	@FXML
	private Button btNovoCategoria;
	
	@FXML
	private void onBtNovoCategoriaAction() {
		System.out.println("onBtNovoCategoriaAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		Stage  stage = (Stage) Main.getMainScene().getWindow();
		tableViewCategoria.prefHeightProperty().bind(stage.heightProperty());
	}
}
