/*
 * RemoveEmployeeModel that
 * removes a staff username
 * from the Employees table
 * in the database.
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

public class RemoveEmployeeModel {

	//initializes DatabaseHandler variable
	DatabaseHandler databaseHandler;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
	
	/*
	 * Deletes a staff username
	 * based on user input
	 * 
	 */
	
	public static void DeleteStaff(String username) throws SQLException {
		
		//alert error is prompted if field is empty
		if(username.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Field is empty. Please try again.");
    		alert.showAndWait();
    		return;
    	}
        
		//a string query used to send instructions to db
        String query = "DELETE FROM Employees WHERE username = ";
		query = query + "'"+username+"'";
		
		//calls on performAction method in DatabaseHandler Class
		if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText(username + " has been removed from the Database.");
    		alert.showAndWait();
    		return;
    	}
    	
    	else {
    
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("No user by that name in the Database to delete.");
    		alert.showAndWait();
    		return;
    	}
	}
}
