package visuals;

import java.util.ArrayList;

import board.Board;
import controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import trainelements.Train;

/**
 * Dinamikus megjelenitok absztrakt ososztalya
 *
 */
public abstract class DynamicVisual {
	
	/**
	 * Kezdopozicio a kepernyon
	 */
	protected Point startPos;
	/**
	 * Vegpozicio a kepernyon
	 */
	protected Point endPos;
	/**
	 * Azonosiot
	 */
	private String id;
	/**
	 * Jeloli, hogy ki van-e jelolve az objektum
	 */
	protected boolean isActive = false;
	
	/**
	 * Kirajzolas absztrakt rajzolo fuggvenye
	 * @param c kirajzolasi felulet
	 */
	public abstract void draw(GraphicsContext c);
	public DynamicVisual(Point sp, Point ep, String id){
		startPos = sp;
		endPos = ep;
		this.id = id;
	}
	
	/**
	 * Beallitja a vegpontokat
	 * @param sp kezdopont
	 * @param ep vegpont
	 */
	public void setPoints(Point sp, Point ep){
		startPos = sp;
		endPos = ep;
	}
	/**
	 * beallitja, hogy ki van-e jelolve az adott elem
	 * @param b logikai valtozo, jeloli, hogy ki van-e jelolve
	 */
	public void setActivation(boolean b){
		isActive = b;
	}
	
	/**
	 * frissiti a visual megjeleniteset a megadott parameterek segitsegevel
	 * @param board aktualis palya
	 * @param trains vonatok listaja
	 * @param c az aktualis controller
	 */
	public void update(Board board, ArrayList<Train> trains, Controller c) {
		System.out.println("ABSTRACT BASE CLASS!!!");
	}
	/**
	 * @return visszaadja az azonositot
	 */
	public String getId() {
		return id;
	}
}
