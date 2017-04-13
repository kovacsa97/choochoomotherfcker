package boardelements;

import main.EndGameException;

/**
 *V�lt�, a hozz� csatlakoz� k�t BoardElement k�z�l az egyik fel� tov�bb�tja 
 *a r� �rkez� vonatot
 *
 */
public class Switch extends BoardElement{
	
	/**
	 * P�lyaelem, melyre elt�r�theti a vonatot az eredeti �tvonal helyett
	 */
	private BoardElement nextSecond;
	/**
	 * a vonat ir�ny�t hat�rozza meg a k�t kij�rat k�z�l.
	 */
	private boolean nextActive;

	/**
	 * @param length
	 * konstruktor, length hosszal l�trehoz egy v�lt�t
	 */
	public Switch(int length) {
		super(length);
		//System.out.println("New Switch created with parameters of int");
		nextActive = true;

	}
	
	/**
	 * @param b
	 * v�lt�hoz az eredeti �tvonalhoz k�pest m�sik, b kij�ratot ad� met�dus
	 */
	public void addExit(BoardElement b){
		nextSecond = b  ;
	}
	
	/**
	 * v�lt� ir�ny�t v�ltoztatja
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
	 * Kiir�s teszthez
	 */
	@Override
	public String toString(){
		return super.toString() + " " + nextSecond.getId();
	}

}
