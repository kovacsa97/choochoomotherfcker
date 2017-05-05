package visuals;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class TrainVisual extends DynamicVisual {
	private ArrayList<Point> myPoints;
	
	public TrainVisual(Point sp, Point ep, String id, ArrayList<Point> l){
		super(sp, ep, id);
		myPoints = l;
	}
	
	public void addPoints(ArrayList<Point> l){
		myPoints = l;
	}

	@Override
	public void draw(GraphicsContext c) {
		// TODO Auto-generated method stub
		
	}
}
