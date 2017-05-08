package visuals;

import java.util.ArrayList;

import board.Board;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import trainelements.Train;

public class TunnelOpportunityVisual extends DynamicVisual{

	public TunnelOpportunityVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
	}
	
	boolean open = false;
	
	@Override
	public void update(Board board, ArrayList<Train> trains){
		if(board.getTunnelOpportunityList().get(super.getId()).isOccupied()){
			open = false;
		}
		else
			open = true;
	}
	
	@Override
	public void draw(GraphicsContext c) {
		
		if(!isActive){
			c.setFill(Color.BLUE);
			c.fillOval(startPos.x-5, startPos.y-5, 20, 20);
		}
		else {
			c.setFill(Color.GOLD);
			c.fillOval(startPos.x-5, startPos.y-5, 20, 20);
		}
		
	}
	

}
