package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.CategoriaService;
import model.services.MarcaService;
import model.services.SetorService;
import model.services.SubCategoriaService;
import model.services.SubSetorService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemVendedor;
	
	@FXML
	private MenuItem menuItemFornecedor;
	
	@FXML
	private MenuItem menuItemCliente;
	
	@FXML
	private MenuItem menuItemMarca;
	
	@FXML
	private MenuItem menuItemSetor;
	
	@FXML
	private MenuItem menuItemSubSetor;
	
	@FXML
	private MenuItem menuItemCategoria;
	
	@FXML
	private MenuItem menuItemSubCategoria;
	
	@FXML
	private MenuItem menuItemProduto;
	
	@FXML
	private MenuItem menuItemServico;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	@FXML
	public void onMenuItemVendedor() {		
	}
	
	@FXML
	public void onMenuItemFonercedor() {		
	}
	
	@FXML
	public void onMenuItemCliente() {		
	}
	
	@FXML
	public void onMenuItemMarca() {		
		loadView("/gui/MarcaList.fxml", (MarcaListController controller) -> {
			controller.setMarcaService(new MarcaService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemSetor() {		
		loadView("/gui/SetorList.fxml", (SetorListController controller) -> {
			controller.setSetorService(new SetorService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemSubSetor() {		
		loadView("/gui/SubSetorList.fxml", (SubSetorListController controller) -> {
			controller.setSubSetorService(new SubSetorService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemCategoria() {		
		loadView("/gui/CategoriaList.fxml", (CategoriaListController controller) -> {
			controller.setCategoriaService(new CategoriaService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemSubCategoria() {		
		loadView("/gui/SubCategoriaList.fxml", (SubCategoriaListController controller) -> {
			controller.setSubCategoriaService(new SubCategoriaService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemProduto() {		
	}
	
	@FXML
	public void onMenuItemServico() {		
	}
	
	@FXML
	public void onMenuItemAbout() {	
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exce��o", "Erro carregando tela", e.getMessage(), AlertType.ERROR);
		}
	}
}
