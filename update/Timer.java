package update;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Timer {
	
	private boolean game = false;
	private List<Updateable> toUpdate = new ArrayList<Updateable>();
	
	public void registerElement(Updateable element){
		toUpdate.add(element);
	}
	
	public void start(){
		game = true;
		try{
			while (game){
				int size = toUpdate.size();
				for (int i=0; i<size; i++){
					toUpdate.get(i).update();
				}
				Thread.sleep(300);
			}
		} catch (Exception e){
			if (e.getMessage() == "Game Over"){
				System.out.println(e.getStackTrace() + " GAME OVER");
				stop();
			}
		}
	}
	
	public void stop(){
		game = false;
	}
}
