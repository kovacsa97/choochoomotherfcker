package position;

import boardelements.BoardElement;
import main.EndGameException;

/**
 * A vonat pozícióját határozza meg
 */
public abstract class Position {
	/**
	 * A jelenlegi pályaelemen belüli pozíció
	 */
	protected int pos;
	/**
	 * jelenlegi pályaelem, melyen a vonat tartózkodik
	 */
	protected BoardElement CurrentBE;
	
	/**
	 * létrehoz egy pozíciót a vonatnak
	 * @param cbe
	 */
	Position (BoardElement cbe){
		//System.out.println("Position was created with parameters type of "+ cbe.toString());
		CurrentBE = cbe;
	}
	
	/**
	 * Vonat mozgását koordináló függvény, adott távolsággal mozgatja előre (dist)
	 * @param dist
	 * @throws EndGameException
	 */
	public abstract void move(int dist) throws EndGameException;
	
}
