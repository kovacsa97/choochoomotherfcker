package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import board.Board;
import boardelements.BoardElement;
import boardelements.CrossingRail;
import boardelements.EntryPoint;
import boardelements.Rail;
import boardelements.Station;
import boardelements.Switch;
import boardelements.Tunnel;
import boardelements.TunnelOpportunity;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import trainelements.Train;
import trainelements.Wagon;
import update.Timer;
import view.View;
import view.View.ControlType;
import visuals.*;

public class Controller {	
	private String fileToParse;
	private Board board;
	private ArrayList<Train> allTrain;
	private ArrayList<StaticVisual> staticVisuals = new ArrayList<>();
	private ArrayList<DynamicVisual> dynamicVisuals = new ArrayList<>();
	private View myView;
	
	private Train selectedTrain=null;
	
	public Controller( View v ){
		myView = v;
	}
	
	public boolean executeSwitch(String id){
		Switch s = board.getSwitchList().get(id);
		s.changeDir();
		return true;
	}
	
	public boolean executeTunnel(String id1, String id2){
		TunnelOpportunity to1 = board.getTunnelOpportunityList().get(id1);
		TunnelOpportunity to2 = board.getTunnelOpportunityList().get(id2);
		Tunnel t = board.getTunnel();
		t.setEnds(to1, to2);
		return true;
	}
	
	public boolean executeSetDrivingForce(String id, int df){
		Train train = null;
		for (Train t : allTrain){
			if (t.getId().equals(id)){
				train = t;
				break;
			}
		}
		train.setDrivingForce(df);
		return true;
	}
	
	public void displayBoard(String fileToParse){
		try {
			parse(fileToParse);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myView.initBoard(staticVisuals);
		Timer t = new Timer();
		t.setController(this);
		board.setAllTimers(t);
		t.start();
	}
	
	public void displayChange(){
		myView.update(dynamicVisuals);
	}
	
	/**
	 * Frissiti a view informacios treeviewjet a megfelelo elemekkel
	 */
	public void updateInfo(){
		TreeItem<String> root=new TreeItem<String>("root");
		
		// Train lista
		TreeItem<String> trainroot=new TreeItem<String>("Trains");
		for (Train item : allTrain){
			TreeItem<String> trainbase=new TreeItem<String>(item.getId());
			trainbase.getChildren().add(new TreeItem<String>("Locomotive (Driving power: "+item.getMyLocomotive().getEnginePower()+")"));
			for (Wagon w : item.getMyWagons())
				trainbase.getChildren().add(new TreeItem<String>("Wagon (Number of passengers: "+w.getNumberOfPassengers()+")"));
			trainroot.getChildren().add(trainbase);
			}
		root.getChildren().add(trainroot);
		
		// Rail lista
		TreeItem<String> railroot=new TreeItem<String>("Rails");
		root.getChildren().add(railroot);
		
		// Rail lista
		TreeItem<String> stationroot=new TreeItem<String>("Stations");
		root.getChildren().add(stationroot);
				
		// Rail lista
		TreeItem<String> toroot=new TreeItem<String>("Tunnel opportunities");
		root.getChildren().add(toroot);
				
		// Rail lista
		TreeItem<String> switchroot=new TreeItem<String>("Switches");
		root.getChildren().add(switchroot);
		
		myView.updateTreeView(root);
	}
	
	
	/**
	 * kezeli az uj elem kivalasztasat az informacios listabol
	 * @param selectedItem a kivalasztott elem
	 */
	public void selectTreeItem(TreeItem<String> selectedItem) {
		if (selectedItem.getParent().getValue()=="root")
			return;
		if (selectedItem.getParent().getValue()=="Trains")
			for(Train t : allTrain)
				if (t.getId()==selectedItem.getValue()) {
					myView.setControlType(ControlType.Train);
					selectedTrain=t;
					return;
				}
		if (selectedItem.getParent().getParent().getValue()=="Trains")
			for (Train t : allTrain)
				if (t.getId()==selectedItem.getParent().getValue()) {
					myView.setControlType(ControlType.Train);
					selectedTrain=t;
					return;
				}
	}
	
	public void parse(String filename) throws SAXException, IOException, ParserConfigurationException{
		File inputFile = new File(filename);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        ///Parsing Map
        
        Node map = doc.getElementsByTagName("map").item(0);
        for(Node element = map.getFirstChild(); element != null; element = element.getNextSibling()){
        	if (element.getNodeType() == Node.ELEMENT_NODE) {
        		String id = element.getAttributes().getNamedItem("id").getNodeValue();
        		Point sp = new Point(Integer.parseInt(element.getAttributes().getNamedItem("spx").getNodeValue()), Integer.parseInt(element.getAttributes().getNamedItem("spy").getNodeValue()));
        		Point ep = new Point(Integer.parseInt(element.getAttributes().getNamedItem("epx").getNodeValue()), Integer.parseInt(element.getAttributes().getNamedItem("epy").getNodeValue()));
        		if(element.getNodeName().equals("rail")){
        			staticVisuals.add(new RailVisual(sp, ep, id));
        		}
        		else if(element.getNodeName().equals("tunnelopp")){
        			dynamicVisuals.add(new TunnelOpportunityVisual(sp, ep, id));
        		}
        		else if(element.getNodeName().equals("station")){
        			staticVisuals.add(new StationVisual(sp, ep, id, color.Color.PINK));
        		}
				else if(element.getNodeName().equals("switch")){
					Point oe = new Point(Integer.parseInt(element.getAttributes().getNamedItem("oex").getNodeValue()), Integer.parseInt(element.getAttributes().getNamedItem("oey").getNodeValue()));
					dynamicVisuals.add(new SwitchVisual(sp, ep, oe, id));
				}
				else if(element.getNodeName().equals("crossingrail")){
					staticVisuals.add(new RailVisual(sp, ep, id));
				}
				else if(element.getNodeName().equals("entrypoint")){
					staticVisuals.add(new EntryPointVisual(sp, ep, id));
				}
				else if(element.getNodeName().equals("tunnel")){						
					dynamicVisuals.add(new TunnelVisual(sp, ep, id));
				}
        	}
        }
	}
	
	public void setBoard(Board b){
		board = b;
	}
}
