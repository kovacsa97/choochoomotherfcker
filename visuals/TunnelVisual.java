package visuals;

import java.util.ArrayList;

import board.Board;
import boardelements.Tunnel;
import controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import trainelements.Train;

/**
 * a Tunnel (alagut) rajzolasaert felelos osztaly
 *
 */
public class TunnelVisual extends DynamicVisual {

	/**
	 * a tunnelbol igy a visualjabol is egy lehet, ezert nem toroljuk, csak nem rajzoljuk ki
	 */
	private boolean visible;
	
	public TunnelVisual(Point sp, Point ep, String id) {
		super(sp, ep, id);
		visible = true;
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#update(board.Board, java.util.ArrayList, controller.Controller)
	 */
	@Override
	public void update(Board board, ArrayList<Train> trains, Controller c){
		Tunnel t = board.getTunnel();
		t.getNextElement().getId();
		
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext c) {
		if(!visible)
			return;
		//calculate angle
		double alpha = Math.atan2((double)(endPos.y - startPos.y), (double)(endPos.x - startPos.x));
				
			//define drawing width
			double width = 15;
			
			//calculate offsets
			double deltaX = width / 2 * Math.cos(alpha);
			double deltaY = width / 2 * Math.sin(alpha);
			
			c.setStroke(Color.BROWN);
			c.setLineWidth(width);
			c.strokeLine(startPos.x + deltaX,
					startPos.y + deltaY,
					endPos.x - deltaX,
					endPos.y - deltaY);
	}
	public void setVisible(boolean b){
		visible = b;
	}

}
