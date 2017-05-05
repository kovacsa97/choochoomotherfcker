package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class EntryPointVisual extends StaticVisual{

	public EntryPointVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	public void draw(GraphicsContext c) {	
		
		int a = this.endPos.x - this.startPos.x;
		int b = this.endPos.y - this.startPos.y;
		
		int d = (int)Math.sqrt(a*a+b*b);

		double cosa = b/d;
		double alpha = Math.acos(cosa);
				
		c.save();
	    c.transform(new Affine(new Rotate(alpha*57, this.startPos.x + d/2, this.startPos.y)));
	    c.setFill(Color.GREEN);
		c.fillRect(this.startPos.x + d/2, this.startPos.y, d, d);
	    c.restore();
	}
}
