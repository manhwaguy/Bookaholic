/*
 * System Interface Controller that
 * interacts with SystemInterface.fxml
 * to deliver the scene's functionality.
 * 
 * Author: Melwyn Cespedes
 * 
 */

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.database.DatabaseHandler;
import application.model.DisplayCatalogModel;
import application.model.DisplayUsersModel;
import application.model.InOutModel;
import application.model.UpdateCatalogModel;
import application.model.UpdateUsersModel;

public class SystemInterfaceController implements Initializable {

	/*
	 * Primary AnchorPane for SystemInterface FXML
	 * file with Exit Button
	 */
	
	@FXML
    private AnchorPane systemPane;
	
	@FXML
    private Button exitButton;

    /*
     * Check Out Tab Pane and all
     * associated TextFields &
     * Buttons.
     */
    
    @FXML
    private AnchorPane checkOutPane;
    
    @FXML
    private TextField titleCheckOutField;
    
    @FXML
    private TextField bookIDCheckOutField;
    
    @FXML
    private Button checkOutButton;

    /*
     * Return Book Tab Pane and all
     * associated TextFields &
     * Buttons.
     */
    
    @FXML
    private AnchorPane returnBookPane;

    @FXML
    private Button returnBookButton;
    
    @FXML
    private TextField titleReturnField;
    
    @FXML
    private TextField bookIDReturnField;

    /*
     * Catalog Tab Pane and all
     * associated Tables, Columns, 
     * & Buttons.
     */
    
    @FXML
    private AnchorPane catalogPane;
    
    @FXML
    private TableView<DisplayCatalogModel> tableDisplay;
    
    @FXML
    private TableColumn<DisplayCatalogModel, String> titleColumn;

    @FXML
    private TableColumn<DisplayCatalogModel, String> authorColumn;
    
    @FXML
    private TableColumn<DisplayCatalogModel, String> bookIDColumn;
    
    @FXML
    private TableColumn<DisplayCatalogModel, String> statusColumn;
    
    @FXML
    private Button displayBooksButton;


    /*
     * Users Tab Pane and all
     * associated Tables, Columns,
     * & Buttons.
     */
    
    @FXML
    private AnchorPane usersPane;
    
    @FXML
    private Button displayStudentButton;
    
    @FXML
    private TableView<DisplayUsersModel> studentTable;
    
    @FXML
    private TableColumn<DisplayUsersModel, String> classYearColumn;

    @FXML
    private TableColumn<DisplayUsersModel, String> emailColumn;

    @FXML
    private TableColumn<DisplayUsersModel, String> studentIDColumn;

    /*
     * Update Books Tab Pane and all
     * associated TextFields, TextAreas,
     * & Buttons.
     */
    
    @FXML
    private AnchorPane updateBooksPane;
    
    @FXML
    private TextField author;
    
    @FXML
    private TextField title;
    
    @FXML
    private TextField id;
    
    @FXML
    private TextField status;
    
    @FXML
    private Button deleteBookButton;
    
    @FXML
    private Button addBookButton;
    
    @FXML
	private TextArea informationArea;
    
    /*
     * Update User Tab Pane and all
     * associated TextFields, TextAreas,
     * & Buttons.
     */

    @FXML
    private AnchorPane updateUserPane;
    
    @FXML
    private TextField studentIdField;
    
    @FXML
    private TextField classYearField;

    @FXML
    private TextField studentEmailField;
    
    @FXML
    private Button addUserButton;
    
    @FXML
    private Button deleteUserButton;
    
    @FXML
    private TextArea userTextArea;
    
    //DatabaseHandler variable
    DatabaseHandler databaseHandler;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	//establishes db connection
		databaseHandler = DatabaseHandler.getInstance();
		
		//create columns methods for Books and Students tables
		createBookColumns();
		createStudentColumns();
		
