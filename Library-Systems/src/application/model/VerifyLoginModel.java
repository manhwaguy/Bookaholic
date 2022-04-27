/*
 * VerifyLogin Model makes sure
 * that the username and password
 * entered match the record in the
 * Employees table.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.database.DatabaseHandler;

public class VerifyLoginModel {

	//verifies user login credentials and returns a string value
	public static String VerifyLogin (String username, String password) throws SQLException {
		
		//string used to store return value
		String encrypted = null;
		
		if(username.isEmpty() || password.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return null;
    	}
		
		//sends an alert if username does not match standards
		if(!(Pattern.matches("[a-z]{3}[0-9]{3}", username))) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Invalid entry. Username must use format abc123.");
			alert.showAndWait();
			return null;
		}
		
		//initializes new databaseHandler and string query that selects from specific username
		DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    	String query = "SELECT * FROM Employees WHERE username = ";
		query = query + "'"+username+"'";
		
		//calls on performQuery method from databaseHandler variable
		ResultSet rs = databaseHandler.performQuery(query);
		
		//while loop that searches for password matching the username id entered
		while(rs.next()) {

			//returns and stores the encrypted password
			encrypted = rs.getString("password");
		}
		
		//encryptes the password entered at login with the same scheme as the encrypted one in the db
		byte[] encryptedBytes = password.getBytes(StandardCharsets.UTF_8);
		String encodedBytes = Base64.getEncoder().encodeToString(encryptedBytes);
		
		//if both encrypted passwords match, allow access
		if(encodedBytes.equals(encrypted)) {
			
			String verified = "verified";
			return verified;
		}
		
		//if both encrypted passwords don't match, do not allow access
		else {
		
			String notVerified = "notVerified";
			return notVerified;
		}
	}
}
