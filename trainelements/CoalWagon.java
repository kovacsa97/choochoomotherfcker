package trainelements;

import color.Color;

public class CoalWagon extends Wagon{

	public CoalWagon(int weight) {
		super(weight, Color.GRAY, 0);
		this.happinessOfPassengers = 0;
	}
	
	@Override
	public void handlePassengers(){
		return;
	}
	
	@Override
	public int getPassengers(){
		return 0;
	}
	
	@Override
	public void passengersGetOn(int n){
		return;
	}
	
}
