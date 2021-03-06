package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import parser.ChooChooParser;
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
	
	@FXML
	ComboBox<String> cbTunnelOpps;
	
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
		
		Integer df = Integer.parseInt(lDrivingForceValue.getText());
		df+=1;
		if (df>5){
			df--;
		}
		lDrivingForceValue.setText(df.toString());
		myController.executeSetDrivingForce(df);
	}
	
	/**
	 * kezeli a - gombot
	 */
	public void decreaseDrivingForce() {
		Integer df = Integer.parseInt(lDrivingForceValue.getText());
		df-=1;
		if(df<=0){
			df++;
		}
		lDrivingForceValue.setText(df.toString());
		myController.executeSetDrivingForce(df);
	}
	
	/**
	 * kezeli a valto valtasat
	 */
	public void changeSwitch() {
		myController.executeSwitch();
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
			
			
			cbTunnelOpps.getItems().clear();
			
			for(TreeItem<String> item : tvElements.getRoot().getChildren())
				if (item.getValue()=="Tunnel opportunities")
					for(TreeItem<String> subitem:item.getChildren())
						if (tvElements.getSelectionModel().getSelectedItem() != subitem)
							cbTunnelOpps.getItems().add(subitem.getValue());
			if (!cbTunnelOpps.getItems().isEmpty())
				cbTunnelOpps.getSelectionModel().select(cbTunnelOpps.getItems().get(0));
			
			
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
		Runtime.getRuntime().halt(0);
	}
	
	/**
	 * kezeli a palyamegnyitas menuelemet
	 */
	public void openHandler() {
		List<String> choices = new ArrayList<>();
		for (final File map : new File("maps").listFiles()) {
			String[] names = map.getName().replaceFirst("[.][^.]+$", "").split("_");
			if (!choices.contains(names[0])) choices.add(names[0]);
		}

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Map selection");
		dialog.setHeaderText("Select from the available maps");
		dialog.setContentText("Selected map:");

		Optional<String> result = dialog.showAndWait();
		
		myController.clear();
		myController.setBoard(new ChooChooParser().parse("maps/" + result.get() + ".xml"));
		myController.displayBoard("maps/" + result.get() + "_visual.xml");
		myController.updateInfo();
		
	}
	
	public void GameOverHandler(int score) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game over");
		alert.setHeaderText("According to our information one of your trains has encountered an unexpected obstacle.");
		alert.setContentText("Your final score is "+score);

		alert.showAndWait();
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
	
	public void setEnginePowerValue(int v) {
		
		lDrivingForceValue.setText(Integer.toString(v));
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
	}
	
	/**
	 * kezeli a tunnel rombolo fuggvenyt
	 */
	public void bDestroyTunnelHandler() {
		myController.destroyTunnel();
	}
	
	/**
	 * kezeli a tunnel epito fuggvenyt
	 */
	public void bTunnelBuildHandler() {
		myController.executeTunnel(
				cbTunnelOpps.getSelectionModel().getSelectedItem(), 
				tvElements.getSelectionModel().getSelectedItem().getValue());
	}
}
