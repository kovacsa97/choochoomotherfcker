package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * a Station (vasutallomas) kirajzolasaert felelos osztaly
 *
 */
public class StationVisual extends StaticVisual{
	
	/**
	 * az allomas szine
	 */
	color.Color myColor;

	public StationVisual(Point sp, Point ep, String id, color.Color c) {
		super(sp, ep, id);
		this.myColor = c;
	}

	/* (non-Javadoc)
	 * @see visuals.StaticVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	public void draw(GraphicsContext c) {
		
		//calculate angle
		double alpha = Math.atan2((double)(endPos.y - startPos.y), (double)(endPos.x - startPos.x));
		
		//define drawing widths
		double railwidth = 15;
		double buildingwidth = 40;
		
		double railDeltaX = railwidth / 2 * Math.cos(alpha);
		double railDeltaY = railwidth / 2 * Math.sin(alpha);
		
		//draw rail
		c.setLineWidth(railwidth);
		c.setStroke(Color.GREY);
		c.strokeLine(startPos.x + railDeltaX,
				startPos.y + railDeltaY,
				endPos.x - railDeltaX,
				endPos.y - railDeltaY);
		
		double buildingDeltaX1 = buildingwidth / 2 * Math.cos(alpha);
		double buildingDeltaY1 = buildingwidth / 2 * Math.sin(alpha);
		
		double buildingDeltaX2 = (buildingwidth + railwidth) / 2 * Math.cos(alpha - Math.PI / 2);
		double buildingDeltaY2 = (buildingwidth + railwidth) / 2 * Math.sin(alpha - Math.PI / 2);
		
		//draw building
		c.setLineWidth(buildingwidth);
	    switch (myColor){
	    case RED:
	    	c.setStroke(Color.RED);
			break;
			
		case BLUE:
			c.setStroke(Color.BLUE);
			break;
			
		case PINK:
			c.setStroke(Color.PINK);
			break;
			
		case YELLOW:
			c.setStroke(Color.YELLOW);
			break;
			
		case GREEN:
			c.setStroke(Color.GREEN);
			break;
					
		default:
			System.out.println("invalod station color: " + id);
			c.setStroke(Color.DARKMAGENTA);
		}
//	    
//	    System.out.println("data for " + id);
//	    System.out.println("raildeltax: " + railDeltaX);
//	    System.out.println("raildeltay: " + railDeltaY);
//	    System.out.println("buildingdeltax1: " + buildingDeltaX1);
//	    System.out.println("buildingdeltax2: " + buildingDeltaX2);
//	    System.out.println("buildingdeltay1: " + buildingDeltaY1);
//	    System.out.println("buildingdeltay2: " + buildingDeltaY2);
		c.strokeLine(startPos.x + buildingDeltaX1 + buildingDeltaX2,
				startPos.y + buildingDeltaY1 + buildingDeltaY2,
				endPos.x - buildingDeltaX1 + buildingDeltaX2,
				endPos.y - buildingDeltaY1 + buildingDeltaY2);
//		c.setFill(Color.BLUE);
//		c.fillOval(startPos.x, startPos.y, 2, 2);
//		c.fillOval(endPos.x, endPos.y, 2, 2);
	    
	}
}
