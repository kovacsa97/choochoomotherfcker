package trainelements;

import java.util.List;

import boardelements.EntryPoint;
import color.Color;
import main.EndGameException;
import position.*;
import update.Timer;
import update.Updateable;

public class Train implements Updateable {
	private List<Wagon> myWagons;
	private Locomotive myLocomotive;
	private StartPos startPos;
	private EndPos endPos;
	private String id;
	
	public Train(List<Wagon> wagons, Locomotive loc, EntryPoint ep) throws EndGameException{
		System.out.println("New Train created with parameters of type List<Wagon>, " + loc.toString() + " " + ep.toString());
		myLocomotive = loc;
		myWagons = wagons;
		startPos = new StartPos(ep, this);
		endPos = new EndPos(ep, startPos, 0);
	}
	
	public int getPassengers(Color c){
		//System.out.println("getPassengers was called inside class Train with parameters of type Color");
		int i = 0;
		while (i!=myWagons.size()){
			if (myWagons.get(i).getColor() == c)
				return myWagons.get(i).getPassengers();
			if (myWagons.get(i).getColor() != c && myWagons.get(i).getNumberOfPassengers() != 0){
				return 0;
			}
			else 
				i++;
		}
		//System.out.println("getPassengers returned int");
		return 0;
	}

	@Override
	public void update() throws EndGameException {
		//System.out.println("update was called inside class Train");
		int dist = myLocomotive.getExcursion();
		startPos.move(myLocomotive.getExcursion());
		endPos.move(dist);
		for (int i=0; i<myWagons.size(); i++){
			myWagons.get(i).handlePassengers();
		}
		//System.out.println("update returned");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString(){
		return "Train";
	}

}
