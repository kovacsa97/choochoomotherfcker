package visuals;

import javafx.scene.canvas.GraphicsContext;

public abstract class DynamicVisual {
	
	protected Point startPos;
	protected Point endPos;
	private String id;
	protected boolean isActive = false;
	
	public abstract void draw(GraphicsContext c);
	public DynamicVisual(Point sp, Point ep, String id){
		startPos = sp;
		endPos = ep;
		this.id = id;
	}
	public void setPoints(Point sp, Point ep){
		startPos = sp;
		endPos = ep;
	}
	public void setActivation(boolean b){
		isActive = b;
	}
}
