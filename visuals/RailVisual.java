package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import sun.java2d.loops.FillRect;

public class RailVisual extends StaticVisual{

	public RailVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	public void draw(GraphicsContext c) {
		int x1 = this.startPos.x;
		int y1 = this.startPos.y;
		int x2 = this.endPos.x;
		int y2 = this.endPos.y;
		
		if (endPos.x-startPos.x<0){
			 x1 = this.endPos.x;
			 y1 = this.endPos.y;
			 x2 = this.startPos.x;
			 y2 = this.startPos.y;
		}
		
		int a = x2 - x1;
		int b = y2 - y1;
		
		boolean isNegative = false;
		
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
	    c.restore();
	}
}
