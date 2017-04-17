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
 *Megadott idokozonkent (vagy ha a randomGeneration false-ra van allitva, 
 *akkor a megadott parancsra) uj vonatot general, 
 *es elinditja a hozza kapcsolt BoardElement fele.
 */
public class EntryPoint extends BoardElement implements Updateable{
	
	/**
	 * A belepesi pont szamlaloja, ha eleri a 0 erteket uj vonatot kuld a palyara
	 */
	private int time;
	/**
	 * Default varakozasi ido, ha az ujonnan letrehozott vonat elhagyja a belepesi pontot, 
	 * erre az ertekre ugrik vissza a szamlalo
	 */
	private int defWaitTime;
	/**
	 * referencia a Timer objektumra, hogy az uj vonatok beregisztralhatok
	 */
	private Timer timer;
	/**
	 * ettol fuggoen general a belepesi pont random parameterekkel vonatot, 
	 * ha hamis, megadott ertekek alapjan teszi ezt.
	 */
	private boolean randomGeneration = false;
	/**
	 * trainModellek listaja
	 */
	private List<TrainModel> trainModelList = new ArrayList<TrainModel>();
	
	/**
	 * Wagon tomege
	 */
	private final int WAGONWEIGHT = 1;
	/**
	 * Mozdony tomege
	 */
	private final int LOCOMOTIVEWEIGHT = 2;
	/**
	 * Mozdony ero
	 */
	private final int LOCOMOTIVEPOWER = 0;
	/**
	 * Utas szam
	 */
	private final int PASSENGERCOUNT = 50;
	/**
	 * Wagonok maximalis szama
	 */
	private final int MAXWAGONNUMBER = 6;
	/**
	 * Specialis wagon eselye
	 */
	private final double SPECIALWAGONCHANCE = 0.3;
	/**
	 * LoweWagon eselye
	 */
	private final double LOVEWAGONCHANCE = 0.5;
	
	/**
	 * Beallitja a default varakozasi idot, es kap egy referenciat a Timer-hez, 
	 * hogy beregisztralhassa az ujonnan letrejovo vonatokat.
	 * @param defWaitTime: default varakozasi ido
	 * @param length: palyaelem hossza
	 */
	public EntryPoint(int defWaitTime, int length){
		super(length);
		this.defWaitTime = defWaitTime;
		time = 0;
	}
	
	/**
	 * Egy vagon letrehozasaert felelos, 
	 * tesztelesehez, manualis validalasahoz szukseges.
	 * @param c: wagon szine
	 * @param passengerCount: utasok szama
	 * @param wagonType: wagon tipusa
	 * @return w: kesz wagon
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
	 * Vonat modell hozzaadasa trainModel listahoz
	 * @param tm: a vonat modellje
	 */
	public void addTrain(TrainModel tm){
		trainModelList.add(tm);
	}

	/**
	 * Random ertekekkel wagonok letrehozasa
	 * @return w: random wagon
	 * @throws EndGameException
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
	 * Keszit egy wagon listat, random generalt ertekekkel.
	 * @return wagonList: uj wagonok listaja
	 * @throws EndGameException
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
	 * Uj vonat letrehozasa
	 */
	public void createTrainRandom() throws EndGameException{

		List<Wagon> wagonList = createWagonList();
		Locomotive loc = new Locomotive(LOCOMOTIVEPOWER, 10);
		loc.setWeight(wagonList.size()*WAGONWEIGHT);
		Train train = new Train(createWagonList(), loc, this);
		timer.registerElement(train);
	}
	
	/**
	 * Ujrainditja a sajat idozitojet
	 */
	public void resetTimer(){

		time = defWaitTime;

	}
	
	/**
	 * Ha nincs engedelyezve a random, akkor palyara engedi a vonatot
	 * @throws EndGameException
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
	 * A szamlalojanak fuggvenyeben cselekszik (csokkenti a szamlalot, 
	 * uj vonatot hoz letre, ha van uj vonat, de meg nem hagyta el, akkor var)
	 */
	public void update() throws EndGameException{
		
		if (this.randomGeneration == false) return;
		
		if (time == 0){
			createTrainRandom();
			resetTimer();
		}
		else
			time--;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#enter(trainelements.Train)
	 * Lekezeli a vonat erkezeset
	 */
	public void enter(Train t) throws EndGameException{

		if (next.isOccupied() == true) throw new EndGameException(); 
		
	}
	
	/**
	 * Beallitja a random generator erteket
	 * @param randomGeneration: randomgeneracio erteke
	 */
	public void setRandomGeneration(boolean randomGeneration) {
		this.randomGeneration = randomGeneration;
	}
	
	/* (non-Javadoc)
	 * @see boardelements.BoardElement#getNext()
	 * Visszaadja a kovetkezo palyaelemet
	 */
	@Override
	public BoardElement getNext() throws EndGameException{
		
		if (next.isOccupied() == true)
			throw new EndGameException();
		return next;
	}
	
	/**
	 * Megkapja a timer-t es beallitja az entripointnak, majd beregisztralja
	 * @param Timer: beallitando Timer
	 */
	public void setTimer(Timer t){
		timer = t;
		t.registerElement(this);
	}
}
