package boardelements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import color.Color;
import main.EndGameException;
import trainelements.CoalWagon;
import trainelements.Locomotive;
import trainelements.LoveWagon;
import trainelements.Train;
import trainelements.TrainModel;
import trainelements.Wagon;
import update.Timer;
import update.Updateable;

/**
 *Megadott id�k�z�nk�nt (vagy ha a randomGeneration false-ra van �ll�tva, 
 *akkor a megadott parancsra) �j vonatot gener�l, 
 *�s elind�tja a hozz� kapcsolt BoardElement fel�.
 */
public class EntryPoint extends BoardElement implements Updateable{
	
	/**
	 * A bel�p�si pont sz�ml�l�ja, ha el�ri a 0 �rt�ket �j vonatot k�ld a p�ly�ra
	 */
	private int time;
	/**
	 * Default v�rakoz�si id�, ha az �jonnan l�trehozott vonat elhagyja a bel�p�si pontot, 
	 * erre az �rt�kre ugrik vissza a sz�ml�l�
	 */
	private int defWaitTime;
	/**
	 * referencia a Timer objektumra, hogy az �j vonatok beregisztr�lhat�k
	 */
	private Timer timer;
	/**
	 * ett�l f�gg�en gener�l a bel�p�si pont random param�terekkel vonatot, 
	 * ha hamis, megadott �rt�kek alapj�n teszi ezt.
	 */
	private boolean randomGeneration = false;
	/**
	 * trainModellek list�ja
	 */
	private List<TrainModel> trainModelList = new ArrayList<TrainModel>();
	
	/**
	 * Wagon s�lya
	 */
	private final int WAGONWEIGHT = 10000;
	/**
	 * Mozdony s�lya
	 */
	private final int LOCOMOTIVEWEIGHT = 20000;
	/**
	 * Mozdony er�
	 */
	private final int LOCOMOTIVEPOWER = 10000000;
	/**
	 * utas sz�m
	 */
	private final int PASSENGERCOUNT = 50;
	/**
	 * maximum wagonok sz�ma
	 */
	private final int MAXWAGONNUMBER = 6;
	/**
	 * speci�lis wagon es�lye
	 */
	private final double SPECIALWAGONCHANCE = 0.3;
	/**
	 * LoweWagon es�lye
	 */
	private final double LOVEWAGONCHANCE = 0.5;
	
	/**
	 * @param defWaitTime
	 * @param length
	 * @param t
	 * , be�ll�tja a default v�rakoz�si id�t, �s kap egy referenci�t a Timer-hez, 
	 * hogy beregisztr�lhassa az �jonnan l�trej�v� vonatokat.
	 */
	public EntryPoint(int defWaitTime, int length){
		super(length);
		this.defWaitTime = defWaitTime;
		time = 0;
	}
	
	/**
	 * @param c
	 * @param passengerCount
	 * @param wagonType
	 * @return
	 * egy vagon l�trehoz�s��rt felel�s, 
	 * tesztel�shez, manu�lis valid�l�shoz sz�ks�ges.
	 */
	private Wagon createWagon(int c, int passengerCount, String wagonType){
		Wagon w = null;
		
		if (wagonType.equals("LOVE")){
			w = new LoveWagon(WAGONWEIGHT, Color.values()[c-1], passengerCount);
		}
		else if (wagonType.equals("COAL")){
			w = new CoalWagon(WAGONWEIGHT);
		}
		else if (wagonType.equals("BASIC")){
			w = new LoveWagon(WAGONWEIGHT, Color.values()[c-1], passengerCount);
		}
		return w;
	}
	
	/**
	 * @param tm
	 * tm vonat hozz�ad�sa trainModel list�hoz
	 */
	public void addTrain(TrainModel tm){
		trainModelList.add(tm);
	}

	/**
	 * @return
	 * @throws EndGameException
	 * random wagonok l�trehoz�sa
	 */
	private Wagon createWagonRandom() throws EndGameException{
		Wagon w;
		double specialWagonChance = Math.random();
		double loveWagonChance = Math.random();
		int color =(int)((Math.random()*10)%(Color.values().length-1));
		
		if (specialWagonChance <= SPECIALWAGONCHANCE){
			if(loveWagonChance < LOVEWAGONCHANCE){
				w = new LoveWagon(WAGONWEIGHT, Color.values()[color], PASSENGERCOUNT);
			}
			else{
				w = new CoalWagon(WAGONWEIGHT);
			}
		}
		
		else{
			w = new Wagon(WAGONWEIGHT, Color.values()[color], PASSENGERCOUNT);
		}
		return w;
	}
	
