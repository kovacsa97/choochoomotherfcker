package position;

import java.util.ArrayList;
import java.util.List;

import boardelements.BoardElement;
import trainelements.Train;

public class StartPos extends Position {
	
	private List<BoardElement> BEfifo;
	Train myTrain;

	public StartPos(BoardElement cbe, Train t) throws Exception {
		super(cbe);
		//System.out.println("StartPos was created with parameters type of "+ cbe.toString());
		pos = cbe.getLength();
		BEfifo = new ArrayList<BoardElement>();
		BEfifo.add(cbe);
		cbe.lock();
	}

	@Override
	public void move(int dist) throws Exception {
		//System.out.println("BEfifo size: " + BEfifo.size());
		//System.out.println("move was called inside class StartPos with parameters of type int");
		if (dist + pos < CurrentBE.getLength()){
			pos+= dist;
			//System.out.println("move returned BoardElement");
			System.out.println("POS: " + pos);
		}
		else{
			BEfifo.add(CurrentBE.getNext());
			pos = dist + pos - CurrentBE.getLength();
			CurrentBE = CurrentBE.getNext();
			CurrentBE.enter(myTrain);
			System.out.println(CurrentBE.toString());
			System.out.println("POS: " + pos);
			//System.out.println("move returned BoardElement");
		}
	}
	
	public BoardElement popBack(){
		//System.out.println("popBack was called inside class StartPos: " + BEfifo.get(0).toString());
		BEfifo.remove(0);
		//System.out.println("popBack returned");
		return BEfifo.get(0);
	}
	
	@Override
	public String toString(){
		return "StartPos";
	}

}
