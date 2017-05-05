package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeView;
import visuals.DynamicVisual;
import visuals.StaticVisual;

public class View {
	@FXML
	TreeView<String> tvElements;
	
	@FXML
	Canvas boardCanvas;
	
	private ArrayList<StaticVisual> staticVisuals;
	
	public View(){
	}
	
	public void initBoard(ArrayList<StaticVisual> list){
		staticVisuals=list;
		for (StaticVisual staticVisual : list) {
			staticVisual.draw(boardCanvas.getGraphicsContext2D());
		}
	}
	
	public void update(ArrayList<DynamicVisual> list){
		for (StaticVisual staticVisual : staticVisuals) {
			staticVisual.draw(boardCanvas.getGraphicsContext2D());
		}
		
		for (DynamicVisual dynamicVisual : list) {
			dynamicVisual.draw(boardCanvas.getGraphicsContext2D());
		}

	}
}
