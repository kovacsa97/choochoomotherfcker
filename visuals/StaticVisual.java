package visuals;

import javafx.scene.canvas.GraphicsContext;

public abstract class StaticVisual {
	
	protected Point startPos;
	protected Point endPos;
	protected String id;
	
	public StaticVisual(Point sp, Point ep, String id){
		startPos = sp;
		endPos = ep;
		this.id = id;
	}
	
	public abstract void draw(GraphicsContext c);
}
