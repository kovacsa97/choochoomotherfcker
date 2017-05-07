package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import visuals.*;
//import visuals.Point;
//import visuals.StaticVisual;
//import visuals.StationVisual;
//import visuals.SwitchVisual;


public class Main extends Application {
	private Controller c;
	private View w;
	
	
	public void test() {
//		ArrayList<StaticVisual> l=new ArrayList<StaticVisual>();
//		//l.add(new StationVisual(new Point(10,200), new Point(70,200), "fuck_u"));
//		w.initBoard(l);
//		
//		ArrayList<DynamicVisual> dl=new ArrayList<DynamicVisual>();
//		dl.add(new TunnelOpportunityVisual(new Point(40,200), new Point(700,150), "TheUltimateSwitch"));
//		w.update(dl);
		
			c.displayBoard("assets/map_1_visual.xml");
			c.displayChange();
			//c.updateInfo();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w=new View();
		c=new Controller(null, null, null,w);
		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(w);
		primaryStage.setScene(new Scene((Parent)l.load()));
		primaryStage.show();
		
		test();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
