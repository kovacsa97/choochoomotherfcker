package boardelements;

import color.Color;
import trainelements.Train;

public class Station extends BoardElement{
	
	private Color color;
	private int point;
	private int newPassengerCount;
	private double newPassengerProbability;
	
	public Station(int c, int length){
		super(length);
		//System.out.println("New Station created with parameters of type Color, int");
		this.color = Color.values()[c-1];
		this.locked = false;
	}
	
	public int getAllPoints(){
		return point;
	}
	
	@Override
	public void enter(Train t) {
		//System.out.println("enter was called inside class Station with parameters of type " + t.toString());
		//System.out.println("POINTS: "+ point);
		locked = true;
		point += t.getPassengers(color);
		
		//System.out.println("POINTS: "+ point);
		//System.out.println("enter returned");
	} 
	
	public int getCount() {
		return newPassengerCount;
	}

	@Override
	public String toString(){
		return super.toString() + " " + color.toString() + " " + point;
	}
	

}
