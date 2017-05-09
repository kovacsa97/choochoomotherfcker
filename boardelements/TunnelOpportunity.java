package boardelements;

import main.EndGameException;
import trainelements.Train;

/**
 * Az alagutak vegpontjait reprezentalo osztaly
 */
public class TunnelOpportunity extends BoardElement{
	
	/**
	 * length hosszal meghivja a BoardElement konstruktorat.
	 * @param length palyaelem hossza
	 */
	public TunnelOpportunity(int length) {
		super(length);
		locked = true;
	}


	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Ha az egyik vege null veget vet a jateknak, ha nincs, lockolja.
	 */
	@Override
	public void enter(Train t) throws EndGameException {
		if (locked == true) throw new EndGameException();
		locked = true;
	}
}
