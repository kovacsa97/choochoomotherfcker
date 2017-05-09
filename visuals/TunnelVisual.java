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
		c.setStroke(Color.BROWN);
		c.setLineWidth(25);
		c.strokeLine(startPos.x, startPos.y, endPos.x, endPos.y);
	}
	
	public void setVisible(boolean b){
		visible = b;
	}

}
