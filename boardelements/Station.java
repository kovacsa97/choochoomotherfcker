package boardelements;

import color.Color;
import trainelements.Train;

/**
 * A meg�ll�t reprezent�l� oszt�ly
 */
public class Station extends BoardElement{
	
	/**
	 * �llom�s sz�ne
	 */
	private Color color;
	/**
	 * j�t�kos pontjait t�rolja az �llom�son
	 */
	private int point;
	/**
	 * felsz�ll� utasok sz�ma
	 */
	private int newPassengerCount;
	
	public void setNewPassengerCount(int val){
		newPassengerCount = val;
	}
	/**
	 * utasok felsz�ll�s�nak val�sz�n�s�ge
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
	 * utasok el�rhet�ek-e
	 */
	private boolean enablePassengers = false;
	
	/**
	 * @param c
	 * @param length
	 * l�trehoz egy �llom�st adott sz�nnel.
	 */
	public Station(int c, int length){
		super(length);
		//System.out.println("New Station created with parameters of type Color, int");
		this.color = Color.values()[c-1];
		this.locked = false;
	}
	
	/**
	 * @return �llom�son �sszegy�jt�tt pontsz�mot adja vissza
	 */
	public int getAllPoints(){
		return point;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Megh�vja a kapott vonat getPassengers() f�ggv�ny�t �s a kapott int-et a pontjaihoz adja, 
	 * ezzel megval�s�tva a lesz�ll�st. 
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
	 * @return �llom�sr�l felsz�ll� utasok sz�ma
	 */
	public int getCount() {
		return newPassengerCount;
	}

	/* (non-Javadoc)
	 * @see boardelements.BoardElement#toString()
	 * kiir�s tesztel�shez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + color.toString() + " " + point;
	}
	
	/**
	 * @param r
	 * randomness be�ll�t�sa
	 */
	public void setRandomness(boolean r){
		randomness = r;
	}
	
	/**
	 * @param s
	 * enablePassengers be�ll�t�sa
	 */
	public void setPassengerGetOn(boolean s){
		this.enablePassengers = s;
	}

}
