package trainelements;

import java.util.List;

import boardelements.EntryPoint;
import color.Color;
import main.EndGameException;
import position.*;
import update.Timer;
import update.Updateable;

/**
 * A vonatot reprezentáló osztály
 */
public class Train implements Updateable {
	/**
	 * A vonathoz tartozó vagonok listája
	 */
	private List<Wagon> myWagons;
	/**
	 * A vonathoz tartozó mozdony
	 */
	private Locomotive myLocomotive;
	/**
	 * A vonat elejét reprezentáló paraméter
	 */
	private StartPos startPos;
	/**
	 * A vonat végét reprezentáló paraméter
	 */
	private EndPos endPos;
	/**
	 * Vonat id-ja
	 */
	private String id;
	
	/**
	 * @param wagons
	 * @param loc
	 * @param ep
	 * @throws EndGameException
	 * vagonokat, egy mozdonyt kap, 
	 * valamint a kezdő pályaelemét, mellyel a kező- és végpozícióját állítja be
	 */
	public Train(List<Wagon> wagons, Locomotive loc, EntryPoint ep) throws EndGameException{
		//System.out.println("New Train created with parameters of type List<Wagon>, " + loc.toString() + " " + ep.toString());
		myLocomotive = loc;
		myWagons = wagons;
		startPos = new StartPos(ep, this);
		endPos = new EndPos(ep, startPos, 0);
	}
	
	/**
	 * @param c
	 * @return
	 * utasokat leszállító függvény, egy színt kap, 
	 * mely alapján kiválasztja a megfelelő vagont, melyről leszállhatnak az utasok
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
		ret.append(" " + myWagons.size() + 1);
		ret.append("\n\t\t" + myLocomotive.toString());
		for(int i = 0; i < myWagons.size(); i++){
			ret.append("\n\t\t" + myWagons.get(i).toString());
		}
		return ret.toString();
	}

}
