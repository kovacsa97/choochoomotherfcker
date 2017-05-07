package visuals;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import trainelements.Train;

public class TrainVisual extends DynamicVisual {
	private ArrayList<Point> myPoints;
	private ArrayList<color.Color> colors;
	
	public TrainVisual(Point sp, Point ep, String id, ArrayList<Point> l){
		super(sp, ep, id);
		myPoints = l;
	}
	
	public void addPoints(ArrayList<Point> l, ArrayList<color.Color> colors){
		myPoints = l;
		this.colors = colors;
	}

	@Override
	public void draw(GraphicsContext c) {
		
		int counter = 0;
		
		for(int i=0; i<myPoints.size(); i+=2){
			int x1 = this.myPoints.get(i).x;
			int y1 = this.myPoints.get(i).y;
			int x2 = this.myPoints.get(i+1).x;
			int y2 = this.myPoints.get(i+1).y;
			
			int a = x2 - x1;
			int b = y2 - y1;
			
			boolean isNegative = false;
			
			if (a<0){
				a*=-1;
				isNegative = true;
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
			
			switch (colors.get(counter)){
			case BLACK: c.setFill(Color.BLACK);
			case RED: c.setFill(Color.RED);
			case BLUE: c.setFill(Color.BLUE);
			case PINK: c.setFill(Color.PINK);
			case YELLOW: c.setFill(Color.YELLOW);
			case GREEN: c.setFill(Color.GREEN);
			case GRAY: c.setFill(Color.LIGHTGRAY);
			}
			
			c.save();
		    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
		    c.fillOval(x1, y1, d, 12);
		    c.restore();
		    counter++;
		}
	}
}
