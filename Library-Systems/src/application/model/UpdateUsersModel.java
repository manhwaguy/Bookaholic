/*
 * UpdateUsers Model that
 * adds or deletes a user
 * into/from the Students
 * table.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.database.DatabaseHandler;

public class UpdateUsersModel {

	//initializes DatabaseHandler variable
	DatabaseHandler databaseHandler;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
	
	//method that adds a user and returns a string value
	public static String AddUser(String email, String id, String classYear) throws SQLException {
		
		if(email.isEmpty() || id.isEmpty() || classYear.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return null;
    	}
		
		//string query that takes in the user input values and sends an instruction to the db
		String query = "INSERT INTO Students VALUES ('"+email+"', '"+id+"', '"+classYear+"')";
		
		//calls performAction method in DatabaseHandler Class
		if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		String addedUser = "added";
    		return addedUser;
    	}
    	
    	else {
    		
    		String notAdded = "notAdded";
    		return notAdded;
    	}
	}
	
	//method that deletes a user and returns a string value
	public static String DeleteUser(String email, String id, String classYear) throws SQLException {
		
		if(email.isEmpty() || id.isEmpty() || classYear.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return null;
    	}
        
		//string query that takes in the user input values and sends an instruction to the db
        String query = "DELETE FROM Students WHERE studentID = ";
		query = query + "'"+id+"'";
		
		//calls performAction method in DatabaseHandler Class
		if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		String deleteUser = "deleted";
    		return deleteUser;
    	}
    	
    	else {
    		
    		String notFound = "notFound";
    		return notFound;
    	}
	}
}
