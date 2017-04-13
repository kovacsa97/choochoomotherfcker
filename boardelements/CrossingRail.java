package boardelements;

/**
 * olyan sínpárt reprezentál, amit egy másik (hasonló osztályú) sínpár keresztez, 
 * és kezeli a keresztezõdésben való ütközést.
 *
 */
public class CrossingRail extends Rail{
	
	/**
	 * keresztezõ sínt tárolja
	 */
	private Rail otherRail;

	/**
	 * @param length
	 * Rail konstruktorát meghívja lengt hosszal
	 */
	public CrossingRail(int length) {
		super(length);
	}
	
	/**
	 * @param r
	 * beállítja az õt keresztezõ sínt.
	 */
	public void setOtherRail(Rail r){
		otherRail = r;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#lock()
	 * a másik sínt is lockolja.
	 */
	@Override
	public void lock(){
		this.lock();
		otherRail.lock();
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#unlock()
	 * másik sínt is feloldja.
	 */
	@Override
	public void unlock(){
		this.unlock();
		otherRail.unlock();
	}


}
