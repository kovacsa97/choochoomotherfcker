package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import board.Board;
import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parser.ChooChooParser;
import trainelements.Train;
import update.Timer;
import view.View;
import visuals.*;
//import visuals.Point;
//import visuals.StaticVisual;
//import visuals.StationVisual;
//import visuals.SwitchVisual;


public class Main extends Application {
	private Controller c;
	private View w;
	private Timer t;
	
	public void test() {
		
		Board b = new ChooChooParser().parse("maps/map1.xml");
		c.setBoard(b);
		c.displayBoard("maps/map1_visual.xml");
		c.displayChange();
		c.updateInfo();
		//t.start();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w=new View();
		c=new Controller(w);
		w.setController(c);
		
		////////////////////
		t = new Timer();
		t.setController(c);
		ArrayList<Train> list = new ArrayList<Train>();
		for (int i=1;i<t.getList().size(); i++)
			list.add((Train) t.getList().get(i));
		c.setTrains(list);
		///////////////////
		

		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(w);
		primaryStage.setScene(new Scene((Parent)l.load()));
		primaryStage.show();
		
		//Thread.sleep(10000);
		
		
		//w.openHandler();
		
		test();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
