package boardelements;

import color.Color;
import trainelements.Train;

/**
 * A megallot reprezentalo osztaly
 */
public class Station extends BoardElement{
	
	/**
	 * Allomas szine
	 */
	private Color color;
	/**
	 * Jatekos pontjait tarolja az allomason
	 */
	private int point;
	/**
	 * Felszallo utasok szama
	 */
	private int newPassengerCount;
	/**
	 * Utasok felszallasanak valoszinusege
	 */
	private double newPassengerProbability = 0;
	/**
	 * Utasok felszallasa random tortenik-e
	 */
	private boolean randomness = true;
	/**
	 * Utasok felszallasa engedelyezett-e
	 */
	private boolean enablePassengers = false;
	
	/**
	 * Letrehoz egy allomast adott szinnel.
	 * @param c: allomas szine
	 * @param length: allomas hossza
	 */
	public Station(int c, int length){
		super(length);
		//System.out.println("New Station created with parameters of type Color, int");
		this.color = Color.values()[c-1];
		this.locked = false;
	}
	
	/**
	 * @return Allomason osszegyujtott pontszamot adja vissza
	 */
	public int getAllPoints(){
		return point;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Meghivja a kapott vonat getPassengers() fuggvenyet es a kapott int-et a pontjaihoz adja, 
	 * ezzel megvalositva a leszallast. 
	 */
	
	@Override
	public void enter(Train t) {
		locked = true;
		int out=t.getPassengers(color);
		System.out.println("takeoff "+id+" "+out);
		point += out;
		if ((randomness && newPassengerProbability>Math.random()) || (!randomness && enablePassengers)){
			t.passengerGetOn(this.color, this.newPassengerCount);
		}
	} 
	
	/**
	 * A felszallo utasok szamat adja vissza
	 * @return newPassengerCount: allomasrol felszallo utasok szama
	 */
	public int getCount() {
		return newPassengerCount;
	}

	/* (non-Javadoc)
	 * @see boardelements.BoardElement#toString()
	 * Kiiras tesztelesehez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + color.toString() + " " + point;
	}
	
	/**
	 * randomness beallitasa
	 * @param r: igaz/hamis ertek
	 */
	public void setRandomness(boolean r){
		randomness = r;
	}
	
	/**
	 * enablePassengers beallitasa
	 * @param s: igaz/hamis ertek
	 */
	public void setPassengerGetOn(boolean s){
		this.enablePassengers = s;
	}
	
	/**
	 * Felszallo utasok szamanak beallitasa
	 * @param val: felszallo utasok szama
	 */
	public void setNewPassengerCount(int val){
		newPassengerCount = val;
	}
	
	/**
	 * Utasok felszallasanak valoszinuseget allito fuggveny
	 * @param val: valoszinusege a felszallasnak
	 */
	public void setNewPassengerProbability(double val){
		newPassengerProbability = val;
	}
}
