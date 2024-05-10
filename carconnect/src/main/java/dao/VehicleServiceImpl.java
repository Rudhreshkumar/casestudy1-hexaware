package dao;
import entity.Vehicle;
import exception.UserNotFoundException;
import exception.VehicleNotFoundException;
import util.DBConnUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleServiceImpl implements IVehicleService {
	
	
	 private static final String query ="INSERT INTO Vehicle values(?,?,?,?,?,?,?,?)";
	 private static final String query_getvid="select * from vehicle where VehicleID=?";
	 private static final String query_delete="DELETE FROM Vehicle WHERE VehicleID = ?";
	 private static final String query_update="UPDATE Vehicle SET Model=?, Make=?, year=?, color=?, RegistrationNumber=?, Availability=?, DailyRate=? WHERE VehicleID=?";
	 private static final String query_getall="select * from vehicle";
	 
	 private Vehicle extractVehicleFromResultSet(ResultSet rs) throws SQLException {
	      Vehicle vehicle=new Vehicle();
	      vehicle.setVehicleID(rs.getInt("VehicleID"));
	      vehicle.setModel(rs.getString("Model"));
	      vehicle.setMake(rs.getString("Make"));
	      vehicle.setYear(rs.getString("year"));
	      vehicle.setColor(rs.getString("color"));
	      vehicle.setRegistrationNumber(rs.getString("RegistrationNumber"));
	      vehicle.setAvailability(rs.getInt("Availability"));
	      vehicle.setDailyRate(rs.getInt("DailyRate"));
	      
	       // return Vehicle;
	       
	            System.out.println("Vehicle found:");
	            System.out.println("VehicleId: " + vehicle.getVehicleID());
	            System.out.println("Model: " + vehicle.getModel());
	            System.out.println("Make: " + vehicle.getMake());
	            System.out.println("year: " + vehicle.getYear());
	            System.out.println("color: " + vehicle.getColor());
	            System.out.println("RegistrationNumber: " + vehicle.getRegistrationNumber());
	            
	           
	            System.out.println("Availability: " + vehicle.isAvailability());
	            System.out.println("DailyRate: " + vehicle.getDailyRate());
	            return vehicle;
	    }
	 
    @Override
    public Vehicle getVehicleById(int vehicleId) {
        // Implementation code to get vehicle by ID from database
    	
 	   try(Connection con=DBConnUtil.getConnection();PreparedStatement pstmt = con.prepareStatement(query_getvid)){
     	pstmt.setInt(1, vehicleId);
     	ResultSet resultset=pstmt.executeQuery();
     	if(resultset.next()) 
     	{
     		 extractVehicleFromResultSet(resultset) ;
     		 
     	}
     	else {
     		throw new VehicleNotFoundException("Vehicle NOT FOUND");
     	}
     	
     	
     }catch(SQLException ex){
     	System.err.println("error while fetching vehicle");
     	ex.printStackTrace();
     }
 	   catch(VehicleNotFoundException ex) {
 	    	ex.printStackTrace();
 	   }
        return null;
    }

    @Override
   public boolean  getAvailableVehicles() {
        // Implementation code to get list of available vehicles from database
    	 try(Connection con=DBConnUtil.getConnection();Statement pstmt = con.createStatement()){
    		 
    		 ResultSet rs=pstmt.executeQuery("select * from vehicle where Availability =1");
    		 if(!rs.next())
    		 {
    			 throw new VehicleNotFoundException("no available vehicles");
    			 
    			 
    		 }
    		 
    		 while(rs.next())
    		 {
    			 System.out.println(" Vehicle " + rs.getInt("VehicleID") + ", Model: " + rs.getString("Model") + ", Make: " + rs.getString("Make")+" year "+rs.getString("year")+" color "+rs.getString("color")+" RegisteredNumber: "+rs.getString("RegistrationNumber")+" Availablity :"+rs.getInt("Availability")+" ,DailyRate "+rs.getInt("DailyRate"));
    		 }
    		
    		 
         }catch(SQLException ex){
         	System.err.println("error while fetching vehicle");
         	ex.printStackTrace();
         }
     	   catch(VehicleNotFoundException ex) {
     		   
     	    	ex.printStackTrace();
     	    	return false;
     	   }
    	 return true;
           
    	
       
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        // Implementation code to add a new vehicle to the database
try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query)) {
    		
    		
    		
            statement.setInt(1,vehicle.getVehicleID());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getMake());
            statement.setString(4,vehicle.getYear());
            statement.setString(5,vehicle.getColor());
            statement.setString(6,vehicle.getRegistrationNumber());
            statement.setInt(7,vehicle.isAvailability());
            statement.setDouble(8,vehicle.getDailyRate());
          
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

    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        // Implementation code to update vehicle information in the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_update)) {
    		 
             statement.setString(1, vehicle.getModel());
             statement.setString(2, vehicle.getMake());
             statement.setString(3,vehicle.getYear());
             statement.setString(4,vehicle.getColor());
             statement.setString(5,vehicle.getRegistrationNumber());
             statement.setInt(6,vehicle.isAvailability());
             statement.setDouble(7,vehicle.getDailyRate());
            
             statement.setInt(8,vehicle.getVehicleID());
            
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("A ROW IS UPDATED");
            	return true;
            }
            else {
            	System.out.println("NOT UPDATED");
            	return false;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving vehicle by username: " + e.getMessage());
        }
        
    	
        System.out.println("Update vehicle method called.");
        return false;
    	
    }
    

    @Override
    public void removeVehicle(int vehicleId) {
        // Implementation code to remove a vehicle from the database
    	try (Connection con=DBConnUtil.getConnection();PreparedStatement statement = con.prepareStatement(query_delete)) {
    		
    		
    		
            statement.setInt(1,vehicleId);
            
            int rowinserted=statement.executeUpdate();
            if(rowinserted>0) {
            	System.out.println("ONE ROW IS DELETED");
            }
            else {
            	throw new UserNotFoundException("entered vehicleId not found");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving while deleting vehicle: " + e.getMessage());
        }catch(UserNotFoundException ex) {
    	    	ex.printStackTrace();
    	   }
    	
        System.out.println("Delete vehicle method called for vehicleId with ID: " + vehicleId);
    }
    @Override
   
    public boolean getallVehicle() {
    	try (Connection con=DBConnUtil.getConnection();Statement statement = con.createStatement()) 
    	{
    		ResultSet rs=statement.executeQuery(query_getall);
	     	if(!rs.next())
	     		return false;
     	   	while(rs.next()) 
    	     	{
    	     		
    	     		 extractVehicleFromResultSet(rs) ;
    	     		 
    	     	}
     	   
    	}
    	catch(SQLException ex){
          	System.err.println("error while fetching vehicles");
          	ex.printStackTrace();
          	return false;
          }
    	return true;
    }
    

    	
    }

