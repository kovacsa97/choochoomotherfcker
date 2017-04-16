package trainelements;

import java.util.ArrayList;
import java.util.List;

import boardelements.EntryPoint;
import color.Color;
import main.EndGameException;
import position.*;
import update.Timer;
import update.Updateable;

/**
 * A vonatot reprezent�l� oszt�ly
 */
public class Train implements Updateable {
	/**
	 * A vonathoz tartoz� vagonok list�ja
	 */
	private List<Wagon> myWagons;
	/**
	 * A vonathoz tartoz� mozdony
	 */
	private Locomotive myLocomotive;
	/**
	 * A vonat elej�t reprezent�l� param�ter
	 */
	private StartPos startPos;
	/**
	 * A vonat v�g�t reprezent�l� param�ter
	 */
	private EndPos endPos;
	/**
	 * Vonat id-ja
	 */
	private String id;
	
	/**
	 * Vagonokat �s egy mozdonyt kap, 
	 * valamint a kezd� p�lyaelem�t, mellyel a kezd� �s v�gpoz�ci�t �ll�tja be
	 * @param wagons
	 * @param loc
	 * @param ep
	 * @throws EndGameException
	 */
	public Train(List<Wagon> wagons, Locomotive loc, EntryPoint ep) throws EndGameException{
		//System.out.println("New Train created with parameters of type List<Wagon>, " + loc.toString() + " " + ep.toString());
		myLocomotive = loc;
		myWagons = wagons;
		if(myWagons == null){
			myWagons = new ArrayList<Wagon>(); 
		}
		startPos = new StartPos(ep, this);
		endPos = new EndPos(ep, startPos, 0);
	}
	
	/**
	 * Utasokat leszállító függvény, egy színt kap, 
	 * mely alapján kiválasztja a megfelelő vagont, melyről leszállhatnak az utasok
	 * @param c
	 * @return
	 */
	public int getPassengers(Color c){
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
		return 0;
	}
	
	/**
	 * @param c
	 * @param n
	 * Utasokat felszállító függvény, mely megkapja egy egész számban, 
	 * hogy hány utas száll fel, valamint egy színt, hogy melyik vonatra szállhatnak fel
	 */
	public void passengerGetOn(Color c, int n){
		//System.out.println("getPassengers was called inside class Train with parameters of type Color");
		int i = 0;
		while (i!=myWagons.size()){
			if (myWagons.get(i).getColor() == c){
				myWagons.get(i).passengersGetOn(n);
			}
			else{
				i++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see update.Updateable#update()
	 * A vonat változásait kezelő függvény: mozgás és utaspontszám ciklusonkénti változását kezeli.
	 */
	@Override
	public void update() throws EndGameException {
		int dist = myLocomotive.getExcursion();
		startPos.move(myLocomotive.getExcursion());
		endPos.move(dist);
		for (int i=0; i<myWagons.size(); i++){
			myWagons.get(i).handlePassengers();
		}
	}
	
	/**
	 * @return a vonat id-je
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * Vonat id-jét beállítja
	 */
	public void setId(String id) {
		this.id = id;
	}

	//TODO
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder("train");
		ret.append(" " + id);
		ret.append(" " + startPos);
		ret.append(" " + endPos);
		ret.append(" " + (myWagons.size() + 1));
		ret.append("\n\t\t" + myLocomotive.toString());
		for(int i = 0; i < myWagons.size(); i++){
			ret.append("\n\t\t" + myWagons.get(i).toString());
		}
		return ret.toString();
	}
	
	public void setDrivingForce(int drivingForce){
		this.myLocomotive.setEnginePower(drivingForce);
	}
}
