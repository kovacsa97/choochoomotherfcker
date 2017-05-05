package controller;

import java.util.ArrayList;

import board.Board;
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
	
	public void parse(){
		
	}
}
