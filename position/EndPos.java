package position;

import boardelements.BoardElement;

/**
 * A vonat veget reprezentalo osztaly
 *
 */
public class EndPos extends Position {
	
	/**
	 * hozzatartozo vonat eleje
	 */
	private StartPos myStartPos;

	/**
	 * letrehozza a vonat vegenek poziciojat
	 * @param cbe: (current board element) a letrehozas helye
	 * @param sp: (start position) a vonat kezdopozicioja 
	 * @param pos: a board element-en beluli pozicio
	 */
	public EndPos(BoardElement cbe, StartPos sp, int pos) {
		super(cbe);
		myStartPos = sp;
		this.pos = pos;
	}

	
	/* (non-Javadoc)
	 * @see position.Position#move(int)
	 */
	@Override
	public void move(int dist) {
		if (dist + pos < CurrentBE.getLength()){
			pos+= dist;
		}
		else{
			pos = dist + pos - CurrentBE.getLength();
			CurrentBE.leave();
			CurrentBE = myStartPos.popBack();
		}
	}
	

}
