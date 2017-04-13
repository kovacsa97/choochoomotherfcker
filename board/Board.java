package board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import boardelements.*;

public class Board {
	
	private HashMap<String,Station> stationList;
	private HashMap<String,TunnelOpportunity> tunnelOpportunityList ;
	private HashMap<String,Switch> switchList;
	private HashMap<String,Rail> railList;
	private HashMap<String,EntryPoint> entryPointList;
	private HashMap<String,HashMap<String,? extends BoardElement>> toStringList;
	
	private Tunnel tunnel;
	
	public Board(HashMap<String,Station> sl, 
			HashMap<String,Switch> sw, 
			HashMap<String,TunnelOpportunity> to, 
			HashMap<String,Rail> rl,
			HashMap<String,EntryPoint> rp,
			Tunnel t){
		railList = rl;
		entryPointList = rp;
		stationList = sl;
		tunnelOpportunityList = to;
		switchList = sw;
		stationList = sl;
		tunnel = t;
	}
	
	public int calculatePoints(){
		int point = 0;
		for (int i=0; i<stationList.size(); i++){
			point+=stationList.get(i).getAllPoints();
		}
		return point;
	}
	
	public void buildTunnel(TunnelOpportunity t1, TunnelOpportunity t2) {
		tunnel.setEnds(t1, t2);
	}
	
	public void buildTunnel(String t1id, String t2id) {
		buildTunnel(tunnelOpportunityList.get(t1id),tunnelOpportunityList.get(t2id));
	}
	
	public void destroyTunnel() {
		tunnel.destroy();
	}
	
	public void list(PrintStream ps, String type) {
		toStringList.get(type).forEach((s, b)->ps.println(type+" "+s+" "+b.toString()));
	}	
	
	public void getNextTrain(){
		for (EntryPoint ep: entryPointList.values()){
			ep.getNextTrain();
		}
	}
}
