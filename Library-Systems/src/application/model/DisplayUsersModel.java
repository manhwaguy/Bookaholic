/*
 * DisplayUsers Model that
 * displays a table of all
 * students in the Students
 * table.
 * 
 * Author: Melwyn Cespedes
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

public class DisplayUsersModel implements Initializable {

	//DatabaseHandler variable
    DatabaseHandler databaseHandler;
    public static ObservableList<DisplayUsersModel> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
	}
    
    //string variables for student information
    String email;
    String studentID;
    String classYear;
    
    //constructor
    public DisplayUsersModel(String email, String studentID, String classYear) {
    	
    	this.email = email;
    	this.studentID = studentID;
    	this.classYear = classYear;
    }
    
    //getters & setters
    public String getEmail() {
		
   		return email;
   	}
    	
    public void setEmail(String email) {
   		
   		this.email = email;
    }
    
    public String getStudentID() {
		
   		return studentID;
   	}
    	
    public void setStudentID(String studentID) {
   		
   		this.studentID = studentID;
    }
    
    public String getClassYear() {
		
   		return classYear;
   	}
    	
    public void setClassYear(String classYear) {
   		
   		this.classYear = classYear;
    }
    
    //sends query to db and returns results, put into a list
    public static void DisplayStudents () {
    	
    	//clears list/tableview each time Display is clicked, to prevent repeats
    	list.removeAll(list);
    	
    	//creates new databaseHandler, and sends query to db
    	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
		String query = "SELECT * FROM Students";
		ResultSet rs = databaseHandler.performQuery(query);
		
		try {
			
			//while loop that goes through each row and returns values
			while(rs.next()) {
				
				//returned values are added to list
				list.add(new DisplayUsersModel(rs.getString("email"), rs.getString("studentID"), rs.getString("classYear")));
			}
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
		}
    }
}
