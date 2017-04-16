package boardelements;

/**
 * Ket TunnelOpportunityt osszekoto palyaelem
 *
 */
public class Tunnel extends BoardElement {

	/**
	 * Length hosszal mehivja a BoardElement konstruktorat
	 * @param length: palyaelem hossza
	 */
	public Tunnel(int length) {
		super(length);
	}
	
	/**
	 * Lerombol egy meglevo alagutat
	 */
	public void destroy(){
		if (next!=null) next.lock();
		if (prev!=null) prev.lock();
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#setEnds(boardelements.BoardElement, boardelements.BoardElement)
	 * Vegpontok beallitasa.
	 */
	@Override
	public void setEnds(BoardElement p, BoardElement n){
		destroy();
		next = n;
		n.unlock();
		prev = p;
		p.unlock();
	}

}
