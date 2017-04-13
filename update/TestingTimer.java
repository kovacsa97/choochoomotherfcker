package update;

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
	public void step(int step){
		for(int i = 0; i < step; i++){
			int size = toUpdate.size();
			for (int j=0; j<size; j++){
				try {
					toUpdate.get(i).update();
				} catch (EndGameException e) {
					e.printStackTrace();
				}
			}
		
		}
	}
	
}
