package update;

import main.EndGameException;

/**
 * A j�t�k �n�ll�an m�k�d� elemeit fogja �ssze, 
 * a k�z�s funkcionalit�s - reag�l�s az id� m�l�s�ra - alapj�n. 
 */
public interface Updateable {
	/**
	 * @throws EndGameException
	 * Az Updateable interface-t megvalosito objektumok override-oljak a z update() fuggvenyt
	 */
	public void update() throws EndGameException;
}
