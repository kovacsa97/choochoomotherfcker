package boardelements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import color.Color;
import trainelements.Locomotive;
import trainelements.LoveWagon;
import trainelements.Train;
import trainelements.Wagon;
import update.Timer;
import update.Updateable;

public class EntryPoint extends BoardElement implements Updateable{
	
	private int time;
	private int defWaitTime;
	private Timer timer;
	private boolean randomGeneration = true;
	
	public EntryPoint(int defWaitTime, int length, Timer t){
		super(length);
		//System.out.println("New EntryPoint created with parameters of type int, int");
		this.defWaitTime = defWaitTime;
		time = 0;
		this.timer = t;
		t.registerElement(this);
	}
	
	private Wagon createWagon(Color c, int passengerCount, int weight){
		Random rand = new Random();
		Wagon w;
		int chance = rand.nextInt(10)+1;
		if (chance<=0.3)
			w = new LoveWagon(weight, Color.RED, passengerCount );
		else
			w = new Wagon(weight, Color.PINK, passengerCount );
		return w;
	}
	
	private List<Wagon> createWagonList(int n){
		Random rand = new Random();
		int wagonNumber = rand.nextInt(n) + 1;
		List<Wagon> wagonsList = new ArrayList<Wagon>();
		for (int i= 0; i<wagonNumber; i++){
			wagonsList.add(createWagon());
		}
		return wagonsList;
	}
	
	public void createTrain() throws Exception{
		//System.out.println("createTrain was called inside class EntryPoint");
		Locomotive loc = new Locomotive(1000000, 10, 0.02);
		Train train = new Train(createWagonList(10), loc, this);
		timer.registerElement(train);
		//System.out.println("CreateTrain returned");
	}
	
	public void resetTimer(){
		//System.out.println("resetTimer was called inside class EntryPoint");
		time = defWaitTime;
		//System.out.println("resetTimer returned");
	}
	
	public void update() throws Exception{
		//System.out.println("update was called inside class EntryPoint");
		if (time == 0){
			createTrain();
			resetTimer();
		}
		else
			time--;
		//System.out.println("update returned");
	}
	
	@Override
	public void enter(Train t) throws Exception{
		//System.out.println("getNext was called inside class EntryPoint");
		if (next.isOccupied() == true) throw new Exception("Game Over"); 
		
	}
	
	@Override
	public String toString(){
		return "EntryPoint";
	}
}
