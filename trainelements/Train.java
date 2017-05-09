package trainelements;

import java.util.ArrayList;
import java.util.List;

import boardelements.BoardElement;
import boardelements.EntryPoint;
import color.Color;
import main.EndGameException;
import position.*;
import update.Timer;
import update.Updateable;

/**
 * A vonatot reprezentalo osztaly
 */
public class Train implements Updateable {
	/**
	 * A vonathoz tartozo wagonok listaja
	 */
	private List<Wagon> myWagons;

	/**
	 * A vonathoz tartozo mozdony
	 */
	private Locomotive myLocomotive;
	/**
	 * A vonat elejet reprezentalo parameter
	 */
	private StartPos startPos;
	/**
	 * A vonat veget reprezentalo parameter
	 */
	private EndPos endPos;
	/**
	 * Vonat id-ja
	 */
	private String id;
	
	/**
	 * Konstruktor mely letrehoz egy vonatot adott parameterekkel
	 * @param wagons vonathoz tartozo wagonok
	 * @param loc vonathoz tartozo mozdony
	 * @param ep a vonat kezdo palyaeleme (belepesi pont)
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
		endPos = new EndPos(ep, startPos, ep.getLength()-(wagons.size()+1)*20);
		id = "";
	}
	
	/**
	 * Utasokat leszallito fuggveny 
	 * @param c allomas szine ahova avonat erkezett
	 * @return int wagonokon utazo utasok 
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
	 * Utasokat felszallito fuggveny
	 * @param c az allomas szine ahova a vonat erkezett
	 * @param n felszallo utasok szama
	 */
	public void passengerGetOn(Color c, int n){
		//System.out.println("getPassengers was called inside class Train with parameters of type Color");
		int i = 0;
		while (i!=myWagons.size()){
			if (myWagons.get(i).getColor() == c){
				myWagons.get(i).passengersGetOn(n);
				return;
			}
			else{
				i++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see update.Updateable#update()
	 * A vonat valtozassait kezelo fuggveny
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
	 * Id visszaado fuggveny
	 * @return id a vonat azonositoja
	 */
	public String getId() {
		return id;
	}

	/**
	 * Vonat azonositojat beallitja
	 * @param id vonat azonositoja
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * toString metodus feluldefinialasa
	 */
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder("train");
		ret.append(" " + id);
		ret.append(" " + startPos);
		ret.append(" " + endPos);
		ret.append(" " + (myWagons.size() + 1));
		ret.append("\n\t" + myLocomotive.toString());
		for(int i = 0; i < myWagons.size(); i++){
			ret.append("\n\t" + myWagons.get(i).toString());
		}
		return ret.toString();
	}
	
	/**
	 * Huzoerot beallito fuggveny
	 *@param drivingForce huzoero nagysaga
	 */
	public void setDrivingForce(int drivingForce){
		this.myLocomotive.setEnginePower(drivingForce);
	}
	
	
	/**
	 * Getter a vagonok visszaadasahoz
	 * @return myWagons vagonok listaja
	 */
	public List<Wagon> getMyWagons() {
		return myWagons;
	}
	
	
	/**
	 * Getter a mozdony visszaadasahoz
	 * @return myLocomotive a vonat mozdonya
	 */
	public Locomotive getMyLocomotive() {
		return myLocomotive;
	}
	
	public ArrayList<BoardElement> getBeFIFO(){
		return startPos.getBeFIFO();
	}

	public StartPos getStartPos() {
		return startPos;
	}

	public EndPos getEndPos() {
		return endPos;
	}
	
	
}
