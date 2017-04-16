package boardelements;

import main.EndGameException;
import trainelements.Train;

/**
 * A BoardElement absztrakt õsosztály összefogja a pálya elemeinek közös funkcionalitását (pozíció, hosszúság)
 *
 */
public class BoardElement {
	/**
	 * A következõ pályaelem a sorban
	 */
	protected BoardElement prev;
	/**
	 * : Az õt megelõzõ pályaelem
	 */
	protected BoardElement next;
	/**
	 * A pályaelem foglaltságát jelzõ attribútum
	 */
	protected boolean locked;
	/**
	 * A pályaelem hossza
	 */
	protected int length;
	/**
	 * 
	 */
	protected String id;
	
	/**
	 * @param length
	 * Konstruktor, mely létrehozza a pályaelemet length hosszal
	 */
	public BoardElement(int length){
		//System.out.println("New BoardELement created with parameters of type int");
		this.length = length;
		locked = false;
	}
	
	/**
	 * @return length
	 * a pálya hosszával visszatér
	 */
	public int getLength(){
		//System.out.println("getLength was called inside class BoardElement");
		//System.out.println("getLength returned int");
		return length;
	}
	
	/**
	 * @return locked
	 * visszaadja, hogy foglalt-e a pálya
	 */
	public boolean isOccupied(){
		//System.out.println("isOccupied was called inside class BoardElement");
		//System.out.println("isOccupied returned boolean");
		if (prev == null || next == null) return true;
		return locked;
	}
	
	/**
	 * @param t
	 * @throws EndGameException
	 * lekezeli a vonat érkezését az adott pályaelemre úgy, hogy lock-olja
	 */
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class BoardElement");
		//System.out.println("I am entered");
		lock();
		//System.out.println("enter returned");
	}
	
	/**
	 * zárolja a pályaelemet, ha a vonat belép
	 */
	public void lock(){
		//System.out.println("lock was called inside class BoardElement");
		locked = true;
		//System.out.println("lock returned");
	}
	
	/**
	 * feloldja a pályaelemet, ha a vonat elhagyja
	 */
	public void unlock(){
		//System.out.println("unlock was called inside class BoardElement");
		locked = false;
		//System.out.println("unlock returned");
	}
	
	/**
	 * meghívja a felszabadító függvényét a pályaelememnek, ha a vonat elhagyja
	 */
	public void leave(){
		//System.out.println("leave was called inside class BoardElement");
		unlock();
		//System.out.println("leave returned");
	}
	
	/**
	 * @return
	 * @throws EndGameException
	 * visszaadja az õt követõ, szabad pályaelemet. Ha nincs ilyen ütközés történt, kivételt dob.
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
	 * @param p
	 * @param n
	 * beállítja az õt követõ és megelõzõ pályaelemet
	 */
	public void setEnds(BoardElement p, BoardElement n){
		//System.out.println("setEnds was called inside class BoardElement with parameters of type " + p.toString() + ", "+ n.toString());
		next = n;
		prev = p;
		//System.out.println("setEnds returned");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Kiirja a prev és a next id-jét
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
	 * @return id
	 * visszatér a BoardElement id-jával
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * beállítja az id-t
	 */
	public void setId(String id) {
		this.id = id;
	}
}
