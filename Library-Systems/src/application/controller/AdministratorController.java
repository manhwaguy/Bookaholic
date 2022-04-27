/*
 * Application Controller that's interacts
 * with the Application fxml to deliver the
 * Scene's functionality.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.DisplayEmployeeModel;
import application.model.RemoveEmployeeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.database.DatabaseHandler;

public class AdministratorController implements Initializable {

	/*
	 * Admin Pane and its
	 * Button
	 * 
	 */
	
	@FXML
    private AnchorPane adminPane;
	
	@FXML
    private Button exitButton;
	
	
	/*
	 * Update Staff Pane and
	 * all associated TextFields
	 * & Buttons
	 * 
	 */
	
	@FXML
    private AnchorPane updateStaffPane;
	
    @FXML
    private TextField usernameField;

    /*
     *Staff Display Pane and
     *all associate TableViews
     *& Buttons. 
     *
     */
    
    @FXML
    private AnchorPane staffDisplayPane;

    @FXML
    private Button displayEmployee;

    @FXML
    private TableView<DisplayEmployeeModel> staffTable;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<DisplayEmployeeModel, String> staffColumn;
    
    //DatabaseHandler variable
    DatabaseHandler databaseHandler;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
		
		//sets staffColum with properties pulled from username column in Employees Table
		staffColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
	}

    /*
     * When user clicks on Display button
     * a table with all staff usernames will
     * display.
     * 
     */
    
	@FXML
    void displayStaff(ActionEvent event) {

		//calls on DisplayStaff method from DisplayEmployeeModel Class
		DisplayEmployeeModel.DisplayStaff();
		
		//sets staffTable with populated list from DisplayEmployeeModel Class
    	staffTable.getItems().setAll(DisplayEmployeeModel.list);
    }

	/*
	 * Removes Staff based on username
	 * entered into text field.
	 * 
	 */
	
    @FXML
    void removeEmployee(ActionEvent event) throws SQLException {
    	
    	//grabs username from user input
    	String deleteUser = usernameField.getText();
        usernameField.setText("");
        
        //calls DeleteStaff method from RemoveEmployeeModel
        RemoveEmployeeModel.DeleteStaff(deleteUser);
        
        usernameField.setText("");
    }

    /*
     * When user clicks Exit button,
     * they are returned to the main
     * screen.
     * 
     */
    
    @FXML
    void returnHome(ActionEvent event) throws IOException {

    	adminPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));
    	Scene scene = new Scene(adminPane,600,425);
    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

}
