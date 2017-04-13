package boardelements;

/**
 * olyan s�np�rt reprezent�l, amit egy m�sik (hasonl� oszt�ly�) s�np�r keresztez, 
 * �s kezeli a keresztez�d�sben val� �tk�z�st.
 *
 */
public class CrossingRail extends Rail{
	
	/**
	 * keresztez� s�nt t�rolja
	 */
	private Rail otherRail;

	/**
	 * @param length
	 * Rail konstruktor�t megh�vja lengt hosszal
	 */
	public CrossingRail(int length) {
		super(length);
	}
	
	/**
	 * @param r
	 * be�ll�tja az �t keresztez� s�nt.
	 */
	public void setOtherRail(Rail r){
		otherRail = r;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#lock()
	 * a m�sik s�nt is lockolja.
	 */
	@Override
	public void lock(){
		this.lock();
		otherRail.lock();
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#unlock()
	 * m�sik s�nt is feloldja.
	 */
	@Override
	public void unlock(){
		this.unlock();
		otherRail.unlock();
	}


}
