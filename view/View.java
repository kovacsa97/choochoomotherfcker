package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import visuals.DynamicVisual;
import visuals.StaticVisual;

/**
 * Megjelenitest megvalosito osztaly
 */
public class View {
	
	Controller myController;
	
	@FXML
	TreeView<String> tvElements;
	
	@FXML
	Canvas boardCanvas;
	
	@FXML 
	AnchorPane trainControlPane;
	@FXML
	Label lDrivingForceValue;
	
	@FXML 
	AnchorPane switchControlPane;
	
	@FXML 
	AnchorPane toControlPane;
	
	/**
	 * az aktiv vezerles tipusat reprezentalja
	 */
	public enum ControlType {
		Train,
		TunnelOpp,
		Switch,
		None
	};
	
	/**
	 * kezeli a + gombot
	 */
	public void increaseDrivingForce() {
		System.out.println("Driving force increased");
	}
	
	/**
	 * kezeli a - gombot
	 */
	public void decreaseDrivingForce() {
		System.out.println("Driving force decreased");
	}
	
	/**
	 * kezeli a valto valtasat
	 */
	public void changeSwitch() {
		System.out.println("Switch changed");
	}
			
	/**
	 * beallitja az aktiv vezerles tipusat
	 * @param ct az aktiv vezerles tipusa
	 */
	public void setControlType(ControlType ct) {
		switch (ct) {
		case Train:
			switchControlPane.setVisible(false);
			toControlPane.setVisible(false);
			trainControlPane.setVisible(true);
			break;
		case TunnelOpp:
			switchControlPane.setVisible(false);
			toControlPane.setVisible(true);
			trainControlPane.setVisible(false);
			break;
		case Switch:
			switchControlPane.setVisible(true);
			toControlPane.setVisible(false);
			trainControlPane.setVisible(false);
			break;
		case None:
			switchControlPane.setVisible(false);
			toControlPane.setVisible(false);
			trainControlPane.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * Palya betoltesenel egyszer inicializalando elemek listaja
	 */
	private ArrayList<StaticVisual> staticVisuals;
	
	/**
	 * kezeli a kilepes menuelemet
	 */
	public void exitHandler() {
		Platform.exit();
		System.exit(0);
	}
	
	/**
	 * kezeli a palyamegnyitas menuelemet
	 */
	public void openHandler() {
		List<String> choices = new ArrayList<>();
		

		ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
		dialog.setTitle("Map selection");
		dialog.setHeaderText("Select from the available maps");
		dialog.setContentText("Selected map:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Your choice: " + result.get());
		}
	}
	
	public View(){
	}
	
	/**
	 * beallitja a controllert
	 * @param c a controller objektum
	 */
	public void setController(Controller c) {
		myController=c;
	}
	
	/**
	 * Palya betoltese a megfelelo statikus elemek atadasaval
	 * @param list a palya statikus elemeinek visualjai
	 */
	public void initBoard(ArrayList<StaticVisual> list){
		staticVisuals=list;
		for (StaticVisual staticVisual : list) {
			staticVisual.draw(boardCanvas.getGraphicsContext2D());
		}
		
		tvElements.getSelectionModel()
        .selectedItemProperty()
        .addListener((observable, oldValue, newValue) ->
        	myController.selectTreeItem(newValue));
		
		setControlType(ControlType.None);
	}
	
	/**
	 * Palyanezet frissitese a megfelelo dinamikus elemek atadasaval
	 * @param list a palya dinamikus elemeinek visualjai
	 */
	public void update(ArrayList<DynamicVisual> list){
		GraphicsContext boardContext=boardCanvas.getGraphicsContext2D();
		
		boardContext.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		
		for (StaticVisual staticVisual : staticVisuals) {
			staticVisual.draw(boardContext);
		}
		
		for (DynamicVisual dynamicVisual : list) {
			dynamicVisual.draw(boardContext);
		}
	}
	
	/**
	 * A vezerlo treeview elemeit allitja be
	 * @param i a beallitando treeitem
	 */
	public void updateTreeView(TreeItem<String> i) {
		tvElements.setRoot(i);
		tvElements.setShowRoot(false);
	}
}
