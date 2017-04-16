package boardelements;

import main.EndGameException;

/**
 *Valto, a hozza csatlakozo ket BoardElement kozul az egyik fele tovabbitja 
 *a ra erkezo vonatot
 *
 */
public class Switch extends BoardElement{
	
	/**
	 * Palyaelem, melyre elteritheti a vonatot az eredeti utvonal helyett
	 */
	private BoardElement nextSecond;
	/**
	 * A vonat iranyat hatarozza meg a ket kijarat kozul.
	 */
	private boolean nextActive;

	/**
	 * Konstruktor, length hosszal letrehoz egy valtot
	 * @param length: a palyaelem hossza
	 */
	public Switch(int length) {
		super(length);
		nextActive = true;

	}
	
	/**
	 * Valtohoz az eredeti utvonalhoz kepest masik kijaratot ado metodus
	 * @param b: masik kijarat
	 */
	public void addExit(BoardElement b){
		nextSecond = b  ;
	}
	
	/**
	 * Valto iranyat valtoztatja
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
	 * Kiiras teszthez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + nextSecond.getId();
	}

}
