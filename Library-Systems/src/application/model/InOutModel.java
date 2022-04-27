/*
 * InOut Model that allows
 * for checking in or out
 * of books.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.database.DatabaseHandler;

public class InOutModel {

	//initializes DatabaseHandler variable
	DatabaseHandler databaseHandler;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//establishes a db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
	
	/*
	 * Checks out a book
	 * after user inputs
	 * title and ID
	 */
	
	public static void CheckOut(String title, String bookID) throws IOException, SQLException {
		
		//alert is sent if one or more fields is empty
		if(title.isEmpty() || bookID.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return;
    	}
		
		//query as a string, that will be sent to the db
		String query = "UPDATE Books SET status = 'OUT' WHERE bookID = ";
		query = query + "'"+bookID+"'";
		
		//calls on performAction method from DatabaseHandler Class
		if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText(title + " has been checked out.");
    		alert.showAndWait();
    	}
    	
    	else {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Could not check out book. Please try again.");
    		alert.showAndWait();
    	}
	}
	
	/*
	 * checks in a book
	 * after user inputs
	 * title and ID
	 * 
	 */
	
	public static void CheckIn(String title, String bookID) throws IOException, SQLException {
		
		
		//prompts an alert if 1 or both fields are empty.
		if(title.isEmpty() || bookID.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return;
    	}
		
		//query as a string, that will be sent to db
    	String query = "UPDATE Books SET status = 'IN' WHERE bookID = ";
		query = query + "'"+bookID+"'";
    	
		//calls performAction method from DatabaseHandler Class
    	if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText(title +" has been returned.");
    		alert.showAndWait();
    	}
    	
    	else {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Could not check out book. Please try again.");
    		alert.showAndWait();
    	}
	}
}