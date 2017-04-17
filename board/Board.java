package board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardelements.*;
import main.EndGameException;
import update.Timer;

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
		
		toStringList=new HashMap<String, HashMap<String, ? extends BoardElement>>();
		toStringList.put("station", stationList);
		toStringList.put("rail", railList);
		toStringList.put("switch", switchList);
		toStringList.put("entrypoint", entryPointList);
		toStringList.put("tunnelopp", tunnelOpportunityList);
	}
	
	public int calculatePoints(){
		int point = 0;
		for (int i=0; i<stationList.size(); i++){
			point+=stationList.get(i).getAllPoints();
		}
		return point;
	}
	
	public void buildTunnel(TunnelOpportunity t1, TunnelOpportunity t2) {
		tunnel=new Tunnel(10);
		tunnel.setEnds(t1, t2);
	}
	
	public void buildTunnel(String t1id, String t2id) {
		TunnelOpportunity t1=tunnelOpportunityList.get(t1id);
		TunnelOpportunity t2=tunnelOpportunityList.get(t2id);
		if (t1!=null && t2!=null)
			buildTunnel(t1,t2);
	}
	
	public void destroyTunnel() {
		tunnel.destroy();
	}
	
	public void list(PrintStream ps, String type) {
		if (toStringList.get(type)==null)
			ps.println("invalid command");
		else toStringList.get(type).forEach((s, b)->ps.println(type+" "+s+" "+b.toString()));
	}	
	
	public void getNextTrain() throws EndGameException{
		for (EntryPoint ep: entryPointList.values()) {
			ep.getNextTrain();
		}
	}
	
	public void setRandomness(boolean r){
		for (EntryPoint ep: entryPointList.values()){
			ep.setRandomGeneration(r);
		}
		for (Station st: stationList.values()){
			st.setRandomness(r);
			
		}
	}
	
	public void setPassengerGetOn(boolean r){
		for (Station st: stationList.values()){
			st.setPassengerGetOn(r);			
		}
	}
	
	public void setAllTimers(Timer t) {
		for (EntryPoint ep: entryPointList.values()){
			ep.setTimer(t);
		}
	}
	
	public void changeSwitch(String id) {
		if (switchList.get(id)!=null)
			switchList.get(id).changeDir();
	}
}
