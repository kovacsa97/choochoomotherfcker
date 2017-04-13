package trainelements;

import color.Color;

/**
 * Szenesvagon, melyen nincsenek utasok
 */
public class CoalWagon extends Wagon{

	/**
	 * @param weight
	 * konstruktor, weight-el és szürke színnel meghívja a Wagon konstruktorát
	 */
	public CoalWagon(int weight) {
		super(weight, Color.GRAY, 0);
		this.happinessOfPassengers = 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#handlePassengers()
	 * Nem csinál semmit utasok híján
	 */
	@Override
	public void handlePassengers(){
		return;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#getPassengers()
	 * 0 utasssal tér vissza
	 */
	@Override
	public int getPassengers(){
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#passengersGetOn(int)
	 * Nem csinál semmit utasok híján
	 */
	@Override
	public void passengersGetOn(int n){
		return;
	}
	
}
