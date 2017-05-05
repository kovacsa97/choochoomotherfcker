package visuals;

public abstract class DynamicVisual {
	
	private Point startPos;
	private Point endPos;
	private String id;
	
	public abstract void draw();
	public void setPoints(Point sp, Point ep){
		startPos = sp;
		endPos = ep;
	}
}
