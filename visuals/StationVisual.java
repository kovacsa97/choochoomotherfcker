package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/**
 * a Station (vasutallomas) kirajzolasaert felelos osztaly
 *
 */
public class StationVisual extends StaticVisual{
	
	/**
	 * az allomas szine
	 */
	color.Color myColor;

	public StationVisual(Point sp, Point ep, String id, color.Color c) {
		super(sp, ep, id);
		this.myColor = c;
	}

	/* (non-Javadoc)
	 * @see visuals.StaticVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	public void draw(GraphicsContext c) {
		
		boolean otherSide = false;
		boolean isNegative = false;

		int x1 = this.startPos.x;
		int y1 = this.startPos.y;
		int x2 = this.endPos.x;
		int y2 = this.endPos.y;
		
		if (endPos.x-startPos.x<0){
			 x1 = this.endPos.x;
			 y1 = this.endPos.y;
			 x2 = this.startPos.x;
			 y2 = this.startPos.y;
			 otherSide = true;
		}
		
		int a = x2 - x1;
		int b = y2 - y1;
		
		if (b<0){
			b*=-1;
			isNegative = true;
		}
		
		int d = (int)Math.sqrt(a*a+b*b);

		double sina = (double) b / (double)d;
		double alpha;
		if (isNegative){
			alpha = Math.asin(sina) * -57;
		}
		else{
			alpha = Math.asin(sina) * 57;
		}
		
		c.save();
	    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
	    c.setFill(Color.GRAY);
	    c.fillRect(x1, y1, d, 10);
	    switch (myColor){

		case RED: c.setFill(Color.RED);
					break;
		case BLUE: c.setFill(Color.BLUE);
					break;
		case PINK: c.setFill(Color.PINK);
					break;
		case YELLOW: c.setFill(Color.YELLOW);
					break;
		case GREEN: c.setFill(Color.GREEN);
					break;
		}
	    
	    if (!otherSide)
	    	c.fillRect(x1, y1-30, d, 30);
	    else
	    	c.fillRect(x1, y1+10, d, 30);
	    c.restore();
	}
}
