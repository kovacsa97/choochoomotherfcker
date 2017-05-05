package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
		
		//pi*fok/180
		int d = (int)Math.sqrt(a*a+b*b);

		double cosa = b/d;
		double alpha = Math.acos(cosa);
		
		c.rotate(alpha);
		c.setFill(Color.GRAY);
		c.fillRect(x1, y1, d, 15);
		c.rotate(-alpha);
	}
}
