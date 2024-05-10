
package dao;

import entity.Admin;
import entity.Customer;
import entity.Authentication;
import java.sql.*;
import exception.*;
import exception.UserNotFoundException;
import exception.AdminNotFoundException;


import java.util.ArrayList;
import java.util.List;

import util.DBConnUtil;
import util.DBPropertyUtil;


import java.util.Scanner;

public class AdminServiceImpl implements IAdminService {
	 private Connection connection;
	 Scanner sc=new Scanner(System.in);
	 
	 private static final String query_getById = "SELECT * FROM Admin WHERE   AdminID  = ?";
	    private static final String query = "SELECT * FROM Admin WHERE   firstName  = ?";
	    private static final String query_register = "INSERT INTO Admin values(?,?,?,?,?,?,?,?,?)";
	    private static final String query_update="UPDATE admin SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, Username=?, Password=?, Role=?,JoinDate=? WHERE AdminID=?";
	    private static final String query_delete = "DELETE FROM Admin WHERE AdminID = ?";
	    public static final String a_query= "SELECT username,password FROM Admin";
	    
	    
	    private void extractAdminFromResultSet(ResultSet rs) throws SQLException {
	       Admin admin= new Admin();
	       admin.setAdminID(rs.getString("adminID"));
	       admin.setFirstName(rs.getString("firstName"));
	       admin.setLastName(rs.getString("lastName"));
	       admin.setEmail(rs.getString("email"));
	       admin.setPhoneNumber(rs.getLong("PhoneNumber"));
	       admin.setUsername(rs.getString("userName"));
	       admin.setPassword(rs.getString("password"));
	       admin.setRole(rs.getString("role"));
	       admin.setJoinDate(rs.getString("JoinDate"));
	       
	       
	        if (admin != null) {
	            System.out.println("Admin found:");
	            System.out.println("AdminId:"+admin.getAdminID());
	            System.out.println("First Name: " + admin.getFirstName());
	            System.out.println("Last Name: " + admin.getLastName());
	            System.out.println("Email: " + admin.getEmail());
	            System.out.println("Phone Number: " + admin.getPhoneNumber());
	            System.out.println("Username: " + admin.getUsername());
	            System.out.println("Password " + admin.getPassword());
	            System.out.println("Role" + admin.getRole());
	            System.out.println("JoinDate: " + admin.getJoinDate());
	        }
	    }
	
	
    @Override
    public void getAdminById(String adminId) {
        // Implementation code to get admin by ID from database
    	Customer customer=null;
 	   try(Connection con=DBConnUtil.getConnection();PreparedStatement pstmt = con.prepareStatement(query_getById)){
     	pstmt.setString(1,  adminId);
     	System.out.println("next resultset");
     	ResultSet resultset=pstmt.executeQuery();
     	if(resultset.next()) 
     	{
     		 extractAdminFromResultSet(resultset) ;
     		 
     	}
     	else {
     		throw new AdminNotFoundException("Admin NOT FOUND");
     	}
     	
     	
     }catch(SQLException ex){
     	System.err.println("error while fetching Admin bt id");
     	ex.printStackTrace();
     }
 	   catch(AdminNotFoundException ex) {
 	    	ex.printStackTrace();
 	   }
       
    }

    @Override
    public void getAdminByUsername(String firstName) {
        // Implementation code to get admin by username from database
    	Customer customer=null;
        try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, firstName);
            try (ResultSet resultSet = statement.executeQuery()) {
               if(resultSet.next()) {
                extractAdminFromResultSet(resultSet) ;}
               else {
            	   System.out.println("Admin not found");
               }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving admin by username: " + e.getMessage());
        }
       
    }

    @Override
    public void registerAdmin(Admin admin) {
        // Implementation code to register a new admin in the database
        try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_register)) {
    		
    		
    		
            statement.setString(1,admin.getAdminID());
            statement.setString(2,admin.getFirstName() );
            statement.setString(3, admin.getLastName());
            statement.setString(4,admin.getEmail());
            statement.setLong(5,admin.getPhoneNumber());
            statement.setString(6,admin.getUsername());
            statement.setString(7,admin.getPassword());
            statement.setString(8,admin.getRole());
            statement.setDate(9,java.sql.Date.valueOf(java.time.LocalDate.now()));
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("A NEW ROW IS INSERTED");
            }
            else {
            	System.out.println("NOT INSERTED");
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        System.out.println("register admin method called.");
    }

    @Override
    public void updateAdmin(Admin admin) {
        // Implementation code to update admin information in the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_update)) {
    		
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3,admin.getEmail());
            statement.setLong(4,admin.getPhoneNumber());
            statement.setString(5,admin.getUsername());
            statement.setString(6,admin.getPassword());
            statement.setString(7,admin.getRole());
            
            statement.setString(8,admin.getJoinDate());
            statement.setString(9,admin.getAdminID());
            
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("A NEW ROW IS UPDATED");
            }
            else {
            	System.out.println("NOT UPDATED");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer by username: " + e.getMessage());
        }
        
    	
        System.out.println("Update admin method called.");
    }

    @Override
    public void deleteAdmin(String adminId) {
        // Implementation code to delete an admin from the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_delete)) 
    	{
    		
    		
    		
            statement.setString(1,adminId);
            
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("ONE ROW IS DELETED");
            }
            else {
            	throw new UserNotFoundException("entered adminid is not found");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving while deleting admin: " + e.getMessage());
        }catch(UserNotFoundException ex) {
    	    	ex.printStackTrace();
    	   }
    	
        System.out.println("Delete admin method called for admin with ID: " + adminId);
    }
    
    public boolean authenticateAdmin(String username,String Password)
    {
    	Authentication b=new Authentication();
    	boolean f=b.isAuthenticated(username,Password,a_query);
    	return f;
    }
}



