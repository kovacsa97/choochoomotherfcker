package visuals;

import javafx.scene.canvas.GraphicsContext;

public class SwitchVisual extends DynamicVisual {
	private Point otherEnd;
	
	public SwitchVisual(Point sp, Point ep, Point oe, String id){
		super(sp, ep, id);
		otherEnd = oe;
	}

	@Override
	public void draw(GraphicsContext c) {
		// TODO Auto-generated method stub
		
	}
}
