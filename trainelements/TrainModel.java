package trainelements;

import java.util.ArrayList;

/**
 * Vonat modelljet reprezentalo osztaly
 */
public class TrainModel {
	public String id;
	public int defDrivingForce;
	public int countOfWagons;
	public ArrayList<String> type = new ArrayList<String>();
	public ArrayList<Integer> color = new ArrayList<Integer>();
	public ArrayList<Integer> passCount = new ArrayList<Integer>();
}
