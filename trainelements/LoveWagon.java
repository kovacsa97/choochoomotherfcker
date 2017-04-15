package trainelements;

import color.Color;

/**
 *speci�lis vagon, melyen n� az utasok boldogs�gpontja az id� eltelt�vel
 */
public class LoveWagon extends Wagon{

	/**
	 * @param weight
	 * @param color
	 * @param numPass
	 * konstruktor
	 */
	public LoveWagon(int weight, Color color, int numPass) {
		super(weight, color, numPass);
		this.happinessOfPassengers = 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#handlePassengers()
	 * n�veli az utasok boldogs�gpontj�t
	 */
	@Override
	public void handlePassengers(){
		this.happinessOfPassengers++;
	}
	
}
