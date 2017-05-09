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
		//calculate angle
		double alpha = Math.atan2((double)(endPos.y - startPos.y), (double)(endPos.x - startPos.x));
		
		//define drawing width
		double width = 40;
		
		//calculate offsets
		double deltaX = width / 2 * Math.cos(alpha);
		double deltaY = width / 2 * Math.sin(alpha);
		
		c.setStroke(Color.CYAN);
		c.setLineWidth(width);
		c.strokeLine(startPos.x + deltaX,
				startPos.y + deltaY,
				endPos.x - deltaX,
				endPos.y - deltaY);
		
	}
}
