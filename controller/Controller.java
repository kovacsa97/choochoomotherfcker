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
import view.View;
import visuals.DynamicVisual;
import visuals.StaticVisual;

public class Controller {	
	private String fileToParse;
	private Board board;
	private ArrayList<Train> allTrain;
	private ArrayList<StaticVisual> staticVisuals;
	private ArrayList<DynamicVisual> dynamicVisuals;
	private View myView;
	
	public Controller(String filename, Board b, ArrayList<Train> t ){
		board = b;
		allTrain = t;
		fileToParse = filename;
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
	
	public void displayBoard(Board b){
		// TODO Board -> StaticVisual
	}
	
	public void DisplayChange(){
		// TODO dynamic elements -> DynamicVisual		
	}
	
	public TreeView updateInfo(){
		return null;
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
        		if(element.getNodeName().equals("rail")){
        			
        		}
        		else if(element.getNodeName().equals("tunnelopp")){
        			
        		}
        		else if(element.getNodeName().equals("station")){
        			
        		}
				else if(element.getNodeName().equals("switch")){
					
				}
				else if(element.getNodeName().equals("crossingrail")){
					
				}
				else if(element.getNodeName().equals("entrypoint")){
					
				}
				else if(element.getNodeName().equals("tunnel")){						
					
				}
        	}
        }
	}
}
