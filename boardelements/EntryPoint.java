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

public class EntryPoint extends BoardElement implements Updateable{
	
	private int time;
	private int defWaitTime;
	private Timer timer;
	private boolean randomGeneration = false;
	private List<TrainModel> trainModelList = new ArrayList<TrainModel>();
	
	private final int WAGONWEIGHT = 10000;
	private final int LOCOMOTIVEWEIGHT = 20000;
	private final int LOCOMOTIVEPOWER = 1000000;
	private final int PASSENGERCOUNT = 50;
	private final int MAXWAGONNUMBER = 6;
	private final double SPECIALWAGONCHANCE = 0.3;
	private final double LOVEWAGONCHANCE = 0.5;
	
	public EntryPoint(int defWaitTime, int length, Timer t){
		super(length);
		this.defWaitTime = defWaitTime;
		time = 0;
		this.timer = t;
		t.registerElement(this);
	}
	
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
	
	public void addTrain(TrainModel tm){
		trainModelList.add(tm);
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	private List<Wagon> createWagonList() throws EndGameException{
		
		int wagonNumber =(int)((Math.random()*10)%MAXWAGONNUMBER);
		List<Wagon> wagonsList = new ArrayList<Wagon>();
		for (int i= 0; i<wagonNumber; i++){
			wagonsList.add(createWagonRandom());
		}
		return wagonsList;
	}
	
	public void createTrainRandom() throws EndGameException{

		List<Wagon> wagonList = createWagonList();
		Locomotive loc = new Locomotive(LOCOMOTIVEPOWER, 10);
		loc.setWeight(wagonList.size()*WAGONWEIGHT);
		Train train = new Train(createWagonList(), loc, this);
		timer.registerElement(train);

	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void resetTimer(){

		time = defWaitTime;

	}
	
	public void getNextTrain() throws EndGameException{
		List<Wagon> wagonList = new ArrayList<Wagon>();
		for (int i=0; i<trainModelList.get(0).countOfWagons; i++){
			Wagon w = createWagon(trainModelList.get(0).color.get(i), trainModelList.get(0).passCount.get(i), trainModelList.get(0).type.get(i));
			wagonList.add(w);
		}
		Locomotive loc = new Locomotive(trainModelList.get(0).defDrivingForce, LOCOMOTIVEWEIGHT);
		Train t = new Train(wagonList, loc, this);
		timer.registerElement(t);
		trainModelList.remove(0);
	}
	
	public void update() throws EndGameException{
		
		if (time == 0){
			createTrainRandom();
			resetTimer();
		}
		else
			time--;
		//System.out.println("update returned");
	}
	
	public void enter(Train t) throws EndGameException{

		if (next.isOccupied() == true) throw new EndGameException(); 
		
	}
	
	public void setRandomGeneration(boolean randomGeneration) {
		this.randomGeneration = randomGeneration;
	}

	@Override
	public String toString(){
		return this.getId();
	}
}
