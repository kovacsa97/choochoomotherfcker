package visuals;

import javafx.scene.canvas.GraphicsContext;

public class StationVisual extends StaticVisual{

	public StationVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	public void draw(GraphicsContext c) {
		c.fillRect(10, 10, 10, 10);
		c.fillRect(8, 8, 10, 2);
		c.fillRect(6, 6, 10, 2);
	}

}
