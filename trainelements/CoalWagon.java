package trainelements;

import color.Color;

/**
 * Szenesvagon, melyen nincsenek utasok
 */
public class CoalWagon extends Wagon{

	/**
	 * @param weight
	 * konstruktor, weight-el �s sz�rke sz�nnel megh�vja a Wagon konstruktor�t
	 */
	public CoalWagon(int weight) {
		super(weight, Color.GRAY, 0);
		this.happinessOfPassengers = 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#handlePassengers()
	 * Nem csin�l semmit utasok h�j�n
	 */
	@Override
	public void handlePassengers(){
		return;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#getPassengers()
	 * 0 utasssal t�r vissza
	 */
	@Override
	public int getPassengers(){
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see trainelements.Wagon#passengersGetOn(int)
	 * Nem csin�l semmit utasok h�j�n
	 */
	@Override
	public void passengersGetOn(int n){
		return;
	}
	
}
