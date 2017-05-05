package view;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import visuals.DynamicVisual;
import visuals.StaticVisual;

public class View {
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
