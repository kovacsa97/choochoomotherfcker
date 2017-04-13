package boardelements;

/**
 * Két TunnelOpportunityt összekötõ pályaelem
 *
 */
public class Tunnel extends BoardElement {

	/**
	 * @param length
	 * Length hosszal meghívja a BoardElement konstruktorát
	 */
	public Tunnel(int length) {
		super(length);
		//System.out.println("New Tunnel created with parameters of type int");

	}
	
	/**
	 * Lerombol egy meglévõ alagutat
	 */
	public void destroy(){
		if (next!=null) next.lock();
		if (prev!=null) prev.lock();
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#setEnds(boardelements.BoardElement, boardelements.BoardElement)
	 * Végpontok beállítása.
	 */
	@Override
	public void setEnds(BoardElement p, BoardElement n){
		//System.out.println("setEnds was called inside class Tunnel with parameters of type " + p.toString() + ", "+ n.toString());
		destroy();
		next = n;
		n.unlock();
		prev = p;
		p.unlock();
		//System.out.println("setEnds returned");
	}

}
