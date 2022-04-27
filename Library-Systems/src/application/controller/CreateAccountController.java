/*
 * CreateAccountController that interacts
 * with CreateAccount.fxml to deliver
 * Scene's functionality.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.database.DatabaseHandler;

import application.model.AddEmployeeModel;

public class CreateAccountController implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button homePageButton;
    
    @FXML
    private AnchorPane createAccountPane;
    
    //DatabaseHandler variable
    DatabaseHandler databaseHandler;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		
    	//establishes db connection
    	databaseHandler = DatabaseHandler.getInstance();
    }

   /*
    * Submits a username and password to the
    * Employees Table in the database when
    * Submit button is clicked.
    * 
    */
    
    @FXML
    void submit(ActionEvent event) throws IOException {
    	
    	//grabs username and password from user input
    	String username = userNameField.getText();
    	String password = passwordField.getText();
    	
    	//calls AddEmployee method from AddEmployeeModel class
	AddEmployeeModel.AddEmployee(username, password);
    	
	//clears text fields
    	userNameField.setText(""); 	
    	passwordField.setText(""); 
    }

    /*
     * Returns user to Main screen
     * when Home Page button is
     * clicked.
     * 
     */
    
    @FXML
    void home(ActionEvent event) throws IOException {

    	createAccountPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));
    	Scene scene = new Scene(createAccountPane,600,425);
    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }
}
