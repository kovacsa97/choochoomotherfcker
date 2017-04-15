package parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import boardelements.BoardElement;
import boardelements.CrossingRail;
import boardelements.EntryPoint;
import boardelements.Rail;
import boardelements.Station;
import boardelements.Switch;
import boardelements.Tunnel;
import boardelements.TunnelOpportunity;
import trainelements.TrainModel;

public class ChooChooParser {
	HashMap<BoardElement, Node> allBEs = new HashMap<BoardElement, Node>();
	HashMap<Node, BoardElement> allNodes = new HashMap<Node, BoardElement>();
	HashMap<String, Rail> rails = new HashMap<String, Rail>(); 
	HashMap<String, TunnelOpportunity> tunnelopps = new HashMap<String, TunnelOpportunity>();
	HashMap<String, Station> stations = new HashMap<String, Station>();
	HashMap<String, Switch> switches = new HashMap<String, Switch>();
	EntryPoint entrypoint;
	Tunnel tunnel;
	
	public void parse(String filename){
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
	        		BoardElement b;
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
						entrypoint = new EntryPoint(Integer.parseInt(element.getAttributes().getNamedItem("defWaitTime").getNodeValue()), 20, null /*SCARY!!!*/);
						entrypoint.setId(id);
						b = entrypoint;
					}
					else if(element.getNodeName().equals("tunnel")){						
						tunnel = new Tunnel(20);
						tunnel.setId(id);
						b = tunnel;
					}
        			//allBEs.put(b, element);
        			//allNodes.put(element, b);
	        		
	        	}
	        }
	        for(Map.Entry<BoardElement, Node> BE : allBEs.entrySet()){
	        	//BE.getKey().setEnds(BE.getValue().getAttributes().getNamedItem("prevId"), BE.getValue().getAttributes().getNamedItem("nextId"));
	        }
	        /*for(Map.Entry<String, TunnelOpportunity> entry : tunnelopps.entrySet()){
	        	System.out.println(entry.getKey() + " "  + entry.getValue());
	        }
	        for(Map.Entry<String, Station> entry : stations.entrySet()){
	        	System.out.println(entry.getKey() + " "  + entry.getValue());
	        }
	        for(Map.Entry<String, Switch> entry : switches.entrySet()){
	        	System.out.println(entry.getKey() + " "  + entry.getValue());
	        }
	        System.out.println(entrypoint);
	        System.out.println(tunnel);*/
	        
	        NodeList trains = doc.getElementsByTagName("train");
	        for(Node train = trains.item(0); train != null; train = train.getNextSibling()){
	        	NodeList trainElements = train.getChildNodes();
	        	TrainModel tm = new TrainModel();
	        	for(Node trainElement = trainElements.item(0); trainElement != null; trainElement = trainElement.getNextSibling()){
	        		if(trainElement.getNodeName().equals("locomotive")){
	        			tm.defDrivingForce = Integer.parseInt(trainElement.getAttributes().getNamedItem("defDrivingForce").getNodeValue());
	        			System.out.println(trainElement.getNodeName() + " " + trainElement.getAttributes().getNamedItem("defDrivingForce"));
	        		}
	        		if(trainElement.getNodeName().contains("wagon")){
	        			tm.countOfWagons++;
	        			tm.type.add(trainElement.getNodeName());
	        			tm.color.add(Integer.parseInt(trainElement.getAttributes().getNamedItem("color").getNodeValue()));
	        			tm.passCount.add(Integer.parseInt(trainElement.getAttributes().getNamedItem("passengerCount").getNodeValue())); 
	        			System.out.println(trainElement.getNodeName() + " " + trainElement.getAttributes().getNamedItem("color"));
	        		}
	        	}
	        	entrypoint.addTrain(tm);
	        }
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("PARSER ERROR!!!!!!!!!!!!!!!!!! - szoljatok az Adamnak (a szebbnek)");
		}
	}
}
