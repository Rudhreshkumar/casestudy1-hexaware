package entity;
import dao.*;
import entity.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthenticationTest 
{
	ICustomerService mockCustomerService = mock(ICustomerService.class);
	public static final String c_query="SELECT UserName,Password FROM Customer";

     @Test
	 void testCustomer() {
    	    Authentication a=new Authentication();
    	    Customer c=new Customer();
    		c.setCustomerID(10);
    		c.setFirstName("sushma");
    		c.setLastName("harshitha");
    		c.setEmail("sushma@gmail.com");
    		c.setPhoneNumber(987987989);
    		c.setAddress("Hyderabad");
    		c.setUserName("sushma");
    		c.setPassword("harshitha");
    	
    		//when(Authentication(c.getUserName(),c.getPassword(),c_query)).thenReturn(new Authentication("sushma","harshitha",c_query));
    		
    		//Admin a= new Admin();
    		
		//fail("Not yet implemented"); // TODO
	}

}
