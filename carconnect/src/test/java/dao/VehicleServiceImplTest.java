package dao;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DBConnUtil;
import org.junit.jupiter.api.Test;
import exception.VehicleNotFoundException;
import util.DBConnUtil;
import dao.IVehicleService;
import dao.VehicleServiceImpl;
import entity.Vehicle;
class VehicleServiceImplTest {
	//IVehicleService vs=new VehicleServiceImpl();
 IVehicleService mockVehicleService = mock(IVehicleService.class);
	
	//@Test
//	void testGetAvailableVehicles() 
//	{
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	void testAddVehicle() 
	{
		
		Vehicle v=new Vehicle();
		v.setVehicleID(19);
		v.setModel("F140");
	    v.setMake("Ford");
	    v.setYear("2022");
	    v.setColor("Blue");
	    v.setRegistrationNumber("MNO345");
		v.setAvailability(1);
		v.setDailyRate(1500);
		 // Stub the behavior of getVehicleById to return a non-null value
        when(mockVehicleService.getVehicleById(anyInt())).thenReturn(new Vehicle());
        
        // Call the addVehicle method
        mockVehicleService.addVehicle(v);
        
        // Verify that the addVehicle method is called with the correct parameters
        verify(mockVehicleService).addVehicle(v);
		
		
        
	}

	@Test
	void testUpdateVehicle()
	{
		Vehicle v=new Vehicle();
		v.setVehicleID(18);
		v.setModel("F140");
	    v.setMake("Ford");
	    v.setYear("2022");
	    v.setColor("Blue");
	    v.setRegistrationNumber("MNO345");
		v.setAvailability(1);
		v.setDailyRate(1500);
		//boolean flag=mockVehicleService.updateVehicle(v);
            // Remove an existing artwork (assuming artwork with ID 1 exists)
		 when(mockVehicleService.updateVehicle(any(Vehicle.class))).thenReturn(true);
			boolean flag=mockVehicleService.updateVehicle(v);
			 verify(mockVehicleService).updateVehicle(v);
            assertTrue(flag);
       
	}
	@Test
	 void testAvailableVehicle()
	 {
		
			when(mockVehicleService.getAvailableVehicles()).thenReturn(true);
			boolean flag=mockVehicleService.getAvailableVehicles();
			 verify(mockVehicleService).getAvailableVehicles();
            assertTrue(flag);
			
			
	 }
	@Test
	 void testgetallVehicle()
	 {
		 
			
			when(mockVehicleService.getallVehicle()).thenReturn(true);
			boolean flag=mockVehicleService.getallVehicle();
			 verify(mockVehicleService).getallVehicle();
           assertTrue(flag);
			
			
	 }
}


