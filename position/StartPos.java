package position;

import java.util.ArrayList;
import java.util.List;

import boardelements.BoardElement;
import main.EndGameException;
import trainelements.Train;

/**
 * A vonat elejet reprezentalo osztaly
 */
public class StartPos extends Position {
	
	/**
	 * vonat altal lefoglalt palyaelemeket tartalmazza
	 */
	private List<BoardElement> BEfifo;
	
	/**
	 * vonat referenciaja amelyikhez tartozik
	 */
	Train myTrain;

	/**
	 * letrehozza a kapott vonat kezdopoziciojat a kapott palyaelemen
	 * @param cbe: (current board element) a letrehozas helye
	 * @param t: a vonat referenciaja amihez tartozik
	 * @throws EndGameException: ha ket vonat utkozik vagy a vonat kisiklik ez a kivetel keletkezik
	 */
	public StartPos(BoardElement cbe, Train t) throws EndGameException {
		super(cbe);
		pos = cbe.getLength();
		BEfifo = new ArrayList<BoardElement>();
		BEfifo.add(cbe);
		cbe.lock();
	}

	/* (non-Javadoc)
	 * @see position.Position#move(int)
	 */
	@Override
	public void move(int dist) throws EndGameException {
		try{
			if (dist + pos < CurrentBE.getLength()){
				pos+= dist;
			}
			else{
				BEfifo.add(CurrentBE.getNext());
				pos = dist + pos - CurrentBE.getLength();
				CurrentBE = CurrentBE.getNext();
				CurrentBE.enter(myTrain);
			}
		} catch (EndGameException e){
			throw new EndGameException(myTrain.getId());
		}
	}
	
	/**
	 * kiveszi a listabol es visszaadja az utolso elemmet
	 * @return: az elhagyott board element
	 */
	public BoardElement popBack(){
		BEfifo.remove(0);
		return BEfifo.get(0);
	}
}
