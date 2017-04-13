package trainelements;

import color.Color;

public class LoveWagon extends Wagon{

	public LoveWagon(int weight, Color color, int numPass) {
		super(weight, color, numPass);
		System.out.println("New LoveWagon created");
		this.happinessOfPassengers = 0;
	}
	
	@Override
	public void handlePassengers(){
		//System.out.println("HandlePassengers was called inside class LoveWagon");
		this.happinessOfPassengers++;
		//System.out.println("HandlePassengers returned");
	}
	
	@Override
	public String toString(){
		return "LoveWagon";
	}
}
