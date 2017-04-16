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
	 * letrehozza a kapott vonat kezdopoziciojat a kapott palyaelemen.
	 * @param cbe: (current board element) a letrehozas helye
	 * @param t: a vonat referenciaja amihez tartozik
	 * @throws EndGameException: ha ket vonat utkozik vagy a vonat kisiklik ez a kivetel keletkezik
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
	 * A kapott dist �rt�ket hozz�adja a bels� pos �rt�khez. 
	 * Ha ez meghaladja a currentBE hossz�t, elk�ri a currentBE.getNext() met�dussal a k�vetkez� p�lya elemet, 
	 * lock()-ot h�v r�, beleteszi a BEFifo v�g�re a kor�bbi elemet, megh�vja az enter() f�ggv�nyt (callback miatt sz�ks�ges a felsz�ll�sn�l), 
	 * �s m�dos�tja a currentBE-t.
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
	 * elt�vol�tja, majd visszat�r a fenti list�b�l a legutols� elemmel, ha a vonat elhagyta.
	 */
	public BoardElement popBack(){
		//System.out.println("popBack was called inside class StartPos: " + BEfifo.get(0).toString());
		BEfifo.remove(0);
		//System.out.println("popBack returned");
		return BEfifo.get(0);
	}

}
