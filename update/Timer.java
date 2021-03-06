package update;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import main.EndGameException;
import trainelements.Train;


/**
 * TIMER OSZTALY
 * 
 *
 */
public class Timer extends Thread {
	
	private volatile boolean game = false;
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
				
				ArrayList<Train> list = new ArrayList<Train>();
				
				for (int i=1; i<toUpdate.size(); i++){
					list.add((Train) toUpdate.get(i));
				}
				
				
				Platform.runLater(()->{
					myController.displayChange(list);
					});
				
				Thread.sleep(1000);
			}
		} catch (EndGameException e){
				Platform.runLater(()->{
					myController.gameOver();
				});
				stopMe();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		start();
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
