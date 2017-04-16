package boardelements;

import main.EndGameException;
import trainelements.Train;

/**
 * A BoardElement absztrakt �soszt�ly �sszefogja a p�lya elemeinek k�z�s funkcionalit�s�t (poz�ci�, hossz�s�g)
 *
 */
public class BoardElement {
	/**
	 * A k�vetkez� p�lyaelem a sorban
	 */
	protected BoardElement prev;
	/**
	 * : Az �t megel�z� p�lyaelem
	 */
	protected BoardElement next;
	/**
	 * A p�lyaelem foglalts�g�t jelz� attrib�tum
	 */
	protected boolean locked;
	/**
	 * A p�lyaelem hossza
	 */
	protected int length;
	/**
	 * 
	 */
	protected String id;
	
	/**
	 * @param length
	 * Konstruktor, mely l�trehozza a p�lyaelemet length hosszal
	 */
	public BoardElement(int length){
		//System.out.println("New BoardELement created with parameters of type int");
		this.length = length;
		locked = false;
	}
	
	/**
	 * @return length
	 * a p�lya hossz�val visszat�r
	 */
	public int getLength(){
		//System.out.println("getLength was called inside class BoardElement");
		//System.out.println("getLength returned int");
		return length;
	}
	
	/**
	 * @return locked
	 * visszaadja, hogy foglalt-e a p�lya
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
	 * lekezeli a vonat �rkez�s�t az adott p�lyaelemre �gy, hogy lock-olja
	 */
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class BoardElement");
		//System.out.println("I am entered");
		lock();
		//System.out.println("enter returned");
	}
	
	/**
	 * z�rolja a p�lyaelemet, ha a vonat bel�p
	 */
	public void lock(){
		//System.out.println("lock was called inside class BoardElement");
		locked = true;
		//System.out.println("lock returned");
	}
	
	/**
	 * feloldja a p�lyaelemet, ha a vonat elhagyja
	 */
	public void unlock(){
		//System.out.println("unlock was called inside class BoardElement");
		locked = false;
		//System.out.println("unlock returned");
	}
	
	/**
	 * megh�vja a felszabad�t� f�ggv�ny�t a p�lyaelememnek, ha a vonat elhagyja
	 */
	public void leave(){
		//System.out.println("leave was called inside class BoardElement");
		unlock();
		//System.out.println("leave returned");
	}
	
	/**
	 * @return
	 * @throws EndGameException
	 * visszaadja az �t k�vet�, szabad p�lyaelemet. Ha nincs ilyen �tk�z�s t�rt�nt, kiv�telt dob.
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
	 * be�ll�tja az �t k�vet� �s megel�z� p�lyaelemet
	 */
	public void setEnds(BoardElement p, BoardElement n){
		//System.out.println("setEnds was called inside class BoardElement with parameters of type " + p.toString() + ", "+ n.toString());
		next = n;
		prev = p;
		//System.out.println("setEnds returned");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Kiirja a prev �s a next id-j�t
	 */
	@Override
	public String toString(){
		return prev.getId() + " " + next.getId();
	}

	/**
	 * @return id
	 * visszat�r a BoardElement id-j�val
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * be�ll�tja az id-t
	 */
	public void setId(String id) {
		this.id = id;
	}
}
