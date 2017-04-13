package boardelements;

import main.EndGameException;
import trainelements.Train;

public class BoardElement {
	protected BoardElement prev;
	protected BoardElement next;
	protected boolean locked;
	protected int length;
	protected String id;
	
	public BoardElement(int length){
		//System.out.println("New BoardELement created with parameters of type int");
		this.length = length;
		locked = false;
	}
	
	public int getLength(){
		//System.out.println("getLength was called inside class BoardElement");
		//System.out.println("getLength returned int");
		return length;
	}
	
	public boolean isOccupied(){
		//System.out.println("isOccupied was called inside class BoardElement");
		//System.out.println("isOccupied returned boolean");
		return locked;
	}
	
	public void enter(Train t) throws EndGameException {
		//System.out.println("enter was called inside class BoardElement");
		//System.out.println("I am entered");
		lock();
		//System.out.println("enter returned");
	}
	
	public void lock(){
		//System.out.println("lock was called inside class BoardElement");
		locked = true;
		//System.out.println("lock returned");
	}
	
	public void unlock(){
		//System.out.println("unlock was called inside class BoardElement");
		locked = false;
		//System.out.println("unlock returned");
	}
	
	public void leave(){
		//System.out.println("leave was called inside class BoardElement");
		unlock();
		//System.out.println("leave returned");
	}
	
	public BoardElement getNext() throws EndGameException{
		//System.out.println("getNext was called inside class BoardElement");
		if (next.isOccupied() == true && prev.isOccupied() == true)
			throw new EndGameException();
		if (next.isOccupied() == false){
			//System.out.println("getNext returned BoardElement");
			return next;
		}
		if (prev.isOccupied() == false)
			//System.out.println("getNext returned BoardElement");
			return prev;
		return null;
	}
	
	public void setEnds(BoardElement p, BoardElement n){
		//System.out.println("setEnds was called inside class BoardElement with parameters of type " + p.toString() + ", "+ n.toString());
		next = n;
		prev = p;
		//System.out.println("setEnds returned");
	}
	
	@Override
	public String toString(){
		return "BoardElement";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
