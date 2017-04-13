package update;

import main.EndGameException;

/**
 * Updateable interface
 *
 */
public interface Updateable {
	/**
	 * @throws EndGameException
	 * Az Updateable interface-t megvalosito objektumok override-oljak a z update() fuggvenyt
	 */
	public void update() throws EndGameException;
}
