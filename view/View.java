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
	GraphicsContext c;
	
	public View(GraphicsContext c){
		this.c = c;
	}
	
	public void initBoard(ArrayList<StaticVisual> list){
		
	}
	
	public void update(ArrayList<DynamicVisual> list){
		
	}
}
