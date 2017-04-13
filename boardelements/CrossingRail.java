package boardelements;

public class CrossingRail extends Rail{
	
	private Rail otherRail;

	public CrossingRail(int length) {
		super(length);
	}
	
	public void setOtherRail(Rail r){
		otherRail = r;
	}
	
	@Override
	public void lock(){
		this.lock();
		otherRail.lock();
	}
	
	@Override
	public void unlock(){
		this.unlock();
		otherRail.unlock();
	}


}
