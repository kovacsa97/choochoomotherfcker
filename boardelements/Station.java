package boardelements;

import color.Color;
import trainelements.Train;

/**
 * A megállót reprezentáló osztály
 */
public class Station extends BoardElement{
	
	/**
	 * Állomás színe
	 */
	private Color color;
	/**
	 * játékos pontjait tárolja az állomáson
	 */
	private int point;
	/**
	 * felszálló utasok száma
	 */
	private int newPassengerCount;
	
	public void setNewPassengerCount(int val){
		newPassengerCount = val;
	}
	/**
	 * utasok felszállásának valószínûsége
	 */
	private double newPassengerProbability;
	
	public void setNewPassengerProbability(double val){
		newPassengerProbability = val;
	}
	/**
	 * randomness
	 */
	private boolean randomness = true;
	/**
	 * utasok elérhetõek-e
	 */
	private boolean enablePassengers = false;
	
	/**
	 * @param c
	 * @param length
	 * létrehoz egy állomást adott színnel.
	 */
	public Station(int c, int length){
		super(length);
		//System.out.println("New Station created with parameters of type Color, int");
		this.color = Color.values()[c-1];
		this.locked = false;
	}
	
	/**
	 * @return állomáson összegyûjtött pontszámot adja vissza
	 */
	public int getAllPoints(){
		return point;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Meghívja a kapott vonat getPassengers() függvényét és a kapott int-et a pontjaihoz adja, 
	 * ezzel megvalósítva a leszállást. 
	 */
	@Override
	public void enter(Train t) {
		//System.out.println("enter was called inside class Station with parameters of type " + t.toString());
		//System.out.println("POINTS: "+ point);
		locked = true;
		point += t.getPassengers(color);
		
		if ((randomness && newPassengerProbability<Math.random()) || (!randomness && enablePassengers)){
			t.passengerGetOn(this.color, this.newPassengerCount);
		}
		
		//System.out.println("POINTS: "+ point);
		//System.out.println("enter returned");
	} 
	
	/**
	 * @return állomásról felszálló utasok száma
	 */
	public int getCount() {
		return newPassengerCount;
	}

	/* (non-Javadoc)
	 * @see boardelements.BoardElement#toString()
	 * kiirás teszteléshez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + color.toString() + " " + point;
	}
	
	/**
	 * @param r
	 * randomness beállítása
	 */
	public void setRandomness(boolean r){
		randomness = r;
	}
	
	/**
	 * @param s
	 * enablePassengers beállítása
	 */
	public void setPassengerGetOn(boolean s){
		this.enablePassengers = s;
	}

}
