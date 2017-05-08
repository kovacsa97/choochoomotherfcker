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
public class Timer extends Thread{
	
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
	@Override
	public void start(){
		game = true;
		try{
			while (game){
				//System.out.println("x");
				int size = toUpdate.size();
				for (int i=0; i<size; i++){
					toUpdate.get(i).update();
				}
				myController.displayChange();
				myController.updateInfo();
				Thread.sleep(300);
			}
		} catch (EndGameException e){
			if (e.getMessage() == "Game Over"){
				System.out.println(e.getStackTrace() + " GAME OVER");
				stopMe();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Megallitja a Timer-t
	 */
	public void stopMe(){
		game = false;
	}
	
	public void setController(Controller c){
		myController = c;
	}
	
	public List<Updateable> getList(){
		return this.toUpdate;
	}
}
