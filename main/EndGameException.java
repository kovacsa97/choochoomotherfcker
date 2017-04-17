package main;

/**
 * Kivetel a jatek vegenek jelzesehez
 */
public class EndGameException extends Exception {
	public String tid;
	
	public EndGameException(String id){
		tid = id;
	}
	
	public EndGameException(){

	}
}
