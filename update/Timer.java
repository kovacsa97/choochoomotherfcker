package update;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import controller.Controller;
import main.EndGameException;


/**
 * TIMER OSZTALY
 * 
 *
 */
public class Timer {
	
	private boolean game = false;
	private Controller myController;
	protected List<Updateable> toUpdate = new ArrayList<Updateable>();
	
	/**
	 * Regisztralja az elemeket a Timer reszere
	 * @param element
	 */
	public void registerElement(Updateable element){
		toUpdate.add(element);
	}
	
	/**
	 * Elinditja a Timer-t
	 */
	public void start(){
		game = true;
		try{
			while (game){
				int size = toUpdate.size();
				for (int i=0; i<size; i++){
					toUpdate.get(i).update();
				}
				myController.displayChange();
				Thread.sleep(300);
			}
		} catch (EndGameException e){
			if (e.getMessage() == "Game Over"){
				System.out.println(e.getStackTrace() + " GAME OVER");
				stop();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Megallitja a Timer-t
	 */
	public void stop(){
		game = false;
	}
	
	public void setController(Controller c){
		myController = c;
	}
	
	public List<Updateable> getList(){
		return this.toUpdate;
	}
}
