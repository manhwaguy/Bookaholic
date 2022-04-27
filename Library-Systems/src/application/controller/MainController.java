/*
 * Main Controller that interacts with
 * Main.fxml to deliver the scene's
 * functionality
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.model.VerifyLoginModel;

public class MainController {

    @FXML
    private Button createAccountButton;

    @FXML
    private Button loginButton;
    
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private AnchorPane welcomePane;
    
    @FXML
    private Button adminButton;
    
    /*
     * Logins a user in if the username/password
     * is already in the database. Does verify password
     * off encryption. 
     * 
     */
    
    @FXML
    void login(ActionEvent event) throws IOException, SQLException {
    	   	
    	//string variable used for return call from VerifyLogin method
    	String verified = null;
    	
    	//grabs username/password from user input
    	String username = userNameField.getText();
    	String password = passwordField.getText();
		
    	//calls VerifyLogin method from VerifyLoginModel and returns a string
    	verified = VerifyLoginModel.VerifyLogin(username, password);
    	
    	//if string return matches, user is taken to the System Interface window
		if(verified == "verified") {
			
			welcomePane = FXMLLoader.load(getClass().getResource("/application/view/SystemInterface.fxml"));
	    	Scene scene = new Scene(welcomePane,600,425);
	    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(scene);
	    	window.show();
		}
   
		//if string return matches, an error displays
		else if(verified == "notVerified") {
			
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("The username and password you entered do not match our records. Please try again.");
    		alert.showAndWait();
    		return;
		}
		
		else {
			
			return;
		}
    }

    /*
     * Takes users to the Create Account
     * window if Create Account button is
     * pressed.
     * 
     */
    
    @FXML
    void createAccount(ActionEvent event) throws IOException {
    	
    	welcomePane = FXMLLoader.load(getClass().getResource("/application/view/CreateAccount.fxml"));
    	Scene scene = new Scene(welcomePane,600,425);
    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }
    
    /*
     * Takes user to admin window
     * if the right username and password
     * are inputed.
     * 
     */
    
    @FXML
    void admin(ActionEvent event) throws IOException {
    	
    	//grabs username and password from user input
    	String username = userNameField.getText();
    	String password = passwordField.getText();
    	
    	//Don't recommend doing this in the real world!!!, sets in-built admin credentials
    	String admin = "admin";
    	String notreallysecure = "notreallysecure";
    	
    	//if either text field is empty, error alert is thrown
    	if(username.isEmpty() || password.isEmpty()) {
    	
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("One or more fields are empty. Please try again.");
    		alert.showAndWait();
    	}
    	
    	//if username and password match, user is taken to admin page
    	else if(username.equals(admin) && password.equals(notreallysecure)) {

    		welcomePane = FXMLLoader.load(getClass().getResource("/application/view/Administrator.fxml"));
    		Scene scene = new Scene(welcomePane,600,425);
    		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		window.setScene(scene);
    		window.show();
    	}
    	
    	//if username and password don't match in-built credentials, error alert thrown
    	else {
    	
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("The username and password entered is not allowed to proceed.");
    		alert.showAndWait();
    	}
   }
}