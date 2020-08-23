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
import model.entities.Marca;

public class MarcaListController implements Initializable {

	@FXML
	private TableView<Marca> tableViewMarca;
	
	@FXML
	private TableColumn<Marca, Long> tableColumnId;
	
	@FXML
	private TableColumn<Marca, String> tableColumnNome;
	
	@FXML
	private Button btNovoMarca;
	
	@FXML
	private void onBtNovoMarcaAction() {
		System.out.println("onBtNovoMarcaAction");
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
}
