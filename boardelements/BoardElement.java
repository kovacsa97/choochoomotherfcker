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
	 * @param length: palyaelem hossza
	 */
	public BoardElement(int length){
		//System.out.println("New BoardELement created with parameters of type int");
		this.length = length;
		locked = false;
	}
	
	/**
	 * A palyaelem hosszaval ter vissza
	 * @return length: palyaelem hossza
	 */
	public int getLength(){
		//System.out.println("getLength was called inside class BoardElement");
		//System.out.println("getLength returned int");
		return length;
	}
	
	/**
	 * Visszaadja, hogy foglalt-e a palya
	 * @return locked: palya foglaltsaga
	 */
	public boolean isOccupied(){
		//System.out.println("isOccupied was called inside class BoardElement");
		//System.out.println("isOccupied returned boolean");
		if (prev == null || next == null) return true;
		return locked;
	}
	
	/**
	 * Lekezeli a vonat erkezeset az adott palyaelemre
	 * @param t: az erkezo vonat
	 * @throws EndGameException
	 */
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class BoardElement");
		//System.out.println("I am entered");
		lock();
		//System.out.println("enter returned");
	}
	
	/**
	 * Zarolja a palyaelemet vonat erkezesekor
	 */
	public void lock(){
		//System.out.println("lock was called inside class BoardElement");
		locked = true;
		//System.out.println("lock returned");
	}
	
	/**
	 * Feloldja a palyaelemet ha a vonat elhagyja
	 */
	public void unlock(){
		//System.out.println("unlock was called inside class BoardElement");
		locked = false;
		//System.out.println("unlock returned");
	}
	
	/**
	 * Meghivja a palyaelemfelszabadito metodust
	 */
	public void leave(){
		//System.out.println("leave was called inside class BoardElement");
		unlock();
		//System.out.println("leave returned");
	}
	
	/**
	 * Visszaadja az ot koveto, szabad palyaelemet. Ha nincs ilyen utkozes tortent, kivetelt dob
	 * @return prev/next: a kovetkezo palyaelem
	 * @throws EndGameException
	 */
	public BoardElement getNext() throws EndGameException{
		//System.out.println("getNext was called inside class BoardElement");
		if (next.isOccupied() == true && prev.isOccupied() == true)
			throw new EndGameException();
		if (next.isOccupied() == false){
			//System.out.println("getNext returned BoardElement");
			return next;
		}
		if (prev.isOccupied() == false)
			//System.out.println("getNext returned BoardElement");
			return prev;
		return null;
	}
	
	/**
	 * Beallitja az ot koveto es megelozo palyaelemet
	 * @param p: elozo palyaelem
	 * @param n: kovetkezo palyaelem
	 */
	public void setEnds(BoardElement p, BoardElement n){
		//System.out.println("setEnds was called inside class BoardElement with parameters of type " + p.toString() + ", "+ n.toString());
		next = n;
		prev = p;
		//System.out.println("setEnds returned");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Kiirja a prev és a next id-jet
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
	 * @param id: a palyaelem azonositoja
	 */
	public void setId(String id) {
		this.id = id;
	}
}
