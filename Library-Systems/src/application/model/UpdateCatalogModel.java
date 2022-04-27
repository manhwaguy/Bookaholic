/*
 * UpdateCatalog Model allows
 * for the addition or deletion
 * of a book in the Books table.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.database.DatabaseHandler;

public class UpdateCatalogModel implements Initializable {

	//initializes DatabaseHandler variable
	DatabaseHandler databaseHandler;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//established db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
	
	//method that adds a book and returns a string value
	public static String AddBook(String author, String title, String bookID, String status) throws SQLException {
		
		if(author.isEmpty() || title.isEmpty() || bookID.isEmpty() || status.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return null;
    	}
		
		//string query that takes in the user input values and sends an instruction to the db
		String query = "INSERT INTO Books VALUES ('"+author+"', '"+title+"', '"+bookID+"', '"+status+"')";
    	
		//calls performAction method in DatabaseHandler Class
    	if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		String added = "added";
    		return added;
    	}
    	
    	else {
    		
    		String duplicate = "duplicate";
    		return duplicate;
    	}
	}
	
	//method that deletes a book and returns a string value
	public static String DeleteBook(String author, String title, String bookID, String status) throws IOException {
		
		if(author.isEmpty() || title.isEmpty() || bookID.isEmpty() || status.isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    		return null;
    	}
		
		//string query that takes in the user input values and sends an instruction to the db
    	String query = "DELETE FROM Books WHERE bookID = ";
		query = query + "'"+bookID+"'";
		
		//calls performAction method in DatabaseHandler Class
		if(DatabaseHandler.databaseHandler.performAction(query)) {
    		
    		String deleted = "deleted";
    		return deleted;
    	}
    	
		//this doesn't get reached: Bug
    	else {
    		
    		String unknown = "unknown";
    		return unknown;
    	}
	}
}
