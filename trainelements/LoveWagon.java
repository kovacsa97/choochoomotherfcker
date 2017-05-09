package trainelements;

import color.Color;

/**
 *Specialis vagon, melyen no az utasok boldogsagpontja az ido elteltevel
 */
public class LoveWagon extends Wagon{

	/**
	 * konstruktor, mely letrehoz egy LoveWagont adott parameterekkel
	 * @param weight wagon tomege
	 * @param color wagon szine
	 * @param numPass utasok szama
	 */
	public LoveWagon(int weight, Color color, int numPass) {
		super(weight, color, numPass);
		this.happinessOfPassengers = 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#handlePassengers()
	 * noveli az utasok boldogsagpontjat
	 */
	@Override
	public void handlePassengers(){
		this.happinessOfPassengers++;
	}
	
}
