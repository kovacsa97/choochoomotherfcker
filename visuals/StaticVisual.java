package visuals;

import javafx.scene.canvas.GraphicsContext;

/**
 * absytrakt ososytaly a palya statikus elemei ebbol szarmaznak le
 *
 */
public abstract class StaticVisual {
	
	/**
	 * a kirajzolas kezdopontja a Canvas-on
	 */
	protected Point startPos;
	/**
	 * a kirajzolas vegpontja a Canvas-on
	 */
	protected Point endPos;
	/**
	 * megegyezik a modellbeli idval
	 */
	protected String id;
	
	public StaticVisual(Point sp, Point ep, String id){
		startPos = sp;
		endPos = ep;
		this.id = id;
	}
	
	
	
	public String getId() {
		return id;
	}

	public Point getStartPos() {
		return startPos;
	}



	public Point getEndPos() {
		return endPos;
	}



	/**
	 * @param c a javaFX GraphicsContext-je, erre rajzolunk
	 */
	public abstract void draw(GraphicsContext c);
}
