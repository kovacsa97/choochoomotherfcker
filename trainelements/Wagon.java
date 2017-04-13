package trainelements;

import java.util.List;

import color.Color;

public class Wagon {
	
	protected int weight;
	protected int happinessOfPassengers;
	protected int numberOfPassengers;
	protected Color color;
	
	public Wagon(int weight, Color color, int numPass)
	{
		System.out.println("New Wagon created with parameters of type int, Color, int");
		this.color = color;
		this.weight = weight;
		this.numberOfPassengers = numPass;
		happinessOfPassengers = 1000;                   // Ki kéne találni mi legyen a kezdõérték
														// Át lehetne adni konstruktorban és akkor változhat a nehézség és a kocsikért kapott pont			
	}
		
	public int getWeight() 
	{
		return weight;
	}
	
	public Color getColor(){
		//System.out.println("getColor was called inside class Wagon");
		//System.out.println("getColor returned Color");
		return color;
	}
	
	public int getPassengers(){
		//System.out.println("getPassengers was called inside class Wagon");
		int numPass = numberOfPassengers;
		numberOfPassengers = 0;
		//System.out.println("getPassengers returned int");
		return numPass*happinessOfPassengers;
	}
	
	public void handlePassengers(){
		//System.out.println("HandlePassengers was called inside class Wagon");
		happinessOfPassengers--;
		//System.out.println("HandlePassengers returned");
	}
	
	public int getNumberOfPassengers(){
		return numberOfPassengers;
	}
	
	public void passengersGetOn(int n){
		this.numberOfPassengers +=n;
	}
	
	@Override
	public String toString(){
		return "Wagon";
	}
}
