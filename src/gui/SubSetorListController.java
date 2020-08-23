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
import model.entities.SubSetor;

public class SubSetorListController implements Initializable {

	@FXML
	private TableView<SubSetor> tableViewSubSetor;
	
	@FXML
	private TableColumn<SubSetor, Long> tableColumnId;
	
	@FXML
	private TableColumn<SubSetor, String> tableColumnNome;
	
	@FXML
	private Button btNovoSubSetor;
	
	@FXML
	private void onBtNovoSubSetorAction() {
		System.out.println("onBtNovoSubSetorAction");
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
}
