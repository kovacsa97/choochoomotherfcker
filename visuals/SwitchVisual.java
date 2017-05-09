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
		//calculate angle
		double alpha = Math.atan2((double)(endPos.y - startPos.y), (double)(endPos.x - startPos.x));
		
		//define drawing width
		double width = 15;
		
		//calculate offsets
		double deltaX = width / 2 * Math.cos(alpha);
		double deltaY = width / 2 * Math.sin(alpha);
		
		if(isActive){
			c.setStroke(Color.GOLDENROD);
		}else{
			c.setStroke(Color.GREY);
		}
		c.setLineWidth(width);
		c.strokeLine(startPos.x + deltaX,
				startPos.y + deltaY,
				endPos.x - deltaX,
				endPos.y - deltaY);
//		c.setFill(Color.BLUE);
//		c.fillOval(startPos.x, startPos.y, 2, 2);
//		c.fillOval(endPos.x, endPos.y, 2, 2);
		
		//calculate angle
		alpha = Math.atan2((double)(otherEnd.y - startPos.y), (double)(otherEnd.x - startPos.x));
		//define drawing width
		width = 15;
		
		//calculate offsets
		deltaX = width / 2 * Math.cos(alpha);
		deltaY = width / 2 * Math.sin(alpha);
		
		if(isActive){
			c.setStroke(Color.GOLD);
		}else{
			c.setStroke(Color.GREY);
		}
		c.setLineWidth(width);
		c.strokeLine(startPos.x + deltaX,
				startPos.y + deltaY,
				otherEnd.x - deltaX,
				otherEnd.y - deltaY);
//		c.setFill(Color.BLUE);
//		c.fillOval(startPos.x, startPos.y, 2, 2);
//		c.fillOval(endPos.x, endPos.y, 2, 2);
	}
}
