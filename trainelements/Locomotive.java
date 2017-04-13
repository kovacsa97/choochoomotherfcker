package trainelements;

import java.awt.Rectangle;

import update.Timer;

public class Locomotive {
	private int enginePower;
	private int weight;
	private double friction;
	
	
	public Locomotive(int enginePower, int weight, double friction){
		//System.out.println("New Locomotive created with parameters of type int, int, double");
		this.enginePower = enginePower;
		this.weight = weight;
		this.friction = friction;
	}
	
	public void setEnginePower(int ep){
		enginePower+= ep; 
	}
	
	public int getExcursion(){
		//System.out.println("getExcursion was called inside class Locomotive");
		double a = enginePower / weight - friction*weight*10;	// a = F/m - Fs;
		double v0 = a*0.002;									// v0 = a*t
		//System.out.println("getExcursion returned");
		return (int) (v0*0.02 + a / 2 * 0.002 * 0.002);			// s = v0 * t - a/2*t*t (idõ a képfrissítés)
	}
	
	@Override
	public String toString(){
		return "Locomotive";
	}
	
}
