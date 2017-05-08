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
import view.View.ControlType;
import visuals.*;
//import visuals.Point;
//import visuals.StaticVisual;
//import visuals.StationVisual;
//import visuals.SwitchVisual;


public class Main extends Application {
	private Controller c;
	private View w;
	
	public void test() {
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w=new View();
		c=new Controller(w);
		w.setController(c);

		

		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(w);
		
		
		primaryStage.setScene(new Scene((Parent)l.load()));
		w.setControlType(ControlType.None);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
