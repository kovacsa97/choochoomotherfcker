package boardelements;

/**
 * Olyan sinpart reprezental, melyet egy masik sinpar keresztez, 
 * es kezeli a keresztezodesben valo utkozest.
 *
 */
public class CrossingRail extends Rail{
	
	/**
	 * Keresztezo sint tarolja
	 */
	private Rail otherRail;

	/**
	 * Rail konstruktorat meghivja length hosszal
	 * @param length a palyaelem hossza
	 */
	public CrossingRail(int length) {
		super(length);
	}
	
	/**
	 * Beallitja az ot keresztezo sint.
	 * @param r a keresztezo sin
	 */
	public void setOtherRail(Rail r){
		otherRail = r;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#lock()
	 * a masik sint is lockolja.
	 */
	@Override
	public void lock(){
		this.locked = true;
		if (!otherRail.isOccupied())
			otherRail.lock();
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#unlock()
	 * masik sint is feloldja.
	 */
	@Override
	public void unlock(){
		this.locked = true;
		if (!otherRail.isOccupied())
			otherRail.unlock();
	}


}
