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
import parser.ChooChooParser;
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
	
	
	public void test() {
		c.setBoard(new ChooChooParser().parse("assets/test_map1.xml"));
		c.displayBoard("assets/map_1_visual.xml");
		
		//c.updateInfo();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w=new View();
		c=new Controller(w);
		w.setController(c);
		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(w);
		primaryStage.setScene(new Scene((Parent)l.load()));
		primaryStage.show();
		
		
		//w.openHandler();
		
		test();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
