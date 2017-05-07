package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.util.Builder;
import sun.java2d.loops.FillParallelogram;

public class SwitchVisual extends DynamicVisual {
	private Point otherEnd;
	
	public SwitchVisual(Point sp, Point ep, Point oe, String id){
		super(sp, ep, id);
		otherEnd = oe;
	}

	@Override
	public void draw(GraphicsContext c) {
		/*Polygon p = new Polygon();
		p.getPoints().addAll(new Double[]{ (double) 100, (double) 100, (double) 200, (double) 200, (double) 200, (double) 230, (double) 100, (double) 130});
		p.getTransforms().add(new Rotate(70, 0, 0));
		p.getLocalToSceneTransform();
		
		
		double x[] = new double[4];
		double y[] = new double[4];
		for(int i = 0; i < 8; i++){
			if(i%2 == 0)
				x[i/2] = p.getPoints().get(i);
			else
				y[(i-1)/2] = p.getPoints().get(i);
		}
		for(int i = 0; i < 4; i++){
			System.out.println(x[i] + " " + y[i]);
		}
		c.fillPolygon(x, y, 4);*/
		
		int x1 = this.startPos.x;
		int y1 = this.startPos.y;
		int x2 = this.endPos.x;
		int y2 = this.endPos.y;
		
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
		
		c.save();
		c.transform(new Affine(new Rotate(alpha, x1, y1+5)));	    
		c.setFill(Color.GRAY);
	    c.fillRect(x1, y1, d, 10);
	    c.restore();

		x2 = this.otherEnd.x;
		y2 = this.otherEnd.y;
		
		a = x2 - x1;
		b = y2 - y1;
		
		isNegative = false;
		
		if (a<0){
			a*=-1;
			isNegative = true;
		}
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
		
		c.save();
		c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
	    c.setFill(Color.GRAY);
	    c.fillRect(x1, y1, d, 10);
	    c.restore();
	}
}
