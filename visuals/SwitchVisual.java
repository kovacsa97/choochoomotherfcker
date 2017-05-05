package visuals;

public class SwitchVisual extends DynamicVisual {
	private Point otherEnd;
	
	public SwitchVisual(Point sp, Point ep, Point oe, String id){
		super(sp, ep, id);
		otherEnd = oe;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
