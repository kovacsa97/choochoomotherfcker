package boardelements;

import trainelements.Train;

public class TunnelOpportunity extends BoardElement{
	
	public TunnelOpportunity(int length) {
		super(length);
		locked = true;
		//System.out.println("New TunnelOpportunity created with parameters of type int");
	}
	
	@Override
	public String toString(){
		return "TunnelOpportunity";
	}

	@Override
	public void enter(Train t) throws Exception {
		//System.out.println("enter was called inside class TunnelOpportunity");
		if (locked == true) throw new Exception("GAME OVER");
		locked = true;
		//System.out.println("enter returned");
	}
}
