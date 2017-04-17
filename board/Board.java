package board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardelements.*;
import main.EndGameException;
import update.Timer;

/**
 * A palya elemeinek tarolasaert felelos. Rajta keresztul valosul meg az alagutak kezelese. 
 * Ki tudja szamitani a jatekos pontjait az �llomasok segitsegevel.
 */
public class Board {
	
	/**
	 * Station-oket tartalmaz� lista
	 */
	private HashMap<String,Station> stationList;
	/**
	 * TunnelOpportunity-ket tartalmazo lista
	 */
	private HashMap<String,TunnelOpportunity> tunnelOpportunityList ;
	/**
	 * Switch-eket tartalmazo lista
	 */
	private HashMap<String,Switch> switchList;
	/**
	 * Rail-eket tartalmazo lista
	 */
	private HashMap<String,Rail> railList;
	/**
	 * entryPointokat tartalmazo lista
	 */
	private HashMap<String,EntryPoint> entryPointList;
	/**
	 * ToStringek listaja
	 */
	private HashMap<String,HashMap<String,? extends BoardElement>> toStringList;
	
	/**
	 * A palya Tunnelje
	 */
	private Tunnel tunnel;
	
	/**
	 * letrehozza a palyat, 
	 * eltarolja a palyahoz tartoza palyaelemeket
	 * @param sl
	 * @param sw
	 * @param to
	 * @param rl
	 * @param rp
	 * @param t
	 */
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
	
	/**
	 * a jatekos pontjait szamolja meg, �s ter vissza vele
	 * @return points
	 */
	public int calculatePoints(){
		int point = 0;
		for (int i=0; i<stationList.size(); i++){
			point+=stationList.get(i).getAllPoints();
		}
		return point;
	}
	
	/**
	 * Fel�pit egy alagutat a parameterkent kapott ket bej�rat kozott.
	 * @param t1
	 * @param t2

	 */
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
	
	/**
	 * Megszunteti az alagutat
	 */
	public void destroyTunnel() {
		tunnel.destroy();
	}
	
	/**
	 * kilistazza a palya elemeit
	 * @param ps
	 * @param type
	 */
	public void list(PrintStream ps, String type) {
		if (toStringList.get(type)==null)
			ps.println("invalid command");
		else toStringList.get(type).forEach((s, b)->ps.println(type+" "+s+" "+b.toString()));
	}	
	
	/**
	 * letrehozza a kovetkezo uj vonatot
	 * @throws EndGameException
	 */
	public void getNextTrain() throws EndGameException{
		for (EntryPoint ep: entryPointList.values()) {
			ep.getNextTrain();
		}
	}
	
	/**
	 * beallitja a randomness ertekeket
	 * @param r
	 */
	public void setRandomness(boolean r){
		for (EntryPoint ep: entryPointList.values()){
			ep.setRandomGeneration(r);
		}
		for (Station st: stationList.values()){
			st.setRandomness(r);
			
		}
	}
	
	/**
	 * engedelyezi az utasok leszallasat
	 * @param r
	 */
	public void setPassengerGetOn(boolean r){
		for (Station st: stationList.values()){
			st.setPassengerGetOn(r);			
		}
	}
	
	/**
	 * a t ertekkel beallitja a Timert minden entryPointnak
	 * @param t
	 */
	public void setAllTimers(Timer t) {
		for (EntryPoint ep: entryPointList.values()){
			ep.setTimer(t);
		}
	}
	
	/**
	 * A parameterkent kapott Switch iranyat valtja
	 * @param id
	 */
	public void changeSwitch(String id) {
		if (switchList.get(id)!=null)
			switchList.get(id).changeDir();
	}
}
