package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Admin;
import entity.Customer;
import entity.Reservation;
import entity.Vehicle;
import util.DBConnUtil;

public class Report {
	private static final String query1= "SELECT * FROM Reservation";
	private static final String query2= "SELECT * FROM Admin";
	private static final String query3= "SELECT * FROM Customer";
	private static final String query4= "SELECT * FROM Vehicle";
	private void extractReservationFromResultSet(ResultSet rs) throws SQLException {
	     Reservation  reservation=new Reservation();
	     reservation.setReservationID(rs.getInt("ReservationID"));
	     reservation.setCustomerID(rs.getInt("CustomerID"));
	     reservation.setVehicleID(rs.getInt("VehicleID"));
	     
	     
	     reservation.setStartDate(rs.getString("StartDate"));
	     reservation.setEndDate(rs.getString("EndDate"));
	     
	     
	     reservation.setTotalCost(rs.getInt("TotalCost"));
	     reservation.setStatus(rs.getString("Status"));
	     
	      
	       // return customer;
	       
	            System.out.println("reservation found:");
	            System.out.println("ReservationID: " + reservation.getReservationID());
	            System.out.println("CustomerID: " +reservation.getCustomerID());
	            System.out.println("VehicleID: " + reservation.getVehicleID());
	            System.out.println("StartDate: " + reservation.getStartDate());
	            System.out.println("EndDate: " + reservation.getEndDate());
	            System.out.println("TotalCost: " + reservation.getTotalCost());
	            System.out.println("Status: " + reservation.getStatus());
	        
	    }
	
	
	
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
	 
	 private void extractVehicleFromResultSet(ResultSet rs) throws SQLException {
	      Vehicle vehicle=new Vehicle();
	      vehicle.setVehicleID(rs.getInt("VehicleID"));
	      vehicle.setModel(rs.getString("Model"));
	      vehicle.setMake(rs.getString("Make"));
	      vehicle.setYear(rs.getString("year"));
	      vehicle.setColor(rs.getString("color"));
	      vehicle.setRegistrationNumber(rs.getString("RegistrationNumber"));
	      vehicle.setAvailability(rs.getInt("Availability"));
	      vehicle.setDailyRate(rs.getInt("DailyRate"));
	      
	       // return customer;
	       
	            System.out.println("Vehicle found:");
	            System.out.println("VehicleId: " + vehicle.getVehicleID());
	            System.out.println("Model: " + vehicle.getModel());
	            System.out.println("Make: " + vehicle.getMake());
	            System.out.println("year: " + vehicle.getYear());
	            System.out.println("color: " + vehicle.getColor());
	            System.out.println("RegistrationNumber: " + vehicle.getRegistrationNumber());
	            
	           
	            System.out.println("Availability: " + vehicle.isAvailability());
	            System.out.println("DailyRate: " + vehicle.getDailyRate());
	      
	    }
	
	
	public Report()
	{
		try (Connection con=DBConnUtil.getConnection();Statement statement = con.createStatement()) 
    	{
		 	
		 System.out.println("======Reservations========");
			
			 ResultSet rs=statement.executeQuery(query1);  	
			 
 	     	while(rs.next()) 
 	     	{
 	     		
 	     		 extractReservationFromResultSet(rs) ;
 	     		 
 	     	}
 	    	
 	 	   System.out.println();
 	 	  System.out.println();System.out.println();System.out.println("======Admin========");
 	     	
 	     	
 	     	
 	     	rs=statement.executeQuery(query2);
 	     	
 	     	
 	     	while(rs.next()) 
 	     	{
 	     		
 	     		 extractAdminFromResultSet(rs) ;
 	     		 
 	     	}
 	    	
 	 	   System.out.println();
 	 	  System.out.println();System.out.println();System.out.println("======Customers========");
 	     	rs=statement.executeQuery(query3);
 	     	
 	   	while(rs.next()) 
	     	{
	     		
	     		 extractCustomerFromResultSet(rs) ;
	     		 
	     	}
 	   	
 	   System.out.println();
 	  System.out.println();System.out.println();System.out.println("======vehicles========");
 	   
 		rs=statement.executeQuery(query4);
	     	
 	   	while(rs.next()) 
	     	{
	     		
	     		 extractVehicleFromResultSet(rs) ;
	     		 
	     	}
 	     	
          }catch(SQLException ex){
          	System.err.println("error while fetching Reservation");
          	ex.printStackTrace();
          }
    	}
	
	
	
		
	}