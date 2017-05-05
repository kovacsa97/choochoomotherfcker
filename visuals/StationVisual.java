package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class StationVisual extends StaticVisual{

	public StationVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	public void draw(GraphicsContext c) {
		int width = endPos.x - startPos.x;
		int height = width;
		c.setFill(Color.GREEN);
		c.fillRect(this.startPos.x + width/2, startPos.y, width, height);
	}
}
