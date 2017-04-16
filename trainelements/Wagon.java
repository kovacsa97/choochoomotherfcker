package trainelements;

import java.util.List;

import color.Color;

/**
 * A vagont reprezentalo osztaly
 */

public class Wagon {
	
	/**
	 * wagon tomege
	 */
	protected int weight;
	/**
	 * a vagonon utazo utasok boldogsagpontja
	 */
	protected int happinessOfPassengers;
	/**
	 * a vagonon utazo utasok szama
	 */
	protected int numberOfPassengers;
	/**
	 * vagon szine
	 */
	protected Color color;
	
	/**
	 * Letrehoz egy vagont a megadott parameterekkel
	 * @param weight: wagon tomege
	 * @param color: wagon szine
	 * @param numPass: utasok szama
	 */
	public Wagon(int weight, Color color, int numPass)
	{
		System.out.println("New Wagon created with parameters of type int, Color, int");
		this.color = color;
		this.weight = weight;
		this.numberOfPassengers = numPass;
		happinessOfPassengers = 1000;                  			
	}
		
	/**
	 * Wagon tomeget visszaado metodus
	 * @return wight: tomeg
	 */
	public int getWeight() 
	{
		return weight;
	}
	
	/**
	 * Wagon szinet visszaado metodus
	 * @return color: wagon szine
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Leszallitja az utasokat,visszater a boldogsagpontjukkal
	 * @return int: boldogsagpont
	 */
	public int getPassengers(){
		int numPass = numberOfPassengers;
		numberOfPassengers = 0;
		return numPass*happinessOfPassengers;
	}
	
	/**
	 * Minden utemben csokkenti az utasok boldogsagpontjait
	 */
	public void handlePassengers(){
		happinessOfPassengers--;
	}
	
	/**
	 * Utasok szamaval visszatero metodus
	 * @return numberofPassengers: utasok szama
	 */
	public int getNumberOfPassengers(){
		return numberOfPassengers;
	}
	
	/**
	 * Felszallitja az utasokat
	 * @param n: felszallo utasok szama
	 */
	public void passengersGetOn(int n){
		this.numberOfPassengers +=n;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * toString feluldefinialasa
	 */
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("wagon");
		ret.append(" " + color);
		ret.append(" " + numberOfPassengers);
		return ret.toString();
	}
	
}
