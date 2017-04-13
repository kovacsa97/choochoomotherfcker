package boardelements;

import main.EndGameException;
import trainelements.Train;

/**
 * Az alagutak v�gpontjait reprezent�l� oszt�ly
 */
public class TunnelOpportunity extends BoardElement{
	
	/**
	 * @param length
	 * length hosszal megh�vja a BoardElement konstruktor�t.
	 */
	public TunnelOpportunity(int length) {
		super(length);
		locked = true;
		//System.out.println("New TunnelOpportunity created with parameters of type int");
	}


	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Ha az egyik v�ge null v�get vet a j�t�knak, ha nincs, lockolja.
	 */
	@Override
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class TunnelOpportunity");
		if (locked == true) throw new EndGameException();
		locked = true;
		//System.out.println("enter returned");
	}
}
