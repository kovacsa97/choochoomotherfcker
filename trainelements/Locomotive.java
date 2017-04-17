package trainelements;

import java.awt.Rectangle;

import update.Timer;

public class Locomotive {
	private int enginePower;
	private int weight;
	private final double FRICTION = 0.05;
	
	
	public Locomotive(int enginePower, int weight){
		this.enginePower = enginePower;
		this.weight = weight;
	}
	
	public void setEnginePower(int ep){
		enginePower = ep;
	}
	
	public int getExcursion(){
		double a = enginePower / weight - FRICTION*weight*10;	
		double v0 = a*0.002;									
		return (int) (v0*0.02 + a / 2 * 0.002 * 0.002);			
	}
	
	public void setWeight(int weight){
		this.weight+=weight;
	}
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder("locomotive");
		ret.append(" " + (enginePower));
		return ret.toString();
	}
	
}
