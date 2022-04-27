/*
 * Main.java class that initializes the application
 * and opens the Main.fxml Window.
 * 
 * Author: Melwyn B. Cespedes
 * 
 * CS 3443 - Spring 2022
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));
			Scene scene = new Scene(root,600,425);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//sets title for all windows while application is running
			primaryStage.setTitle("Library Systems V1.5");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
