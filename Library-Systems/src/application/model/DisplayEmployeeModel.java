/*
 * DisplayEmployee Model that displays
 * a table of all staff usernames in
 * the database table.
 * 
 */

package application.model;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import library.database.DatabaseHandler;

public class DisplayEmployeeModel implements Initializable {

	//DatabaseHandler variable
    DatabaseHandler databaseHandler;
    public static ObservableList<DisplayEmployeeModel> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
    
    //staff username string
    String username;
    
    //constructor
    public DisplayEmployeeModel(String username) {
    	
    	this.username = username;
    }
    	
    //getter & setter
    public String getUsername() {
    		
   		return username;
   	}
    	
    public void setUsername(String username) {
   		
   		this.username = username;
    }
    
    //sends query to db and returns results, put into a list
    public static void DisplayStaff () {
    	
    	//clears list/tableview each time Display is clicked, to prevent repeats
    	list.removeAll(list);
    	
    	//creates new databaseHandler, and sends query to db
    	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
		String query = "SELECT * FROM Employees";
		ResultSet rs = databaseHandler.performQuery(query);
		
		try {
			
			//while loop that goes through each row and returns values
			while(rs.next()) {
				
				//returned values are added to list
				list.add(new DisplayEmployeeModel(rs.getString("username")));
			}
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
		}
    }
}
