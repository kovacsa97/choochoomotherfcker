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
		
		int a = x2 - x1;
		int b = y2 - y1;
		
		int d = (int)Math.sqrt(a*a+b*b);

		double cosa = b/d;
		double alpha = Math.acos(cosa);
		
		c.save();
	    c.transform(new Affine(new Rotate(alpha*57, x1, y1)));
	    c.setFill(Color.GRAY);
	    c.fillRect(x1, y1, d, 15);
	    c.restore();
	}
}
