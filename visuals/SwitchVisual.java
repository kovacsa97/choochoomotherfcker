package visuals;

import java.util.ArrayList;
import java.util.Map.Entry;

import board.Board;
import boardelements.Switch;
import controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import trainelements.Train;

/**
 * a Switch (valto) kirajzolasaert felelos osztaly
 *
 */
public class SwitchVisual extends DynamicVisual {
	/**
	 * a switch epp nem aktiv vegpontja
	 */
	private Point otherEnd;
	/**
	 * a switch allasanak megvaltozasat figyelo valtozo
	 */
	private boolean nextActive;
	
	public SwitchVisual(Point sp, Point ep, Point oe, String id){
		super(sp, ep, id);
		otherEnd = oe;
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#update(board.Board, java.util.ArrayList, controller.Controller)
	 */
	@Override
	public void update(Board board, ArrayList<Train> trains, Controller c){
		Switch currentsw = null;
		for(Entry<String, Switch> s : board.getSwitchList().entrySet()){
			if(s.getKey().equals(super.getId())){
				currentsw = s.getValue();
			}
		}
		if(currentsw == null){
			try {
				throw new Exception("BAJ VAN SwitchVisual.update: Nem letezo switch...");
			} catch (Exception e) {
				// Ez igy eleg bena, de igy a hiba legalabb nyomonkovetheto
				e.printStackTrace();
			}
		}
		if(currentsw.getNextActive() != nextActive){
			nextActive = !nextActive;
			Point tmp = otherEnd;
			otherEnd = super.endPos;
			setPoints(super.startPos, tmp);
		}
	}

	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext c) {
					
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
		
		if(isActive){
			c.setFill(Color.DARKGOLDENROD);
		}
		else{
		    c.setFill(Color.GRAY);
		}
		
		c.save();
		c.transform(new Affine(new Rotate(alpha, x1, y1+5)));	    
	    c.fillRect(x1, y1, d, 10);
	    c.restore();
	    
	    if (endPos.x-startPos.x<0){
			 x1 = this.otherEnd.x;
			 y1 = this.otherEnd.y;
			 x2 = this.startPos.x;
			 y2 = this.startPos.y;
		}
	    
	    else{
	    	x2 = this.otherEnd.x;
			y2 = this.otherEnd.y;
	    }

		a = x2 - x1;
		b = y2 - y1;
		
		isNegative = false;
		
		if (b<0){
			b*=-1;
			isNegative = true;
		}
		
		d = (int)Math.sqrt(a*a+b*b);
		sina = (double) b / (double)d;
		if (isNegative){
			alpha = Math.asin(sina) * -57;
		}
		else{
			alpha = Math.asin(sina) * 57;
		}
		
		if(isActive){
		    c.setFill(Color.GOLD);
		}
		else{
		    c.setFill(Color.GRAY);
		}
				
		c.save();
		c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
	    c.fillRect(x1, y1, d, 10);
	    c.restore();
	}
}
