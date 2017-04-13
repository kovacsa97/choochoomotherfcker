package position;

import boardelements.BoardElement;
import main.EndGameException;

/**
 * A vonat poz�ci�j�t hat�rozza meg
 */
public abstract class Position {
	/**
	 * A jelenlegi p�lyaelemen bel�li poz�ci�
	 */
	protected int pos;
	/**
	 * jelenlegi p�lyaelem, melyen a vonat tart�zkodik
	 */
	protected BoardElement CurrentBE;
	
	/**
	 * @param cbe
	 * l�trehoz egy poz�ci�t a vonatnak
	 */
	Position (BoardElement cbe){
		//System.out.println("Position was created with parameters type of "+ cbe.toString());
		CurrentBE = cbe;
	}
	
	/**
	 * @param dist
	 * @throws EndGameException
	 * Vonat mozg�s�t koordin�l� f�ggv�ny, adott t�vols�ggal mozgatja el�re (dist)
	 */
	public abstract void move(int dist) throws EndGameException;
	
}
