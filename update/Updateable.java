package update;

import main.EndGameException;

/**
 * A játék önállóan mûködõ elemeit fogja össze, 
 * a közös funkcionalitás - reagálás az idõ múlására - alapján. 
 */
public interface Updateable {
	/**
	 * @throws EndGameException
	 * Az Updateable interface-t megvalosito objektumok override-oljak a z update() fuggvenyt
	 */
	public void update() throws EndGameException;
}
