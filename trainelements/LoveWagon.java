package trainelements;

import color.Color;

/**
 *speciális vagon, melyen nõ az utasok boldogságpontja az idõ elteltével
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
	 * növeli az utasok boldogságpontját
	 */
	@Override
	public void handlePassengers(){
		this.happinessOfPassengers++;
	}
	
}
