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
		System.out.println(colors.size());
		
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
			
			if (isActive && counter == 0){
				c.save();
			    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
			    c.setFill(Color.GOLD);
			    c.fillOval(x1, y1, d, 12);
			    c.restore();
			    counter++;
			}
			else{
			
				switch (colors.get(counter)){
				case BLACK:  if (!isActive) c.setFill(Color.BLACK);
							break;
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
				case GRAY: c.setFill(Color.LIGHTGRAY);
							break;
				}
				
				c.save();
			    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
			    c.fillOval(x1, y1, d, 12);
			    c.restore();
			    counter++;
			}
		}
	}
}
