package trainelements;

import java.util.List;

import color.Color;

/**
 * A vagont reprezent�l� oszt�ly
 */
public class Wagon {
	
	/**
	 * vagon t�meg�t reprezent�l� attrib�tum
	 */
	protected int weight;
	/**
	 * a vagonon l�v� utasok boldogs�gpontja
	 */
	protected int happinessOfPassengers;
	/**
	 * a vagonon l�v� utasok sz�ma
	 */
	protected int numberOfPassengers;
	/**
	 * vagon sz�ne
	 */
	protected Color color;
	
	/**
	 * @param weight
	 * @param color
	 * @param numPass
	 * l�trehoz egy vagont a megadott param�terekkel(t�meg, sz�n, utasok sz�ma)
	 */
	public Wagon(int weight, Color color, int numPass)
	{
		System.out.println("New Wagon created with parameters of type int, Color, int");
		this.color = color;
		this.weight = weight;
		this.numberOfPassengers = numPass;
		happinessOfPassengers = 1000;                   // Ki k�ne tal�lni mi legyen a kezd��rt�k
														// �t lehetne adni konstruktorban �s akkor v�ltozhat a neh�zs�g �s a kocsik�rt kapott pont			
	}
		
	/**
	 * @return vagon t�mege
	 */
	public int getWeight() 
	{
		return weight;
	}
	
	/**
	 * @return vagon sz�ne
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * @return boldogs�gpont
	 * lesz�ll�tja az utasokat, �s visszat�r a boldogs�gpontjukkal
	 */
	public int getPassengers(){
		int numPass = numberOfPassengers;
		numberOfPassengers = 0;
		return numPass*happinessOfPassengers;
	}
	
	/**
	 * minden �temben cs�kkenti/n�veli az utasok boldogs�gpontj�t
	 */
	public void handlePassengers(){
		happinessOfPassengers--;
	}
	
	/**
	 * @return utasok sz�ma
	 */
	public int getNumberOfPassengers(){
		return numberOfPassengers;
	}
	
	/**
	 * @param n
	 * felsz�ll�t utasokat, megkapja a felsz�ll� utasok sz�m�t
	 */
	public void passengersGetOn(int n){
		this.numberOfPassengers +=n;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("wagon");
		ret.append(" " + color);
		ret.append(" " + numberOfPassengers);
		return ret.toString();
	}
	
}