	/**
	 * @return
	 * @throws EndGameException
	 * k�sz�t egy vagon list�t, a megadott sz�mnak megfelel�en, random �rt�kekkel.
	 */
	private List<Wagon> createWagonList() throws EndGameException{
		
		int wagonNumber =(int)((Math.random()*10)%MAXWAGONNUMBER);
		System.out.println(wagonNumber);
		List<Wagon> wagonsList = new ArrayList<Wagon>();
		for (int i= 0; i<wagonNumber; i++){
			wagonsList.add(createWagonRandom());
		}
		return wagonsList;
	}
	
	/**
	 * @throws EndGameException
	 * vonat l�trehoz�sa
	 */
	public void createTrainRandom() throws EndGameException{

		List<Wagon> wagonList = createWagonList();
		Locomotive loc = new Locomotive(LOCOMOTIVEPOWER, 10);
		loc.setWeight(wagonList.size()*WAGONWEIGHT);
		Train train = new Train(createWagonList(), loc, this);
		timer.registerElement(train);
	}
	
	/**
	 * �jraind�tja a saj�t id�z�t�j�t
	 */
	public void resetTimer(){

		time = defWaitTime;

	}
	
	/**
	 * @throws EndGameException
	 * Ha nincs enged�lyezve a random, akkor p�ly�ra engedi a vonatot
	 */
	public void getNextTrain() throws EndGameException{
		if (trainModelList.size() == 0) return;
		List<Wagon> wagonList = new ArrayList<Wagon>();
		if(trainModelList.get(0).countOfWagons == 0){
			Locomotive loc = new Locomotive(trainModelList.get(0).defDrivingForce, LOCOMOTIVEWEIGHT);
			Train t = new Train(null, loc, this);
			timer.registerElement(t);
			t.setId(trainModelList.get(0).id);
			trainModelList.remove(0);
			return;
		}
		for (int i=0; i<trainModelList.get(0).countOfWagons; i++){
			Wagon w = createWagon(trainModelList.get(0).color.get(i), trainModelList.get(0).passCount.get(i), trainModelList.get(0).type.get(i));
			wagonList.add(w);
		}
		Locomotive loc = new Locomotive(trainModelList.get(0).defDrivingForce, LOCOMOTIVEWEIGHT);
		Train t = new Train(wagonList, loc, this);
		timer.registerElement(t);
		t.setId(trainModelList.get(0).id);
		trainModelList.remove(0);
	}
	
	/* (non-Javadoc)
	 * @see update.Updateable#update()
	 * A sz�ml�l�j�nak f�ggv�ny�ben cselekszik (cs�kkenti a sz�ml�l�t, 
	 * �j vonatot hoz l�tre, ha van �j vonat, de m�g nem hagyta el, akkor v�r)
	 */
	public void update() throws EndGameException{
		
		if (this.randomGeneration == false) return;
		
		if (time == 0){
			createTrainRandom();
			resetTimer();
		}
		else
			time--;
		//System.out.println("update returned");
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * lekezeli a vonat �rkez�s�t
	 */
	public void enter(Train t) throws EndGameException{

		if (next.isOccupied() == true) throw new EndGameException(); 
		
	}
	
	/**
	 * @param randomGeneration
	 * be�ll�tja a random gener�tor �rt�k�t
	 */
	public void setRandomGeneration(boolean randomGeneration) {
		this.randomGeneration = randomGeneration;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#getNext()
	 * visszaadja a k�vetkez� p�lyaelemet
	 */
	@Override
	public BoardElement getNext() throws EndGameException{
		
		if (next.isOccupied() == true)
			throw new EndGameException();
		return next;
	}
	
	/**
	 * @param Timer
	 * megkapja a timer-t �s be�ll�tja az entripointnak, majd beregisztr�lja
	 */
	public void setTimer(Timer t){
		timer = t;
		t.registerElement(this);
	}
}
