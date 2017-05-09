package trainelements;

import color.Color;

/**
 * Szenesvagon, melyen nincsenek utasok
 */
public class CoalWagon extends Wagon{

	/**
	 * konstruktor, weight-el es szurke szinnel meghivja a Wagon konstruktorat
	 * @param weight wagon tomege
	 */
	public CoalWagon(int weight) {
		super(weight, Color.GRAY, 0);
		this.happinessOfPassengers = 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#handlePassengers()
	 * Nem csinal semmit utasok hijan
	 */
	@Override
	public void handlePassengers(){
		return;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#getPassengers()
	 * 0 utasssal ter vissza
	 */
	@Override
	public int getPassengers(){
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#passengersGetOn(int)
	 * Nem csinal semmit utasok hijan
	 */
	@Override
	public void passengersGetOn(int n){
		return;
	}
	
}
