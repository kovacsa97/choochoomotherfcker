package trainelements;

import java.util.List;

import color.Color;

/**
 * A vagont reprezentáló osztály
 */
public class Wagon {
	
	/**
	 * vagon tömegét reprezentáló attribútum
	 */
	protected int weight;
	/**
	 * a vagonon lévõ utasok boldogságpontja
	 */
	protected int happinessOfPassengers;
	/**
	 * a vagonon lévõ utasok száma
	 */
	protected int numberOfPassengers;
	/**
	 * vagon színe
	 */
	protected Color color;
	
	/**
	 * @param weight
	 * @param color
	 * @param numPass
	 * létrehoz egy vagont a megadott paraméterekkel(tömeg, szín, utasok száma)
	 */
	public Wagon(int weight, Color color, int numPass)
	{
		System.out.println("New Wagon created with parameters of type int, Color, int");
		this.color = color;
		this.weight = weight;
		this.numberOfPassengers = numPass;
		happinessOfPassengers = 1000;                   // Ki kéne találni mi legyen a kezdõérték
														// Át lehetne adni konstruktorban és akkor változhat a nehézség és a kocsikért kapott pont			
	}
		
	/**
	 * @return vagon tömege
	 */
	public int getWeight() 
	{
		return weight;
	}
	
	/**
	 * @return vagon színe
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * @return boldogságpont
	 * leszállítja az utasokat, és visszatér a boldogságpontjukkal
	 */
	public int getPassengers(){
		int numPass = numberOfPassengers;
		numberOfPassengers = 0;
		return numPass*happinessOfPassengers;
	}
	
	/**
	 * minden ütemben csökkenti/növeli az utasok boldogságpontját
	 */
	public void handlePassengers(){
		happinessOfPassengers--;
	}
	
	/**
	 * @return utasok száma
	 */
	public int getNumberOfPassengers(){
		return numberOfPassengers;
	}
	
	/**
	 * @param n
	 * felszállít utasokat, megkapja a felszálló utasok számát
	 */
	public void passengersGetOn(int n){
		this.numberOfPassengers +=n;
	}
	
}
