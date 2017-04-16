package position;

import boardelements.BoardElement;
import main.EndGameException;

/**
 * A vonat poziciojat hatarozza meg
 */
public abstract class Position {
	/**
	 * A jelenlegi palyaelemen beluli pozicio
	 */
	protected int pos;
	/**
	 * jelenlegi palyaelem, melyen a vonat tartozkodik
	 */
	protected BoardElement CurrentBE;
	
	/**
	 * letrehoz egy poziciot a vonatnak
	 * @param cbe
	 */
	Position (BoardElement cbe){
		//System.out.println("Position was created with parameters type of "+ cbe.toString());
		CurrentBE = cbe;
	}
	
	/**
	 * Vonat mozgasat koordinalo fuggveny, adott tavolsaggal mozgatja elore (dist)
	 * @param dist
	 * @throws EndGameException
	 */
	public abstract void move(int dist) throws EndGameException;
	
}
