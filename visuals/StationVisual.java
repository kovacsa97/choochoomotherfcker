package visuals;

import boardelements.Station;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class StationVisual extends StaticVisual{
	
	color.Color myColor;

	public StationVisual(Point sp, Point ep, String id, color.Color c) {
		super(sp, ep, id);
		this.myColor = c;
	}

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
		boolean otherSide = false;
		
		if(a<0){
			otherSide = true;
		}

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
	    c.setFill(Color.GRAY);
	    c.fillRect(x1, y1, d, 10);
	    switch (myColor){

		case RED: c.setFill(Color.RED);
					break;
		case BLUE: c.setFill(Color.BLUE);
					break;
		case PINK: c.setFill(Color.PINK);
					break;
		case YELLOW: c.setFill(Color.YELLOW);
					break;
		case GREEN: c.setFill(Color.GREEN);
					break;
		}
	    
	    if (!otherSide)
	    	c.fillRect(x1+10, y1-30, d-20, 30);
	    else
	    	c.fillRect(x1+10, y1+30, d-20, 30);
	    c.restore();
	}
}
