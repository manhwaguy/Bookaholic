/*
 * AddEmployeeModel Class that adds
 * an employee account to the Employees
 * table in the database
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.database.DatabaseHandler;

public class AddEmployeeModel implements Initializable {
	
	//initializes DatabaseHandler variable
	DatabaseHandler databaseHandler;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}

	/*
	 * Method that adds a new
	 * Employee to Database
	 * 
	 */
	
	public static void AddEmployee(String username, String password) throws IOException {
		
		/*
		 * Alerts that check for empty fields,
		 * username display format, and password
		 * length minimum.
		 * 
		 */
		
		if(username.isEmpty() || password.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return;
    	}
		
		if(!(Pattern.matches("[a-z]{3}[0-9]{3}", username))) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Invalid entry. Username must use format abc123.");
			alert.showAndWait();
			return;
		}
		
		if(!((password.length() >= 8))) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Password must be at least 8 characters. Please try again.");
			alert.showAndWait();
			return;
		}

		//password input is encrypted and that string value is what is saved in the database
		byte[] encryptedBytes = password.getBytes(StandardCharsets.UTF_8);
		String encodedBytes = Base64.getEncoder().encodeToString(encryptedBytes);
		
		//string used to send a query command to the database
		String query = "INSERT INTO Employees VALUES ('"+username+"', '"+encodedBytes+"')";
 
			//calls on the performAction method in DatabaseHandler Class
			try {
				if(DatabaseHandler.databaseHandler.performAction(query)) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText(username + " added to the Database.");
					alert.showAndWait();
				}
				
				else {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Username already taken. Please try again.");
					alert.showAndWait();
				}
			}
			
			catch (Exception e) {

				e.printStackTrace();
			}			
	}
}
