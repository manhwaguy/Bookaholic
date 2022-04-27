/*
 * DatabaseHandler creates the database
 * connection, handler, tables, and sends
 * queries to the database.
 * 
 * Author: Melwyn Cespedes
 * 
 * Note: Be careful modifying this Class, as it is crucial
 * to the performance of the application.
 */

package library.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

	//creates the db url as a string, connection/statement variables, and databaseHandler variable
	private static final String DATABASE_URL = "jdbc:derby:database;create=true";
	private static Connection connection = null;
	private static Statement statement = null;
	public static DatabaseHandler databaseHandler = null;
	
	//initializes methods used during initial appliction start up
	public DatabaseHandler() {
		
		makeConnection();
		createBooksTable();
		createEmployeeTable();
		createStudentTable();
	}
	
	//shares db object during connection by creating a databaseHandler
	public static DatabaseHandler getInstance() {
		
		if(databaseHandler == null) {
			
			databaseHandler = new DatabaseHandler();
		}
		
		return databaseHandler;
	}

	//creates the connection to the db url wiht the JDBC Driver
	void makeConnection() {
		
		try {
			
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			connection = DriverManager.getConnection(DATABASE_URL);
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//creates Books table in db
	void createBooksTable() {
		
		String TABLE_NAME = "Books";
		
		try {
			
			//initializes table
			statement = connection.createStatement();
			DatabaseMetaData dataBaseMeta = connection.getMetaData();
			ResultSet table = dataBaseMeta.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(table.next()) {
				
				//lets operator know that table was initialized successfully
				System.out.println("Table " + TABLE_NAME + " initialzed.");
			}
			
			else {
				
				//creates the table columns
				statement.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "			author varchar(200),\n"
						+ "			title varchar(200),\n"
						+ "			bookID varchar(200) primary key,\n"
						+ "			status varchar(200)," 
						+ " )");
			}
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
		}
	}
	
	//creates Students table in db
	private void createStudentTable() {
		
		String TABLE_NAME = "Students";
		
		try {
			
			statement = connection.createStatement();
			DatabaseMetaData dataBaseMeta = connection.getMetaData();
			ResultSet table = dataBaseMeta.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(table.next()) {
				
				System.out.println("Table " + TABLE_NAME + " initialzed.");
			}
			
			else {
				
				statement.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "			email varchar(200),\n"
						+ "			studentID varchar(200) primary key,\n"
						+ "			classYear varchar(200),"
						+ " )");
			}
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
		}
	}
	
	//creates Employees Table in db
	private void createEmployeeTable() {
		
		String TABLE_NAME = "Employees";
		
		try {
			
			statement = connection.createStatement();
			DatabaseMetaData dataBaseMeta = connection.getMetaData();
			ResultSet table = dataBaseMeta.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(table.next()) {
				
				System.out.println("Table " + TABLE_NAME + " initialzed.");
			}
			
			else {
				
				statement.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "			username varchar(200) primary key,\n"
						+ "			password varchar(200),"
						+ " )");
			}
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
		}
	}
	
	//returns results of query, never modifies db
	public ResultSet performQuery(String query) {
		
		ResultSet result;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(query);
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	//returns a boolean prompting if query was executed or not
	public boolean performAction(String query) {
		
		try {
			
			statement = connection.createStatement();
			statement.execute(query);
			return true;
		}
		
		catch(SQLException sqlException) {
			
			sqlException.printStackTrace();
			return false;
		} 
	}
}
