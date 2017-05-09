package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/**
 * sin kirajyolasaert felelos osytaly
 *
 */
public class RailVisual extends StaticVisual{

	public RailVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	/* (non-Javadoc)
	 * @see visuals.StaticVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	public void draw(GraphicsContext c) {
		//calculate angle
		double alpha = Math.atan2((double)(endPos.y - startPos.y), (double)(endPos.x - startPos.x));
		
		//define drawing width
		double width = 15;
		
		//calculate offsets
		double deltaX = width / 2 * Math.cos(alpha);
		double deltaY = width / 2 * Math.sin(alpha);
		
		c.setStroke(Color.GREY);
		c.setLineWidth(width);
		c.strokeLine(startPos.x + deltaX,
				startPos.y + deltaY,
				endPos.x - deltaX,
				endPos.y - deltaY);
//		c.setFill(Color.BLUE);
//		c.fillOval(startPos.x, startPos.y, 2, 2);
//		c.fillOval(endPos.x, endPos.y, 2, 2);
	}
}
