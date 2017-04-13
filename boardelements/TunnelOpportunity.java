package boardelements;

import main.EndGameException;
import trainelements.Train;

public class TunnelOpportunity extends BoardElement{
	
	public TunnelOpportunity(int length) {
		super(length);
		locked = true;
		//System.out.println("New TunnelOpportunity created with parameters of type int");
	}


	@Override
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class TunnelOpportunity");
		if (locked == true) throw new EndGameException();
		locked = true;
		//System.out.println("enter returned");
	}
}
