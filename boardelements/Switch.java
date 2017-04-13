package boardelements;

import main.EndGameException;

/**
 *Váltó, a hozzá csatlakozó két BoardElement közül az egyik felé továbbítja 
 *a rá érkezõ vonatot
 *
 */
public class Switch extends BoardElement{
	
	/**
	 * Pályaelem, melyre eltérítheti a vonatot az eredeti útvonal helyett
	 */
	private BoardElement nextSecond;
	/**
	 * a vonat irányát határozza meg a két kijárat közül.
	 */
	private boolean nextActive;

	/**
	 * @param length
	 * konstruktor, length hosszal létrehoz egy váltót
	 */
	public Switch(int length) {
		super(length);
		//System.out.println("New Switch created with parameters of int");
		nextActive = true;

	}
	
	/**
	 * @param b
	 * váltóhoz az eredeti útvonalhoz képest másik, b kijáratot adó metódus
	 */
	public void addExit(BoardElement b){
		nextSecond = b  ;
	}
	
	/**
	 * váltó irányát változtatja
	 */
	public void changeDir(){
		if (nextActive == true)
			nextActive = false;
		else
			nextActive = true;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#getNext()
	 * Visszaadja a next elemet
	 */
	@Override
	public BoardElement getNext() throws EndGameException{
	 if (prev.isOccupied() == false) return prev;
	 else if (prev.isOccupied() == true && nextActive && next.isOccupied() == true) throw new EndGameException();
	 else if (prev.isOccupied() == true && !nextActive && nextSecond.isOccupied() == true) throw new EndGameException();
	 else if (prev.isOccupied() == true && nextActive && next.isOccupied() == false) return next;
	 else return nextSecond;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#toString()
	 * Kiirás teszthez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + nextSecond.getId();
	}

}
