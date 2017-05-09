package visuals;

import java.util.ArrayList;

import board.Board;
import controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import trainelements.Train;

/**
 * a TunnelOpportunity (alagut epitesipont) rajzolasaert felelos osztaly
 *
 */
public class TunnelOpportunityVisual extends DynamicVisual{

	public TunnelOpportunityVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}
	
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#update(board.Board, java.util.ArrayList, controller.Controller)
	 * az absztrakt ososztaly fuggvenyet feluldefinialjuk, hogy ne az hivodjon
	 */
	@Override
	public void update(Board board, ArrayList<Train> trains, Controller c){
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext c) {
		
		if(!isActive){
			c.setFill(Color.BLUE);
		}
		else {
			c.setFill(Color.GOLD);
			
		}
		c.fillOval(startPos.x-7, startPos.y-7, 20, 20);
		
	}
	
	public Point getPoint(){
		return startPos;
	}

}
