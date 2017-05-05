package main;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	private Controller c;
	@Override
	public void start(Stage primaryStage) throws Exception {
		c=new Controller(null, null, null);
		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(c);
		primaryStage.setScene(new Scene((Parent)l.load()));
		c.DisplayChange();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
