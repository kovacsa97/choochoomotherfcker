package main;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import visuals.DynamicVisual;
import visuals.Point;
import visuals.StaticVisual;
import visuals.StationVisual;
import visuals.SwitchVisual;


public class Main extends Application {
	private Controller c;
	private View w;
	
	
	public void test() {
		ArrayList<StaticVisual> l=new ArrayList<StaticVisual>();
		l.add(new StationVisual(new Point(10,20), new Point(120,130), "fuck_u"));
		w.initBoard(l);
		
		ArrayList<DynamicVisual> dl=new ArrayList<DynamicVisual>();
		dl.add(new SwitchVisual(new Point(100,130), new Point(130,100), new Point(130,160), "TheUltimateSwitch"));
		w.update(dl);
		
		c.updateInfo();
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
