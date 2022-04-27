/*
 * DisplayCatalog Model that
 * displays books from database
 * into one table upon request.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.model;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import library.database.DatabaseHandler;

public class DisplayCatalogModel implements Initializable {

	//DatabaseHandler variable
    DatabaseHandler databaseHandler;
    
    //observable list used to store the book data
    public static ObservableList<DisplayCatalogModel> list = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
	
	//initialize string variables for book components
	String author;
	String title;
	String bookID;
	String status;
	
	//constructor
	public DisplayCatalogModel(String author, String title, String bookID, String status) {
    	
    	this.author = author;
    	this.title = title;
    	this.bookID = bookID;
    	this.status = status;
    }
    	
	//getters and setters
    public String getAuthor() {
    		
   		return author;
   	}
    	
    public void setAuthor(String author) {
   		
   		this.author = author;
    }
    
    public String getTitle() {
		
   		return title;
   	}
    	
    public void setTitle(String title) {
   		
   		this.title = title;
    }
    
    public String getBookID() {
		
   		return bookID;
   	}
    	
    public void setBookID(String bookID) {
   		
   		this.bookID = bookID;
    }
    
    public String getStatus() {
		
   		return status;
   	}
    	
    public void setStatus(String status) {
   		
   		this.status = status;
    }

    //sends query to db and returns results, storing in a list
	public static void DisplayBooks () {
		
		//refreshes list/table view each time user clicks Display
		list.removeAll(list);
    	
		//creates a new databaseHandler and sends query to db.
    	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    	String query = "SELECT * FROM Books";
		java.sql.ResultSet rs = databaseHandler.performQuery(query);
		
		try {
			
			//while loop that goes through each row in the table and returns values
			while(rs.next()) {
				
				//returned values are added to list
				list.add(new DisplayCatalogModel(rs.getString("author"),
						rs.getString("title"),
						rs.getString("bookID"),
						rs.getString("status")));
			}
		}
		
		catch(SQLException sqlException) {
		
			sqlException.printStackTrace();
		}
	}
}
