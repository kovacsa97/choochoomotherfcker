package trainelements;

import java.awt.Rectangle;

import update.Timer;

public class Locomotive {
	private int enginePower;
	private int weight;
	private final double FRICTION = 0.05;
	
	
	public Locomotive(int enginePower, int weight){
		//System.out.println("New Locomotive created with parameters of type int, int, double");
		this.enginePower = enginePower;
		this.weight = weight;
	}
	
	public void setEnginePower(int ep){
		enginePower+= ep; 
	}
	
	public int getExcursion(){
		//System.out.println("getExcursion was called inside class Locomotive");
		double a = enginePower / weight - FRICTION*weight*10;	// a = F/m - Fs;
		double v0 = a*0.002;									// v0 = a*t
		//System.out.println("getExcursion returned");
		return (int) (v0*0.02 + a / 2 * 0.002 * 0.002);			// s = v0 * t - a/2*t*t (id� a k�pfriss�t�s)
	}
	
	public void setWeight(int weight){
		this.weight+=weight;
	}
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder("locomotive");
		ret.append(" " + enginePower);
		return ret.toString();
	}
	
}
