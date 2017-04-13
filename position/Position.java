package position;

import boardelements.BoardElement;

public abstract class Position {
	protected int pos;
	protected BoardElement CurrentBE;
	
	Position (BoardElement cbe){
		//System.out.println("Position was created with parameters type of "+ cbe.toString());
		CurrentBE = cbe;
	}
	
	public abstract void move(int dist) throws Exception;
	
	@Override
	public String toString(){
		return "Position";
	}
}
