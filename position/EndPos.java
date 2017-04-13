package position;

import boardelements.BoardElement;

public class EndPos extends Position {
	
	private StartPos myStartPos;

	public EndPos(BoardElement cbe, StartPos sp, int pos) {
		super(cbe);
		//System.out.println("EndPos was created with parameters type of "+ cbe.toString() + " " + sp.toString());
		myStartPos = sp;
		this.pos = pos;
	}

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
	
	@Override
	public String toString(){
		return "EndPos";
	}

}
