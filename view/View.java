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
		GraphicsContext boardContext=boardCanvas.getGraphicsContext2D();
		
		boardContext.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		
		for (StaticVisual staticVisual : staticVisuals) {
			staticVisual.draw(boardContext);
		}
		
		for (DynamicVisual dynamicVisual : list) {
			dynamicVisual.draw(boardContext);
		}

	}
}
