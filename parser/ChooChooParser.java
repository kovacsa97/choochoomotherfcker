package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import board.Board;
import boardelements.BoardElement;
import boardelements.CrossingRail;
import boardelements.EntryPoint;
import boardelements.Rail;
import boardelements.Station;
import boardelements.Switch;
import boardelements.Tunnel;
import boardelements.TunnelOpportunity;
import trainelements.TrainModel;


/**
 * A test bemenetek beolvasasaert felelos osztaly.
 * @author AdamBelakovics
 *
 */
public class ChooChooParser {
	HashMap<String, BoardElement> id_to_BE = new HashMap<String, BoardElement>();
	HashMap<String, Rail> rails = new HashMap<String, Rail>(); 
	HashMap<String, TunnelOpportunity> tunnelopps = new HashMap<String, TunnelOpportunity>();
	HashMap<String, Station> stations = new HashMap<String, Station>();
	HashMap<String, Switch> switches = new HashMap<String, Switch>();
	EntryPoint entrypoint;
	Tunnel tunnel;
	
	/**
	 * A bemeneti fajl alapjan felepiti a palyat és visszater vele.
	 * @param filename A beolvasando fajl eleresi utvonala
	 * @return A bemenet alapjan felepitett palya a vonatokkal.
	 */
	public Board parse(String filename){
		try{
			File inputFile = new File(filename);
	        DocumentBuilderFactory dbFactory 
	           = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        Node map = doc.getElementsByTagName("map").item(0);
	        for(Node element = map.getFirstChild(); element != null; element = element.getNextSibling()){
	        	if (element.getNodeType() == Node.ELEMENT_NODE) {
	        		BoardElement b = null;
	        		String id = element.getAttributes().getNamedItem("id").getNodeValue();
	        		if(element.getNodeName().equals("rail")){
	        			Rail r = new Rail(20);
	        			r.setId(id);
	        			b = r;
	        			rails.put(id, r);
	        		}
	        		else if(element.getNodeName().equals("tunnelopp")){
	        			TunnelOpportunity t = new TunnelOpportunity(0);
	        			t.setId(id);
	        			b = t;
	        			tunnelopps.put(id , t);
	        		}
	        		else if(element.getNodeName().equals("station")){
	        			Station s = new Station(Integer.parseInt(element.getAttributes().getNamedItem("color").getNodeValue()), 20);
	        			s.setId(id);
	        			b = s;
	        			stations.put(id, s);
	        		}
					else if(element.getNodeName().equals("switch")){
						Switch s = new Switch(0);
						s.setId(id);
						b = s;
						switches.put(id, s);
					}
					else if(element.getNodeName().equals("crossingrail")){
						CrossingRail cr = new CrossingRail(20);
						cr.setId(id);
						b = cr;
						rails.put(id, new CrossingRail(20));
					}
					else if(element.getNodeName().equals("entrypoint")){
						entrypoint = new EntryPoint(Integer.parseInt(element.getAttributes().getNamedItem("defWaitTime").getNodeValue()), 20);
						entrypoint.setId(id);
						b = entrypoint;
					}
					else if(element.getNodeName().equals("tunnel")){						
						tunnel = new Tunnel(20);
						tunnel.setId(id);
						b = tunnel;
					}
	        		if(b == null)
	        			throw new Exception("Invalid BoardElement");
        			id_to_BE.put(id, b);
	        	}
	        }
	        for(Node element = map.getFirstChild(); element != null; element = element.getNextSibling()){
	        	if (element.getNodeType() == Node.ELEMENT_NODE) {
	        		String id = element.getAttributes().getNamedItem("id").getNodeValue();
	        		if(element.getNodeName().equals("rail")){
	        			Rail r = rails.get(id);
	        			r.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("prevId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("nextId").getNodeValue()));
	        		}
	        		else if(element.getNodeName().equals("tunnelopp")){
	        			TunnelOpportunity t = tunnelopps.get(id);
	        			t.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("inId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("outId").getNodeValue()));
	        		}
	        		else if(element.getNodeName().equals("station")){
	        			Station s = stations.get(id);
	        			s.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("prevId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("nextId").getNodeValue()));
	        			s.setNewPassengerCount(Integer.parseInt(element.getAttributes().getNamedItem("newPassengerCount").getNodeValue()));
	        			s.setNewPassengerProbability(Double.parseDouble(element.getAttributes().getNamedItem("newPassengerProbability").getNodeValue()));
	        		}
					else if(element.getNodeName().equals("switch")){
						Switch s = switches.get(id);
						s.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("inId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("currentOutId").getNodeValue()));
						s.addExit(id_to_BE.get(element.getAttributes().getNamedItem("otherOutId").getNodeValue()));
					}
					else if(element.getNodeName().equals("crossingrail")){
						CrossingRail cr = (CrossingRail) rails.get(id);
						cr.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("prevId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("nextId").getNodeValue()));
						cr.setOtherRail(rails.get(element.getAttributes().getNamedItem("otherRailId").getNodeValue()));
					}
					else if(element.getNodeName().equals("entrypoint")){
						entrypoint.setEnds(null, id_to_BE.get(element.getAttributes().getNamedItem("nextid").getNodeValue()));
					}
					else if(element.getNodeName().equals("tunnel")){						
						tunnel.setEnds(id_to_BE.get(element.getAttributes().getNamedItem("defInId").getNodeValue()), id_to_BE.get(element.getAttributes().getNamedItem("defOutId").getNodeValue()));
					}
	        	}
	        }	        
	        NodeList trains = doc.getElementsByTagName("train");
	        for(Node train = trains.item(0); train != null; train = train.getNextSibling()){
	        	if (train.getNodeType() == Node.ELEMENT_NODE) {
		        	NodeList trainElements = train.getChildNodes();
		        	TrainModel tm = new TrainModel();
		        	tm.id =  train.getAttributes().getNamedItem("id").getNodeValue();
		        	for(Node trainElement = trainElements.item(0); trainElement != null; trainElement = trainElement.getNextSibling()){
		        		if(trainElement.getNodeName().equals("locomotive")){
		        			tm.defDrivingForce = Integer.parseInt(trainElement.getAttributes().getNamedItem("defDrivingForce").getNodeValue());
		        		}
		        		if(trainElement.getNodeName().contains("wagon")){
		        			tm.countOfWagons++;
		        			tm.type.add(trainElement.getNodeName());
		        			tm.color.add(Integer.parseInt(trainElement.getAttributes().getNamedItem("color").getNodeValue()));
		        			tm.passCount.add(Integer.parseInt(trainElement.getAttributes().getNamedItem("passengerCount").getNodeValue())); 
		        		}
		        	}
		        	entrypoint.addTrain(tm);
		        }
	        }
	    } catch(FileNotFoundException f){
	    	System.out.println("Invalid File Path. try assets/*");
	    	return null;
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("PARSER ERROR!");
		}
		HashMap<String, EntryPoint> tmp = new HashMap<String, EntryPoint>();
        tmp.put(entrypoint.getId(), entrypoint);
        return new Board(stations, switches, tunnelopps, rails, tmp, tunnel);
	}
}
