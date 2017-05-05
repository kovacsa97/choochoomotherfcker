package visuals;

import javafx.scene.canvas.GraphicsContext;

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
		c.rotate(90);
		c.fillRect(x1, y1, d, 30);
	}
}
