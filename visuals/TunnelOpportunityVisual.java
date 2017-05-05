package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TunnelOpportunityVisual extends DynamicVisual{

	public TunnelOpportunityVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}

	@Override
	public void draw(GraphicsContext c) {
		c.setFill(Color.BLUE);
	    c.fillOval(startPos.x-10, startPos.y-10, 30, 30);
	}
	

}
