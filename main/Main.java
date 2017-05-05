package main;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader l=new FXMLLoader(getClass().getResource("choochoo_ui.fxml"));
		l.setController(new Controller(null, null, null));
		primaryStage.setScene(new Scene((Parent)l.load()));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