		//sets ID column in student table from id column in Student table
		studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	}
    
    //creates columns for Books table and displays information
    private void createBookColumns() {
		
  		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
  		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
  		bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
  		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
  	}
    
    //creates columns for Students table and displays information
    private void createStudentColumns() {
		
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		//studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		classYearColumn.setCellValueFactory(new PropertyValueFactory<>("classYear"));
	}

    /*
     * Returns user to Main screen
     * when Exit button is clicked. 
     * 
     */
    
	@FXML
    void returnHome(ActionEvent event) throws IOException {

    	systemPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));
    	Scene scene = new Scene(systemPane,600,425);
    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

	/*
	 * Adds a book to the Books Table
	 * when Add button is pressed.
	 * 
	 */
	
    @FXML
    void addBook(ActionEvent event) throws IOException, SQLException {

    	//string used for return value
    	String update = null;
    	
    	//grabs book information from user input
    	String addAuthor = author.getText();
    	String addTitle = title.getText();
    	String addBookID = id.getText();
    	String addStatus = status.getText();
    	informationArea.setText("");
    		
    	//calls AddBook method from UpdateCatalogModel Class, returning a string
    	update = UpdateCatalogModel.AddBook(addAuthor, addTitle, addBookID, addStatus);
    	
    	
    	//if string return matches, appends text
    	if(update == "added") {
    		
    		informationArea.appendText(addTitle + " by " + addAuthor + "\n" + "has been added" + "\n" +  "to the Database." + "\n");
    	}
    	
    	//if string return matches, appends text
    	else if(update == "duplicate") {
    		
    		informationArea.appendText("Book ID must be unique." + "\n" + "Cannot allow duplicates.");
    	}
    	
    	else {
    	
    		return;
    	}
    	
    	author.setText("");
    	title.setText("");
    	id.setText("");
    	status.setText("");
    }

    /*
     * Deletes a book from the Books Table
     * when Delete button clicked.
     * 
     */
    
    @FXML
    void deleteBook(ActionEvent event) throws IOException {

    	//string used for return value
    	String update = null;
    	
    	//grabs book information from user input
    	String deleteAuthor = author.getText();
    	String deleteTitle = title.getText();
    	String deleteBookID = id.getText();
    	String deleteStatus = status.getText();
    	informationArea.setText("");
    	
    	//calls DeleteBook method from UpdateCatalogModel, returning a string
    	update = UpdateCatalogModel.DeleteBook(deleteAuthor, deleteTitle, deleteBookID, deleteStatus);
    	
    	//if string return matches, appends text
    	if(update == "deleted") {
    		
    		informationArea.appendText(deleteTitle + " by " + deleteAuthor + "\n" + "has been deleted" + "\n" +  "from the Database." + "\n");
    	}
    	
    	//does not display message: Bug
    	else if(update == "unknown") {
    		
    		informationArea.appendText("Could not find book." + "\n" + "Please enter valid Book ID.");
    	}
    	
    	author.setText("");
    	title.setText("");
    	id.setText("");
    	status.setText("");
    }

    /*
     * Checks book out if
     * Check Out button
     * clicked.
     * 
     */
    
    @FXML
    void checkOut(ActionEvent event) throws IOException, SQLException {
    	
    	//grabs title and id from user input
    	String titleOut = titleCheckOutField.getText();    
    	String bookIDOut = bookIDCheckOutField.getText();
    	
    	//calls CheckOut method from InOutModel Class
    	InOutModel.CheckOut(titleOut, bookIDOut);
    	
    	titleCheckOutField.setText(""); 	
    	bookIDCheckOutField.setText(""); 
    }

    /*
     * Returns book if
     * Return button
     * clicked
     * 
     */
    
    @FXML
    void returnBook(ActionEvent event) throws IOException, SQLException {

    	//grabs information from user input
    	String returnTitle = titleReturnField.getText();    
    	String returnBookID = bookIDReturnField.getText();
    	
    	//calls CheckIn method from InOutModel
    	InOutModel.CheckIn(returnTitle, returnBookID);
    	
    	titleReturnField.setText(""); 	
    	bookIDReturnField.setText(""); 
    }

    /*
     * Adds user to Students table
     * when Add button is clicked.
     * 
     */
    
    @FXML
    void addUser(ActionEvent event) throws IOException, SQLException {

    	//string used for return value
    	String insert = null;
    	
    	//grabs student information from user input
    	String email = studentEmailField.getText(); 	
    	String id = studentIdField.getText(); 
        String classYear = classYearField.getText();
        userTextArea.setText("");
        
        //calls AddUser method from UpdateUsersModel Class, returns a string
        insert = UpdateUsersModel.AddUser(email, id, classYear);
    	
        //if string return matches, appends text
    	if(insert == "added") {
    		
    		userTextArea.appendText(id + " has been" + "\n" + "added" + "\n" +  "to the Database." + "\n");
    	}
    	
    	//if string return matches, appends text
    	else if(insert == "notAdded") {
    		
    		userTextArea.appendText("Student ID " + "\n" + "must be unique." + "\n" + "Cannot allow " + "\n" + "duplicates.");
    	}
    	
    	studentEmailField.setText(""); 	
    	studentIdField.setText(""); 
        classYearField.setText("");
    }
    
    /*
     * Deletes user from Students
     * table when Delete button
     * is clicked.
     * 
     */
    
    @FXML
    void deleteUser(ActionEvent event) throws SQLException {

    	//string used for return value
    	String remove = null;
    	
    	//grabs student information from user input
    	String deleteEmail = studentEmailField.getText(); 	
    	String deleteID = studentIdField.getText(); 
        String deleteClassYear = classYearField.getText();
        userTextArea.setText("");
        
        //calls DeleteUser method from UpdateUsersModel, returns a string
        remove = UpdateUsersModel.DeleteUser(deleteEmail, deleteID, deleteClassYear);
    	
        //if string return matches, appends text
    	if(remove == "deleted") {
    		
    		userTextArea.appendText(deleteID + " has been" + "\n" + "removed" + "\n" +  "from the" + "\n" + "Database." + "\n");
    	}
    	
    	//message does not display: Bug
    	else if(remove == "notFound") {
    		
    		userTextArea.appendText("Student ID " + "\n" + "not found." + "\n" + "Enter valid" + "\n" + "users only.");
    	}
    	
    	studentEmailField.setText(""); 	
    	studentIdField.setText(""); 
        classYearField.setText("");
    }
    
    /*
     * Displays all books from Books
     * table when Display button is pressed
     * in Catalog Tab.
     * 
     */
    
    @FXML
    void displayBooks(ActionEvent event) throws IOException {
    	 	
    	//calls DisplayBooks method from DisplayCatalogModel Class
    	DisplayCatalogModel.DisplayBooks();
    	
    	//sets tableview from list in DisplayCatalogModel Class
    	tableDisplay.getItems().setAll(DisplayCatalogModel.list);
    }
    
    /*
     * Displays all students from
     * Students table when Display
     * button is pressed in Users
     * tab.
     * 
     */
    
    @FXML
    void displayStudent(ActionEvent event) {

    	//calls DisplayStudents method from DisplayUsersModel
    	DisplayUsersModel.DisplayStudents();
    	
    	//sets tableview from list in DisplayUsersModel Class
    	studentTable.getItems().setAll(DisplayUsersModel.list);
    }
}