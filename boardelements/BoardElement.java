package boardelements;

import main.EndGameException;
import trainelements.Train;

/**
 * A BoardElement absztrakt ososztalya osszefogja a palya elemeinek kozos funkcionalitasat (pozicio, hosszusag)
 *
 */
public class BoardElement {
	/**
	 * A kovetkezo palyaelem a sorban
	 */
	protected BoardElement prev;
	/**
	 *Az ot megelozo palyaelem
	 */
	protected BoardElement next;
	/**
	 * A palyaelem foglaltsagat jelzo attributum
	 */
	protected boolean locked;
	/**
	 * A palyaelem hossza
	 */
	protected int length;
	/**
	 * Egyedi azonosito
	 */
	protected String id;
	
	/**
	 * Konstruktor, mely letrehozza a palyaelemet length hosszal
	 * @param length palyaelem hossza
	 */
	public BoardElement(int length){
		this.length = length;
		locked = false;
	}
	
	/**
	 * A palyaelem hosszaval ter vissza
	 * @return length: palyaelem hossza
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * Visszaadja, hogy foglalt-e a palya
	 * @return locked: palya foglaltsaga
	 */
	public boolean isOccupied(){
		if (prev == null || next == null) return true;
		return locked;
	}
	
	/**
	 * Lekezeli a vonat erkezeset az adott palyaelemre
	 * @param t az erkezo vonat
	 * @throws EndGameException
	 */
	public void enter(Train t) throws EndGameException {
		lock();
	}
	
	/**
	 * Zarolja a palyaelemet vonat erkezesekor
	 */
	public void lock(){
		locked = true;
	}
	
	/**
	 * Feloldja a palyaelemet ha a vonat elhagyja
	 */
	public void unlock(){
		locked = false;
	}
	
	/**
	 * Meghivja a palyaelemfelszabadito metodust
	 */
	public void leave(){
		unlock();
	}
	
	/**
	 * Visszaadja az ot koveto, szabad palyaelemet. Ha nincs ilyen utkozes tortent, kivetelt dob
	 * @return prev/next: a kovetkezo palyaelem
	 * @throws EndGameException
	 */
	public BoardElement getNext() throws EndGameException{
		if (next.isOccupied() == true && prev.isOccupied() == true)
			throw new EndGameException();
		if (next.isOccupied() == false){
			return next;
		}
		if (prev.isOccupied() == false)
			return prev;
		return null;
	}
	
	/**
	 * Getter a kovetkezo palyaelem lekerdezesehez
	 * @return next: kovetkezo palyaelem
	 */
	public BoardElement getNextElement(){
		return next;
	}
	
	/**
	 * Getter az elozo palyaelem lekeresehez
	 * @return prev: elozo palyaelem
	 */
	public BoardElement getPrevElement() {
		return prev;
	}

	/**
	 * Beallitja az ot koveto es megelozo palyaelemet
	 * @param p elozo palyaelem
	 * @param n kovetkezo palyaelem
	 */
	public void setEnds(BoardElement p, BoardElement n){
		next = n;
		prev = p;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Kiirja a prev es a next id-jet
	 */
	@Override
	public String toString(){
		if (prev == null){
			return "null " + next.getId();
		}
		
		if (next == null){
			return prev.getId() + " null";
		}
		return prev.getId() + " " + next.getId();
	}

	/**
	 * Visszater a BoardElement id-javal
	 * @return id: a palyaelem azonositoja
	 */
	public String getId() {
		return id;
	}

	/**
	 * Beallitja az id-t
	 * @param id a palyaelem azonositoja
	 */
	public void setId(String id) {
		this.id = id;
	}
}
