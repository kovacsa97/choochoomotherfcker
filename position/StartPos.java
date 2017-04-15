package position;

import java.util.ArrayList;
import java.util.List;

import boardelements.BoardElement;
import main.EndGameException;
import trainelements.Train;

/**
 * A vonat elejét reprezentáló osztály
 */
public class StartPos extends Position {
	
	/**
	 * vonat által lefoglalt pályaelemeket tartalmazza
	 */
	private List<BoardElement> BEfifo;
	/**
	 * vonat referenciája amelyikhez tartozik
	 */
	Train myTrain;

	/**
	 * @param cbe
	 * @param t
	 * @throws EndGameException
	 * létrehozza a kapott vonat kezdõpozícióját a kapott pályaelemen.
	 */
	public StartPos(BoardElement cbe, Train t) throws EndGameException {
		super(cbe);
		//System.out.println("StartPos was created with parameters type of "+ cbe.toString());
		pos = cbe.getLength();
		BEfifo = new ArrayList<BoardElement>();
		BEfifo.add(cbe);
		cbe.lock();
	}

	/* (non-Javadoc)
	 * @see position.Position#move(int)
	 * A kapott dist értéket hozzáadja a belsõ pos értékhez. 
	 * Ha ez meghaladja a currentBE hosszát, elkéri a currentBE.getNext() metódussal a következõ pálya elemet, 
	 * lock()-ot hív rá, beleteszi a BEFifo végére a korábbi elemet, meghívja az enter() függvényt (callback miatt szükséges a felszállásnál), 
	 * és módosítja a currentBE-t.
	 */
	@Override
	public void move(int dist) throws EndGameException {
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
	
	/**
	 * @return
	 * eltávolítja, majd visszatér a fenti listából a legutolsó elemmel, ha a vonat elhagyta.
	 */
	public BoardElement popBack(){
		//System.out.println("popBack was called inside class StartPos: " + BEfifo.get(0).toString());
		BEfifo.remove(0);
		//System.out.println("popBack returned");
		return BEfifo.get(0);
	}

}
