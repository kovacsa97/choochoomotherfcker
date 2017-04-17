package update;

import main.EndGameException;

/**
 * A jatek onalloan mukodo elemeit fogja ossze, 
 * a kozos funkcionalitas - reagalas az ido mulasara - alapjan. 
 */
public interface Updateable {
	/**
	 * Az Updateable interface-t megvalosito objektumok override-oljak a z update() fuggvenyt
	 * @throws EndGameException
	 */
	public void update() throws EndGameException;
}
