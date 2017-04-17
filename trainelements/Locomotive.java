package trainelements;

import java.awt.Rectangle;

import update.Timer;

/**
 *A mozdonyt reprezentalo osztaly.
 */
public class Locomotive {
	/**
	 * vonat huzoereje
	 */
	private int enginePower;
	/**
	 * vonat sulya
	 */
	private int weight;
	/**
	 * surlodas
	 */
	private final double FRICTION = 0.05;
	
	
	/**
	 * letrehozza a vagont mozgasleiro parameterekkel
	 * @param enginePower
	 * @param weight
	 */
	public Locomotive(int enginePower, int weight){
		this.enginePower = enginePower;
		this.weight = weight;
	}
	
	/**
	 * motorerot allitja be
	 * @param ep
	 */
	public void setEnginePower(int ep){
		enginePower = ep;
	}
	
	/**
	 * kiszamolja a megtett tavolsagot adott ido alatt
	 * @return megtett tavolsag adott ido alatt
	 */
	public int getExcursion(){
		double a = enginePower / weight - FRICTION*weight*10;	
		double v0 = a*0.002;									
		return (int) (v0*0.02 + a / 2 * 0.002 * 0.002);			
	}
	
	/**
	 * sulyt allitja be
	 * @param weight
	 */
	public void setWeight(int weight){
		this.weight+=weight;
	}
	/* (non-Javadoc)
	 * kiiro fuggveny teszteleshez
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder("locomotive");
		ret.append(" " + (enginePower));
		return ret.toString();
	}
	
}
