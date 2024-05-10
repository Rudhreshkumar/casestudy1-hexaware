package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.AuthenticationException;
import util.DBConnUtil;

public class Authentication {
	public Authentication()
	{
		
	}
	public boolean isAuthenticated(String username,String Password,String a_query)
	{
		boolean flag=false;
		try (Connection con=DBConnUtil.getConnection();Statement statement = con.createStatement()) 
    	{
    		ResultSet rs;
    		
    		rs=statement.executeQuery(a_query);
    		while(rs.next()) 
 	     	{
 	     		if(rs.getString("userName").equals(username) && rs.getString("password").equals(Password))
 	     		{
 	     			System.out.println( "Authenticated");
 	     			flag=true;
 	     			return flag;
 	     		}
 	     		 
 	     	}
    	
    		
    			throw new AuthenticationException("please enter valid credentials...");
    			
    		
    	}catch(SQLException ex){
          	System.err.println("error while fetching Reservation");
          	ex.printStackTrace();
          }
    	catch(AuthenticationException e)
    	{
    		System.out.print("your admin credentials are not authenticated  ");
    		e.printStackTrace();
    	}
		return flag;
	}
}