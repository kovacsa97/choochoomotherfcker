package main;

/**
 * Kivetel a jatek vegenek jelzesehez
 */
public class EndGameException extends Exception {
	public String tid;
	
	/**
	 * @param id
	 * konstruktor
	 */
	public EndGameException(String id){
		tid = id;
	}
	
	/**
	 *ures konstruktor 
	 */
	public EndGameException(){

	}
}
