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
	 * @param cbe: (current board element) a letrehozas helye
	 */
	Position (BoardElement cbe){
		CurrentBE = cbe;
	}
	
	/**
	 * Vonat mozgasat koordinalo fuggveny
	 * @param dist: ezt a tavolsagot teszi meg a vonat
	 * @throws EndGameException: ha ket vonat utkozik vagy a vonat kisiklik ez a kivetel keletkezik
	 */
	public abstract void move(int dist) throws EndGameException;

	@Override
	public String toString() {
		return CurrentBE.getId();
	}
	
}
