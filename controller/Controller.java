package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
import javafx.application.Platform;
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
	private Switch selectedSwitch = null;
	private TunnelOpportunity selectedTo = null;
	
	public Controller( View v ){
		myView = v;
	}
	
	public boolean executeSwitch(){
		selectedSwitch.changeDir();
		return true;
	}
	
	public boolean executeTunnel(String id1, String id2){
		TunnelOpportunity to1 = board.getTunnelOpportunityList().get(id1);
		TunnelOpportunity to2 = board.getTunnelOpportunityList().get(id2);
		Tunnel t = board.getTunnel();
		t.setEnds(to1, to2);
		return true;
	}
	
	public boolean executeSetDrivingForce(int df){
		selectedTrain.setDrivingForce(df);
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
		ArrayList<Train> list = new ArrayList<Train>();
		for (int i=1;i<t.getList().size(); i++)
			list.add((Train) t.getList().get(i));
		this.setTrains(list);
		Thread thtimer = new Thread(t);
		thtimer.start();
	}
	
	public void displayChange(ArrayList<Train> list){
		this.setTrains(list);
		boolean exists = false;
		for (Train t : allTrain){
			exists = false;
			for(DynamicVisual d : dynamicVisuals){
				if(d.getId().equals(t.getId())){
					exists = true;
					break;
				}
			}
			if (!exists){
				ArrayList<Point> l = new ArrayList<Point>();
				l.add(new Point(20,20));
				l.add(new Point(10,20));
				TrainVisual tv = new TrainVisual(new Point(0,0), new Point(0,0), t.getId(), l);
				dynamicVisuals.add(tv);
				updateInfo();
			}
		}
		
		
		
		for(DynamicVisual dv : dynamicVisuals){
			dv.update(board, allTrain, this);
		}
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
		
				
		// TunnelOpportunity lista
		TreeItem<String> toroot=new TreeItem<String>("Tunnel opportunities");
		for (Entry<String, TunnelOpportunity> entry : board.getTunnelOpportunityList().entrySet()) {
			TunnelOpportunity to = entry.getValue();
			TreeItem<String> todata=new TreeItem<String>(to.getId());
			toroot.getChildren().add(todata);
		}
		root.getChildren().add(toroot);
				
		// Switch lista
		TreeItem<String> switchroot=new TreeItem<String>("Switches");
		for (Entry<String, Switch> entry : board.getSwitchList().entrySet()) {
			Switch s = entry.getValue();
			TreeItem<String> switchdata=new TreeItem<String>(s.getId());
			switchroot.getChildren().add(switchdata);
		}
		root.getChildren().add(switchroot);
		
		myView.updateTreeView(root);
	}
	
	
	/**
	 * kezeli az uj elem kivalasztasat az informacios listabol
	 * @param selectedItem a kivalasztott elem
	 */
	public void selectTreeItem(TreeItem<String> selectedItem) {
		myView.setControlType(ControlType.None);

		if (selectedItem.getParent().getValue().equals("root")){
			return;
		}
		if (selectedItem.getParent().getValue().equals("Trains")){
			for(Train t : allTrain)
				if (t.getId().equals(selectedItem.getValue())) {
					myView.setControlType(ControlType.Train);
					selectedTrain=t;
					for (DynamicVisual d : dynamicVisuals){
						 d.setActivation(false);
					 }
					for (DynamicVisual d : dynamicVisuals){
						if(d.getId().equals(selectedTrain.getId())){
							d.setActivation(true);
							break;
						}
					}
					return;
				}
		}
		
		if (selectedItem.getParent().getParent().getValue()=="Trains"){
			for (Train t : allTrain)
				if (t.getId()==selectedItem.getParent().getValue()) {
					myView.setControlType(ControlType.Train);
					selectedTrain=t;
					for (DynamicVisual d : dynamicVisuals){
						 d.setActivation(false);
					 }
					for (DynamicVisual d : dynamicVisuals){
						if(d.getId().equals(selectedTrain.getId())){
							d.setActivation(true);
							break;
						}
					}
					return;
				}
		}
		
		if(selectedItem.getParent().getValue().equals("Switches")){
			for (Entry<String, Switch> entry : board.getSwitchList().entrySet()) {
				 if (entry.getValue().getId().equals(selectedItem.getValue())){
					 selectedSwitch = entry.getValue();
					 myView.setControlType(ControlType.Switch);
					 for (DynamicVisual d : dynamicVisuals){
						 d.setActivation(false);
					 }
					 for (DynamicVisual d : dynamicVisuals){
						 if (d.getId().equals(selectedSwitch.getId())){
							d.setActivation(true);
							break;
						 }
					 }
					 return;
				 }
			}
		}
		
		if(selectedItem.getParent().getValue().equals("Tunnel opportunities")){
			for (Entry<String, TunnelOpportunity> entry : board.getTunnelOpportunityList().entrySet()) {
				 if (entry.getValue().getId().equals(selectedItem.getValue())){
					 selectedTo = entry.getValue();
					 myView.setControlType(ControlType.TunnelOpp);
					 for (DynamicVisual d : dynamicVisuals){
						 d.setActivation(false);
					 }
					 for (DynamicVisual d : dynamicVisuals){
						 if (d.getId().equals(selectedTo.getId())){
							d.setActivation(true);
							break;
						 }
					 }
					 return;
				 }
			}
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
        			staticVisuals.add(new StationVisual(sp, ep, id, board.getStationList().get(id).getColor()));
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
	
	public void setTrains(ArrayList<Train> l ){
		allTrain = l;
	}
}
