package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class TunnelVisual extends DynamicVisual {

	public TunnelVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	@Override
	public void draw(GraphicsContext c) {
		int x1 = this.startPos.x;
		int y1 = this.startPos.y;
		int x2 = this.endPos.x;
		int y2 = this.endPos.y;
		
		int a = x2 - x1;
		int b = y2 - y1;
		
		boolean isNegative = false;
		
		if (a<0){
			a*=-1;
			isNegative = true;
		}
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
	    c.setFill(Color.BROWN);
	    c.fillRect(x1, y1, d, 20);
	    c.restore();
	}

}
