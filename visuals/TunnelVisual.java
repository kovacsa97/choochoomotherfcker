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
	 * a tunnelbol igy a visualjabol is egy lehet, ezert nem toroljuk, csak nem rjzoljuk ki
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
		int x1 = this.startPos.x;
		int y1 = this.startPos.y;
		int x2 = this.endPos.x;
		int y2 = this.endPos.y;
		
		if (endPos.x-startPos.x<0){
			 x1 = this.endPos.x;
			 y1 = this.endPos.y;
			 x2 = this.startPos.x;
			 y2 = this.startPos.y;
		}
		
		int a = x2 - x1;
		int b = y2 - y1;
		
		boolean isNegative = false;
		
		if (b<0){
			b*=-1;
			isNegative = true;
		}
		
		int d = (int)Math.sqrt(a*a+b*b);

		double sina = (double) b / (double)d;
		double alpha;
		if (isNegative){
			alpha = Math.asin(sina) * -57;
		}
		else{
			alpha = Math.asin(sina) * 57;
		}
		
		c.save();
	    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
	    c.setFill(Color.BROWN);
	    c.fillRect(x1, y1, d, 20);
	    c.restore();
	}
	
	public void setVisible(boolean b){
		visible = b;
	}

}
