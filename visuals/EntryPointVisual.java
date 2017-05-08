package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/**
 * Az EntryPoint megjeleniteseert felelos osztaly
 *
 */
public class EntryPointVisual extends StaticVisual{

	public EntryPointVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	/* (non-Javadoc)
	 * @see visuals.StaticVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	public void draw(GraphicsContext c) {	
		
		int a = this.endPos.x - this.startPos.x;
		int b = this.endPos.y - this.startPos.y;
		
		int d = (int)Math.sqrt(a*a+b*b);
		
		boolean isNegative = false;
		
		if (a<0){
			a*=-1;
			isNegative = true;
		}
		if (b<0){
			b*=-1;
			isNegative = true;
		}
		
		double sina = (double) b / (double)d;
		double alpha;
		if (isNegative){
			alpha = Math.asin(sina) * -57;
		}
		else{
			alpha = Math.asin(sina) * 57;
		}
				
		c.save();
	    c.transform(new Affine(new Rotate(alpha, this.startPos.x, this.startPos.y+ d/2)));
	    c.setFill(Color.CYAN);
		c.fillRect(this.startPos.x, this.startPos.y-d/2, d, d);
	    c.restore();
	}
}
