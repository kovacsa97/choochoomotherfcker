package board;

import java.util.ArrayList;
import java.util.List;

import boardelements.*;

public class Board {
	
	private List<Station> stationList;
	private List<TunnelOpportunity> tunnelopportunityList ;
	private List<Switch> switchList;
	private Tunnel tunnel;
	
	public Board(List<Station> sl, List<Switch> sw, List<TunnelOpportunity> to, Tunnel t){
		stationList = sl;
		tunnelopportunityList = to;
		switchList = sw;
		tunnel = t;
	}
	
	public int calculatePoints(){
		int point = 0;
		for (int i=0; i<stationList.size(); i++){
			point+=stationList.get(i).getAllPoints();
		}
		return point;
	}
	
}
