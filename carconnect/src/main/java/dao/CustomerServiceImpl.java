package dao;
import java.sql.*;
import exception.*;
import exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnUtil;
import util.DBPropertyUtil;

import entity.Customer;
import java.util.Scanner;
import entity.Authentication;


public class CustomerServiceImpl implements ICustomerService {
    
    Scanner sc=new Scanner(System.in);
    private static final String query_getCustomerById = "SELECT * FROM Customer WHERE CustomerID = ?";
    private static final String query = "SELECT * FROM Customer WHERE  firstName  = ?";
    private static final String query_register = "INSERT INTO Customer values(?,?,?,?,?,?,?,?,?)";
    private static final String query_update = "UPDATE Customer SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, Address=?, Username=?, Password=? WHERE CustomerID=?";
    private static final String query_delete = "DELETE FROM Customer WHERE CustomerID = ?";
    public static final String c_query="SELECT UserName,Password FROM Customer";
    
    
    public CustomerServiceImpl() {
     // this.connection = connection;
    	System.out.println("default cons");
    }
    
    private void extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(rs.getInt("customerID"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setEmail(rs.getString("Email"));
        customer.setPhoneNumber(rs.getLong("PhoneNumber"));
        customer.setAddress(rs.getString("Address"));
        customer.setUserName(rs.getString("UserName"));
        customer.setPassword(rs.getString("Password"));
        customer.setRegistrationDate(rs.getDate("RegistrationDate"));
       // return customer;
       
            System.out.println("Customer found:");
            System.out.println("CustomerID: " + customer.getCustomerID());
            System.out.println("First Name: " + customer.getFirstName());
            System.out.println("Last Name: " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Username: " + customer.getUserName());
            System.out.println("Registration Date: " + customer.getRegistrationDate());
        
    }
    
   public void getCustomerById(int customerId) {
	   Customer customer=null;
	   try(Connection con=DBConnUtil.getConnection();PreparedStatement pstmt = con.prepareStatement(query_getCustomerById)){
    	pstmt.setInt(1, customerId);
    	ResultSet resultset=pstmt.executeQuery();
    	if(resultset.next()) 
    	{
    		 extractCustomerFromResultSet(resultset) ;
    		 
    	}
    	else {
    		throw new UserNotFoundException("USER NOT FOUND");
    	}
    	
    	
    }catch(SQLException ex){
    	System.err.println("error while Customer getCustomerById(int customerId)");
    	ex.printStackTrace();
    }
	   catch(UserNotFoundException ex) {
	    	ex.printStackTrace();
	   }
    //return customer;
	   
    
   }
    

    // Other methods...
    public void getCustomerByUsername(String firstName) {
        //String query = "SELECT * FROM Customer WHERE  firstName= ?";
        Customer customer=null;
        try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1,firstName);
            try (ResultSet resultSet = statement.executeQuery()) {
               if(resultSet.next()) {
                extractCustomerFromResultSet(resultSet) ;}
               else {
            	   System.out.println("Customer not found");
               }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer by username: " + e.getMessage());
        }
        //return null;
        
    }
    
    public void registerCustomer(Customer customer) {
    	
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_register)) {
    		
    		
    		
            statement.setInt(1,customer.getCustomerID());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4,customer.getEmail());
            statement.setLong(5,customer.getPhoneNumber());
            statement.setString(6,customer.getAddress());
            statement.setString(7,customer.getUserName());
            statement.setString(8,customer.getPassword());
            statement.setDate(9,java.sql.Date.valueOf(java.time.LocalDate.now()));
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("A NEW ROW IS INSERTED");
            }
            else {
            	System.out.println("NOT INSERTED");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer by username: " + e.getMessage());
        }
        System.out.println("register customer method called.");
    }

public boolean updateCustomer(Customer customer) {
	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_update)) {
		
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3,customer.getEmail());
        statement.setLong(4,customer.getPhoneNumber());
        statement.setString(5,customer.getAddress());
        statement.setString(6,customer.getUserName());
        statement.setString(7,customer.getPassword());
        
        statement.setInt(8,customer.getCustomerID());
        
        int rowinserted=statement.executeUpdate();
        if(rowinserted>0) {
        	System.out.println("A NEW ROW IS UPDATED");
        	return true;
        }
        else {
        	System.out.println("NOT UPDATED");
        	return false;
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving customer by username: " + e.getMessage());
    }
    
	
    System.out.println("Update customer method called.");
    return false;
}


public void deleteCustomer(int customerId) {
	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_delete)) {
		
		
		
        statement.setInt(1,customerId);
        
        int rowinserted=statement.executeUpdate();
        if(rowinserted>0) {
        	System.out.println("ONE ROW IS DELETED");
        }
        else {
        	throw new UserNotFoundException("entered customerid not found");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving while deleting customer: " + e.getMessage());
    }catch(UserNotFoundException ex) {
	    	ex.printStackTrace();
	   }
	
    System.out.println("Delete customer method called for customer with ID: " + customerId);
}
public boolean authenticateCustomer(String username,String Password)
{
	Authentication b=new Authentication();
	boolean f=b.isAuthenticated(username,Password,c_query);
	return f;
}
	

}
