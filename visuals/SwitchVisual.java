package visuals;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
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
		Polygon p = new Polygon();
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
		c.fillPolygon(x, y, 4);
	}
}
