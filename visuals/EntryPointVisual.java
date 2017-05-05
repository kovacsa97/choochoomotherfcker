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

		double sina = (double)b/(double)d;
		double alpha = Math.asin(sina) * 57;
				
		c.save();
	    c.transform(new Affine(new Rotate(alpha, this.startPos.x, this.startPos.y-d/2)));
	    c.setFill(Color.GREEN);
		c.fillRect(this.startPos.x, this.startPos.y-d/2, d, d);
	    c.restore();
	}
}
