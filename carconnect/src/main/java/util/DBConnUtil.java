package util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	public static Connection getConnection() {
		String connString = null;
		try {
			connString = DBPropertyUtil.getConnectionString("db.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=null;
		try 
		{
			if(connString!=null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/carconnect","root","");
            
			}
			else {
				System.err.println("Connection string is null");
				//throw nullreference exception
			}
        } catch (ClassNotFoundException e) {
        	System.err.println("ERROR:Driver is not loaded..");
            e.printStackTrace();
        } catch (SQLException e) {
        	System.err.println("ERROR:Connection is not established..");
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}

/*import java.sql.SQLException;

//import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;*/

/*public class DBConnUtil {
  public static Connection getConnection(String filename) throws Exception {
      // Get the connection string using the getConnectionString method from DBPropertyUtil
      String connectionString = DBPropertyUtil.getConnectionString(filename);

      // Establish the connection
      Connection connection = DriverManager.getConnection(connectionString);

      return connection;
  }*/
	
	/*public static Connection getConnection(String filename) throws SQLException {
		// Declare connectionString outside the try block
	    String connectionString = null;

        // Get the connection string using the getConnectionString method from DBPropertyUtil
       try{
    	   connectionString = DBPropertyUtil.getConnectionString(filename);
       }catch (Exception e) {
    	    // Handle the exception here, such as logging or displaying an error message
    	    System.err.println("An error occurred while retrieving connection string: " + e.getMessage());
    	    // Optionally, you can re-throw the exception or take other actions based on your application's requirements
    	}
        Connection connection = null;
        try {
            // Establish the connection
        	
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            // Handle the exception, you can log it or re-throw it if needed
            System.err.println("Failed to establish connection: " + e.getMessage());
            throw e; // re-throwing the exception
        }
        return connection;
    }

  // Example of loading JDBC driver (you may need to load your specific driver)
  static {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
}*/