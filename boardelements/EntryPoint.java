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
 *Megadott idõközönként (vagy ha a randomGeneration false-ra van állítva, 
 *akkor a megadott parancsra) új vonatot generál, 
 *és elindítja a hozzá kapcsolt BoardElement felé.
 */
public class EntryPoint extends BoardElement implements Updateable{
	
	/**
	 * A belépési pont számlálója, ha eléri a 0 értéket új vonatot küld a pályára
	 */
	private int time;
	/**
	 * Default várakozási idõ, ha az újonnan létrehozott vonat elhagyja a belépési pontot, 
	 * erre az értékre ugrik vissza a számláló
	 */
	private int defWaitTime;
	/**
	 * referencia a Timer objektumra, hogy az új vonatok beregisztrálhatók
	 */
	private Timer timer;
	/**
	 * ettõl függõen generál a belépési pont random paraméterekkel vonatot, 
	 * ha hamis, megadott értékek alapján teszi ezt.
	 */
	private boolean randomGeneration = false;
	/**
	 * trainModellek listája
	 */
	private List<TrainModel> trainModelList = new ArrayList<TrainModel>();
	
	/**
	 * Wagon súlya
	 */
	private final int WAGONWEIGHT = 10000;
	/**
	 * Mozdony súlya
	 */
	private final int LOCOMOTIVEWEIGHT = 20000;
	/**
	 * Mozdony erõ
	 */
	private final int LOCOMOTIVEPOWER = 10000000;
	/**
	 * utas szám
	 */
	private final int PASSENGERCOUNT = 50;
	/**
	 * maximum wagonok száma
	 */
	private final int MAXWAGONNUMBER = 6;
	/**
	 * speciális wagon esélye
	 */
	private final double SPECIALWAGONCHANCE = 0.3;
	/**
	 * LoweWagon esélye
	 */
	private final double LOVEWAGONCHANCE = 0.5;
	
	/**
	 * @param defWaitTime
	 * @param length
	 * @param t
	 * , beállítja a default várakozási idõt, és kap egy referenciát a Timer-hez, 
	 * hogy beregisztrálhassa az újonnan létrejövõ vonatokat.
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
	 * egy vagon létrehozásáért felelõs, 
	 * teszteléshez, manuális validáláshoz szükséges.
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
	 * tm vonat hozzáadása trainModel listához
	 */
	public void addTrain(TrainModel tm){
		trainModelList.add(tm);
	}

	/**
	 * @return
	 * @throws EndGameException
	 * random wagonok létrehozása
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
	 * készít egy vagon listát, a megadott számnak megfelelõen, random értékekkel.
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
	 * vonat létrehozása
	 */
	public void createTrainRandom() throws EndGameException{

		List<Wagon> wagonList = createWagonList();
		Locomotive loc = new Locomotive(LOCOMOTIVEPOWER, 10);
		loc.setWeight(wagonList.size()*WAGONWEIGHT);
		Train train = new Train(createWagonList(), loc, this);
		timer.registerElement(train);

	}
	
	/**
	 * Újraindítja a saját idõzítõjét
	 */
	public void resetTimer(){

		time = defWaitTime;

	}
	
	/**
	 * @throws EndGameException
	 * Ha nincs engedélyezve a random, akkor pályára engedi a vonatot
	 */
	public void getNextTrain() throws EndGameException{
		List<Wagon> wagonList = new ArrayList<Wagon>();
		if(trainModelList.get(0).countOfWagons == 0){
			Locomotive loc = new Locomotive(trainModelList.get(0).defDrivingForce, LOCOMOTIVEWEIGHT);
			Train t = new Train(null, loc, this);
			timer.registerElement(t);
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
		trainModelList.remove(0);
	}
	
	/* (non-Javadoc)
	 * @see update.Updateable#update()
	 * A számlálójának függvényében cselekszik (csökkenti a számlálót, 
	 * új vonatot hoz létre, ha van új vonat, de még nem hagyta el, akkor vár)
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
	 * lekezeli a vonat érkezését
	 */
	public void enter(Train t) throws EndGameException{

		if (next.isOccupied() == true) throw new EndGameException(); 
		
	}
	
	/**
	 * @param randomGeneration
	 * beállítja a random generátor értékét
	 */
	public void setRandomGeneration(boolean randomGeneration) {
		this.randomGeneration = randomGeneration;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#getNext()
	 * visszaadja a következõ pályaelemet
	 */
	@Override
	public BoardElement getNext() throws EndGameException{
		
		if (next.isOccupied() == true)
			throw new EndGameException();
		return next;
	}
	
	/**
	 * @param Timer
	 * megkapja a timer-t és beállítja az entripointnak, majd beregisztrálja
	 */
	public void setTimer(Timer t){
		timer = t;
		t.registerElement(this);
	}
}
