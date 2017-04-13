package boardelements;

import main.EndGameException;

public class Switch extends BoardElement{
	
	private BoardElement nextSecond;
	private boolean nextActive;

	public Switch(int length) {
		super(length);
		System.out.println("New Switch created with parameters of int");
		nextActive = true;

	}
	
	public void addExit(BoardElement b){
		nextSecond = b  ;
	}
	
	public void changeDir(){
		if (nextActive == true)
			nextActive = false;
		else
			nextActive = true;
	}
	
	@Override
	public BoardElement getNext() throws EndGameException{
	 if (prev.isOccupied() == false) return prev;
	 else if (prev.isOccupied() == true && nextActive && next.isOccupied() == true) throw new EndGameException();
	 else if (prev.isOccupied() == true && !nextActive && nextSecond.isOccupied() == true) throw new EndGameException();
	 else if (prev.isOccupied() == true && nextActive && next.isOccupied() == false) return next;
	 else return nextSecond;
	}
	
	@Override
	public String toString(){
		return super.toString() + " " + nextSecond.getId();
	}

}
