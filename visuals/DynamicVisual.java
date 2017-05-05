package visuals;

public abstract class DynamicVisual {
	
	private Point startPos;
	private Point endPos;
	private String id;
	
	public abstract void draw();
	public DynamicVisual(Point sp, Point ep, String id){
		startPos = sp;
		endPos = ep;
		this.id = id;
	}
	public void setPoints(Point sp, Point ep){
		startPos = sp;
		endPos = ep;
	}
}
