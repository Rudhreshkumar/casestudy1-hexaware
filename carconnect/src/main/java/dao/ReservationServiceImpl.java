
package dao;

import entity.Reservation;
import entity.Vehicle;
import exception.ReservationException;
import exception.UserNotFoundException;
import exception.VehicleNotFoundException;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationServiceImpl implements IReservationService {
	
	private static final String query_getrid="select * from reservation where ReservationID=?";
	private static final String query_getcid="select * from reservation where CustomerID=?";
	private static final String query_delete="delete from reservation where ReservationID=?";
	private static final String query_insert="insert into reservation values(?,?,?,?,?,?,?)";
	private static final String query_isreserved="select * from reservation where VehicleID=? AND Status= 'completed' " ;
	private static final String query_update="UPDATE reservation SET CustomerID=?, VehicleID=?, StartDate=?, EndDate=?, TotalCost=?, Status=? WHERE ReservationID=? " ;
 public boolean isReserved(int vehicleId){
		 
		 try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_isreserved)){
			 statement.setInt(1,vehicleId);
			 ResultSet resultset=statement.executeQuery();
			 if(resultset.next()) {
				 return true;
			 }
		 } catch (SQLException e) {
	        }
		 return false;
	 }

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
    @Override
    public Reservation getReservationById(int rId) {
        // Implementation code to get vehicle by ID from database
    	
 	   try(Connection con=DBConnUtil.getConnection();PreparedStatement pstmt = con.prepareStatement(query_getrid)){
     	pstmt.setInt(1, rId);
     	ResultSet resultset=pstmt.executeQuery();
     	if(resultset.next()) 
     	{
     		 extractReservationFromResultSet(resultset) ;
     		 
     	}
     	else {
     		throw new ReservationException("Reservation NOT FOUND");
     	}
     	
     	
     }catch(SQLException ex){
     	System.err.println("error while fetching vehicle");
     	ex.printStackTrace();
     }
 	   catch(ReservationException ex) {
 	    	ex.printStackTrace();
 	   }
        return null;
    }
    @Override
    public void getReservationsByCustomerId(int custom) {
        // Implementation code to get list of reservations by customer ID from database
    	 try(Connection con=DBConnUtil.getConnection();PreparedStatement pstmt = con.prepareStatement(query_getcid)){
    	     	pstmt.setInt(1, custom);
    	     	
    	     	 ResultSet rs=pstmt.executeQuery();  	 
    	     	while(rs.next()) 
    	     	{
    	     		
    	     		 extractReservationFromResultSet(rs) ;
    	     		 
    	     	}
             }catch(SQLException ex){
             	System.err.println("error while fetching Reservation");
             	ex.printStackTrace();
             }
         	   
    	 }
 
    @Override
    public void createReservation(Reservation reservationData) {
        // Implementation code to create a new reservation in the database
    		try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_insert)) {
            statement.setInt(1,reservationData.getReservationID());
            statement.setInt(2,reservationData.getCustomerID());
            statement.setInt(3,reservationData.getVehicleID());
            statement.setString(4,reservationData.getStartDate().toString());
            statement.setString(5,reservationData.getEndDate().toString());
            statement.setDouble(6,reservationData.getTotalCost());
            statement.setString(7,reservationData.getStatus());
            
            if(isReserved(reservationData.getVehicleID())) {
            	throw new ReservationException("vehicle is already reserved");
            }else {
            	int rowinserted=statement.executeUpdate();
            	 if(rowinserted>0) {
                 	System.out.println("A NEW ROW IS INSERTED");
                 }
                 else {
                 	System.out.println("NOT INSERTED");
                 }
            }
            
           
        } catch (SQLException e) {
            System.out.println("Errorwhile executing query :" + e.getMessage());
        }
catch(ReservationException rf)
{
    System.out.println("reservation exception " + rf.getMessage());
}
    	
    	
    	
    	
    }

    @Override
    public void updateReservation(Reservation reservationData) {
        // Implementation code to update reservation information in the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_update)) {
   		 
            statement.setInt(1, reservationData.getCustomerID());
            statement.setInt(2, reservationData.getVehicleID());
            statement.setString(3,reservationData.getStartDate().toString());
            statement.setString(4,reservationData.getEndDate().toString());
            statement.setDouble(5,reservationData.getTotalCost());
            statement.setString(6,reservationData.getStatus());
          
           
            statement.setInt(7,reservationData.getReservationID());
           
           int rowinserted=statement.executeUpdate();
           if(rowinserted>0) {
           	System.out.println("A  ROW IS UPDATED");
           }
           else {
           	System.out.println("NOT UPDATED");
           }
       } catch (SQLException e) {
           System.out.println("Errorwhile executing query : " + e.getMessage());
       }
       
   	
       System.out.println("Update reservation method called.");
   	
   }
    	
    	
    

    @Override
    public void cancelReservation(int reservationId) {
        // Implementation code to cancel a reservation in the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_delete)) {
            statement.setInt(1,reservationId);
            
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("ONE ROW IS DELETED");
            }
            else {
            	throw new UserNotFoundException("entered reservationId not found");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving while deleting reservation: " + e.getMessage());
        }catch(UserNotFoundException ex) {
    	    	ex.printStackTrace();
    	   }
    	
        System.out.println("Delete reservation method called for reservationId with ID: " + reservationId);
    }
    	
    }

