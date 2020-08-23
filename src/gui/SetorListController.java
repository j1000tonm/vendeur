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
import model.entities.Setor;

public class SetorListController implements Initializable {

	@FXML
	private TableView<Setor> tableViewSetor;
	
	@FXML
	private TableColumn<Setor, Long> tableColumnId;
	
	@FXML
	private TableColumn<Setor, String> tableColumnNome;
	
	@FXML
	private Button btNovoSetor;
	
	@FXML
	private void onBtNovoSetorAction() {
		System.out.println("onBtNovoSetorAction");
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
}
