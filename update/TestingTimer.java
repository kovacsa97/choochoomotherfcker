package update;

import java.io.PrintStream;
import trainelements.Train;
import main.EndGameException;

/**
 * TestingTimer osztály
 *
 */
public class TestingTimer extends Timer {

	/* Ures start() fuggveny
	 * 
	 */
	@Override
	public void start(){}
	
	/* Ures stop() fuggveny
	 * 
	 */
	@Override
	public void stop(){}
	
	/**
	 * @param step
	 * A parameterkent kapott step-szer fogja elvegezni az update-et.
	 */
	public void step(int step) throws EndGameException {
		for(int i = 0; i < step; i++){
			int size = toUpdate.size();
			for (int j=0; j<size; j++){
				toUpdate.get(j).update();
			}
		}
	}
	
	/**
	 * Kiprinteli az aktiv vonatokat
	 * @param ps Stream, ahova ki kell irni
	 */
	public void listTrains(PrintStream ps) {
		for (int i=1;i<toUpdate.size();i++)
			ps.println(toUpdate.get(i).toString());
	}
	
	/**
	 * Beállítja az adott vonat huzoerejet
	 * @param id a vonat azonositoja
	 * @param force a huzoero
	 */
	public void setDrivingForce(String id, int force) {
		for (int i=1;i<toUpdate.size();i++) {
			Train t=(Train)(toUpdate.get(i));
			if (t.getId().equals(id)) t.setDrivingForce(force);
		}
	}
	
}
