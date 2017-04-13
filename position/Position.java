package position;

import boardelements.BoardElement;
import main.EndGameException;

/**
 * Pozíció osztály mely tárol egy pályaelemet és azon belüli helyzetet
 */
public abstract class Position {
	/**
	 * A jelenlegi pályaelemen való poziíció
	 */
	protected int pos;
	/**
	 * A jelenlegi pályaelem
	 */
	protected BoardElement CurrentBE;
	
	/**
	 * Konstruktor mely létrehoz egy pozíciót
	 * @param cbe a pályaelem amin a pozíció keletkezik
	 */
	Position (BoardElement cbe){
		//System.out.println("Position was created with parameters type of "+ cbe.toString());
		CurrentBE = cbe;
	}
	
	/**
	 * Absztrakt mozgató fv, mely mozgatja a pozíciót egy megadott távolsággal
	 * @param dist a távolság, melyel a pozíció mozog
	 * @return a következõ, vagy jelenlegi pályaelem
	 * @throws Exception
	 */
	public abstract void move(int dist) throws EndGameException;
	
	@Override
	public String toString(){
		return "Position";
	}
}
