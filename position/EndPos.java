package position;

import boardelements.BoardElement;

/**
 * A vonat végét reprezentáló osztály
 *
 */
public class EndPos extends Position {
	
	/**
	 * hozzátartozó vonat eleje
	 */
	private StartPos myStartPos;

	/**
	 * @param cbe
	 * @param sp
	 * @param pos
	 * létrehozza a vonat végének pozícióját
	 */
	public EndPos(BoardElement cbe, StartPos sp, int pos) {
		super(cbe);
		//System.out.println("EndPos was created with parameters type of "+ cbe.toString() + " " + sp.toString());
		myStartPos = sp;
		this.pos = pos;
	}

	/* (non-Javadoc)
	 * @see position.Position#move(int)
	 * mozgatás, a startpos move()-hoz hasonlóan
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
