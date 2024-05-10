package dao;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DBConnUtil;
import org.junit.jupiter.api.Test;
import util.DBConnUtil;
import dao.ICustomerService;
import dao.CustomerServiceImpl;
import entity.Customer;


import org.junit.jupiter.api.Test;

class CustomerServiceImplTest {
	ICustomerService mockCustomerService = mock(ICustomerService.class);

	@Test
	 void testUpdateCustomer()
	{
		Customer c=new Customer();
		c.setCustomerID(10);
		c.setFirstName("sushma");
		c.setLastName("harshitha");
		c.setEmail("sushma@gmail.com");
		c.setPhoneNumber(987987989);
		c.setAddress("Hyderabad");
		c.setUserName("sushma");
		c.setPassword("harshitha");
		//c.setRegistrationDate("2024-05-06");
		// when(mockVehicleService.getVehicleById(anyInt())).thenReturn(new Vehicle());
        when(mockCustomerService.updateCustomer(any(Customer.class))).thenReturn(true);
        
        // Call the updateVehicle method
        boolean flag = mockCustomerService.updateCustomer(c);
        
        // Verify that the updateVehicle method is called with the correct parameters
        verify(mockCustomerService).updateCustomer(c);
        
        // Verify that the method returns true
        assertTrue(flag);
		//fail("Not yet implemented"); // TODO
	}

}
