package position;

import boardelements.BoardElement;

/**
 * A vonat v�g�t reprezent�l� oszt�ly
 *
 */
public class EndPos extends Position {
	
	/**
	 * hozz�tartoz� vonat eleje
	 */
	private StartPos myStartPos;

	/**
	 * @param cbe
	 * @param sp
	 * @param pos
	 * l�trehozza a vonat v�g�nek poz�ci�j�t
	 */
	public EndPos(BoardElement cbe, StartPos sp, int pos) {
		super(cbe);
		//System.out.println("EndPos was created with parameters type of "+ cbe.toString() + " " + sp.toString());
		myStartPos = sp;
		this.pos = pos;
	}

	/* (non-Javadoc)
	 * @see position.Position#move(int)
	 * mozgat�s, a startpos move()-hoz hasonl�an
	 */
	@Override
	public void move(int dist) {
		//System.out.println("move was called inside class EndPos with parameters of type int");
		if (dist + pos < CurrentBE.getLength()){
			pos+= dist;
		}
		else{
			pos = dist + pos - CurrentBE.getLength();
			CurrentBE.leave();
			CurrentBE = myStartPos.popBack();
		}
		
		//System.out.println("move returned BoardElement");
	}
	

}
