package main;
import dao.*;
import entity.*;
import java.util.Date;
import java.util.Scanner;

public class Main {
  
   
    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    
    public void displayMainMenu1() {
//    	System.out.println("===== Customer Information =====");
//    	System.out.println("1.GetCustomerById");
//    	System.out.println("2.GetCustomerByUsername(username)");
//    	System.out.println("3. RegisterCustomer");*
//    	System.out.println("4.UpdateCustomer");
//    	System.out.println("5.DeleteCustomer");
//    	System.out.println("6.addVehicle");
//    	System.out.println("7.GetVehicleById");*
//    	System.out.println("8.UpdateVehicle");
//    	System.out.println("9.DeleteVehicle");
//    	System.out.println("10.GetAvailableVehicles");*
//    	
//    	
//    	//
//    	System.out.println("11.GetAdminById");
//    	System.out.println("12.GetAdminByUserName");
//    	System.out.println("13.AddAdmin");
//    	System.out.println("14.UpdateAdmin");
//    	System.out.println("15.DeleteAdmin");
//    	System.out.println("16.GetReservationById ");
//    	System.out.println("17.getreservationsbycustomerid");*
//    	System.out.println("18.create reservation");*
//    	System.out.println("19.update reservation");
//    	System.out.println("20.cancel reservation");*
//    	System.out.println("21.Whole report");
    	
    	
//    	System.out.println("22.AuthenticateAdmin");
//    	System.out.println("23.AuthenticateCustomer");
//    	System.out.println(".Exit");
//    	 System.out.println("=======================================");
//         System.out.print("Enter your choice: ");
    	
    	System.out.println("Enter 1 for admin login");
    	System.out.println("Enter 2 for cutomer login ");
    	
    }
    
    public  Customer getCustDetails() {
    	System.out.println("=====ENTER customer details=====");
    	System.out.println("ENTER customerId");
		int customerId=sc.nextInt()	;	
		System.out.println("ENTER  FirstName ");
		String FirstName=sc.next(); 
		System.out.println("ENTER LastName ");
		String LastName= sc.next(); 
		System.out.println("ENTER Email");
		String Email=sc.next(); 
		System.out.println("ENTER PhoneNumber");
		long PhoneNumber=sc.nextLong(); 
		System.out.println("ENTER Address ");
		String Address =sc.next();  
		System.out.println("ENTER Username ");
		String Username=sc.next()  ;
		System.out.println("ENTER Password ");
		String Password  =sc.next()  ;	
		 Customer customer1=new Customer(customerId, FirstName,LastName,Email,PhoneNumber,Address,Username,Password);
		return customer1;
    }
    
    
    public  Vehicle getVehicleDetails() {
    	System.out.println("=====ENTER Vehicle details=====");
    	System.out.println("ENTER VehicleID");
		int VehicleID=sc.nextInt()	;	
		System.out.println("ENTER  Model ");
		String Model=sc.next(); 
		System.out.println("ENTER Make  ");
		String Make = sc.next(); 
		System.out.println("ENTER Year");
		String Year=sc.next(); 
		System.out.println("ENTER Colour ");
		String Colour =sc.next();  
		System.out.println("ENTER RegistrationNumber");
		String RegistrationNumber=sc.next(); 
		System.out.println("ENTER Availability");
		int Availability=sc.nextInt();
		System.out.println("ENTER DailyRate");
		int DailyRate=sc.nextInt()	;
		
		  Vehicle vehicle=new Vehicle(VehicleID,Model,Make,Year,Colour,RegistrationNumber, Availability,DailyRate);
		return vehicle;
    }
    
    public  Reservation getReservationDetails() {
    	System.out.println("=====ENTER Reservation details=====");
    	System.out.println("ENTER ReservationID");
		int ReservationID=sc.nextInt()	;
		System.out.println("ENTER  CustomerId ");
		int CustomerId=sc.nextInt(); 
		System.out.println("ENTER VehicleID");
		int VehicleID=sc.nextInt()	;
		System.out.println("ENTER startdate ");
		String startdate = sc.next(); 
		System.out.println("ENTER enddate");
		String enddate=sc.next(); 
		System.out.println("ENTER totalcost");
		double totalcost =sc.nextDouble();  
		System.out.println("ENTER status");
		String status=sc.next(); 
		
		
		 Reservation reservation=new Reservation(ReservationID,CustomerId,VehicleID,startdate,enddate,totalcost, status);
		return reservation;
    }
    
    
    public Admin getAdminDetails() {
    	System.out.println("=====ENTER Admin details=====");
    	System.out.println("ENTER AdminID");
		String AdminID=sc.next()	;	
		System.out.println("ENTER  FirstName");
		String FirstName=sc.next(); 
		System.out.println("ENTER LastName  ");
		String LastName = sc.next(); 
		System.out.println("ENTER Email");
		String Email=sc.next(); 
		System.out.println("ENTER PhoneNumber ");
		long PhoneNumber=sc.nextLong();  
		System.out.println("ENTER UserName");
		String UserName=sc.next(); 
		System.out.println("ENTER Password");
		String Password=sc.next();
		System.out.println("ENTER Role");
		String Role=sc.next()	;
		System.out.println("ENTER JoinDate");
		String JoinDate=sc.next(); 
		
		Admin admin=new Admin(AdminID,FirstName,LastName,Email,PhoneNumber,UserName,Password,Role,JoinDate);
		return admin;
		
    }
    public void run() {
        int choice ,ch;
        
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        VehicleServiceImpl vehicleService= new VehicleServiceImpl();
        ReservationServiceImpl reservationService= new ReservationServiceImpl();
        AdminServiceImpl adminService=new  AdminServiceImpl();
        Customer customer;
        Vehicle vehicle;
        Admin admin;
        Reservation reservation;
        Report report;
       
        Scanner sc = new Scanner(System.in);
        

        do 
        {
            displayMainMenu1();
            choice = sc.nextInt();
            switch (choice)
            {
            case 1:
            	System.out.println("enter admin username");
            	String a_id= sc.next();
            	System.out.println("enter admin password");
            	String a_pass= sc.next();
            	boolean f=adminService.authenticateAdmin(a_id, a_pass);
            	if(f) {
            		System.out.println("=========================");
            		System.out.println("1.GetCustomerById");
                	System.out.println("2.GetCustomerByUsername(username)");
                	System.out.println("3. RegisterCustomer");
                	System.out.println("4.UpdateCustomer");
                	System.out.println("5.DeleteCustomer");
                	System.out.println("6.addVehicle");
                	System.out.println("7.GetVehicleById");
                	System.out.println("8.UpdateVehicle");
                	System.out.println("9.DeleteVehicle");
                	System.out.println("10.GetAdminById");
                	System.out.println("11.GetAdminByUserName");
                	System.out.println("12.AddAdmin");
                	System.out.println("13.UpdateAdmin");
                	System.out.println("14.DeleteAdmin");
                	System.out.println("15.GetReservationById ");
                	System.out.println("16.update reservation");
                	System.out.println("17.cancel reservation");
                	
                	System.out.println("18.Whole report");
            		System.out.println("=========================");

            	System.out.println("enter your choice for admin accessible functions");
            	ch=sc.nextInt();
            	switch(ch)
            	{
            	 case 1:
                 	System.out.println("===== Customer Information-GetCustomerById=====");
                    System.out.print("Enter customer ID: ");
                    int customer_Id = sc.nextInt();
                    customerService.getCustomerById(customer_Id);
                    break;
            	 case 2:
                 	System.out.println("===== Customer Information-GetCustomerByName=====");
                    System.out.print("Enter  FirstName : ");
                    String  firstName  = sc.next();
                    customerService.getCustomerByUsername( firstName );
                    break;
            	 case 3:
                 	System.out.println("3. registerCustomer");
                 	customer =getCustDetails();
             		customerService.registerCustomer(customer);
                     break;
                 case 4:
                 	customer =getCustDetails();
             		customerService.updateCustomer(customer);
                    break;
                 case 5:
                 	System.out.print("Enter customer ID: ");
                    int cust_Id = sc.nextInt();
                    customerService.deleteCustomer(cust_Id);
                    break;
                 case 6:
                 	System.out.println("6.addVehicle");
                	vehicle=getVehicleDetails();
                	vehicleService.addVehicle(vehicle);
                    break;
                    
                 case 7:
                	 System.out.println("===== Vehicle Information-getvehicleById=====");
                     System.out.print("Enter vehicle ID: ");
                     int vehicle_Id = sc.nextInt();
                     vehicleService.getVehicleById(vehicle_Id);
                     break;
                 case 8:
                 	 vehicle =getVehicleDetails();
             		 vehicleService.updateVehicle(vehicle);
                     break;
                 case 9:
                	 System.out.print("Enter vehicle ID: ");
                	 int veh_Id = sc.nextInt();
                	 vehicleService.removeVehicle(veh_Id);
                	 break;
                 case 10:
                 	System.out.println("===== Admin Information-GetAdminById=====");
                     System.out.print("Enter Admin ID: ");
                     String admin_id = sc.next();
                     adminService.getAdminById(admin_id);
                      
                     break;
                 case 11:
                 	System.out.println("===== Admin Information-GetAdminUserName=====");
                     System.out.print("Enter  FirstName : ");
                     String  first_Name  = sc.next();
                     adminService.getAdminByUsername(first_Name);
                     
                     break;
                 case 12:
                 	System.out.println("9.addAdmin");
                 	admin=getAdminDetails();
                 	adminService.registerAdmin(admin);
                 	
                     break;
                 case 13:
                 	admin=getAdminDetails();
                 	adminService.updateAdmin(admin);
                     break;
                 case 14:
                	 System.out.print("Enter admin ID: ");
                     String adminid= sc.next();
                     adminService.deleteAdmin(adminid);
                     break;
                 case 15: 
                 	System.out.println("===== Reservation Information-getvehicleById=====");
                     System.out.print("Enter ReservationID : ");
                     int reserve_Id = sc.nextInt();
                      reservationService.getReservationById(reserve_Id);
                     break;
                     
                 case 16:
                 	reservation =getReservationDetails();
             		reservationService.updateReservation(reservation);
                     break;
                 case 17:
                 	System.out.print("Enter reservation ID: ");
                     int reserve= sc.nextInt();
                       reservationService.cancelReservation(reserve);
                       break;

                 case 18:
                 	report=new Report();
                 	break;

            	}
            	
            		
            	
            	}else
            	{
            		System.out.println("user not authenticated");
            		break;
            	}
            case 2:
            	System.out.println("enter customer username");
            	String  c_id= sc.next();
            	System.out.println("enter cutomer password");
            	String c_pass= sc.next();
            	boolean t=customerService.authenticateCustomer(c_id, c_pass);
            	
            	if(t)
            	{
            		System.out.println("=========================");
            		System.out.println("1. RegisterCustomer");
            		System.out.println("2.GetVehicleById");
            		System.out.println("3.GetAvailableVehicles");
            		System.out.println("4.getreservationsbycustomerid");
                	System.out.println("5.create reservation");
                	
                	System.out.println("=========================");
                	System.out.println("enter your choice for customer accessible functions");
                	ch=sc.nextInt();
                	switch(ch)
                	{
                	 case 1:
                     	System.out.println("3. registerCustomer");
                     	customer =getCustDetails();
                 		customerService.registerCustomer(customer);
                         break;
                	 case 2:    
                     	System.out.println("===== Vehicle Information-getvehicleById=====");
                      System.out.print("Enter vehicle ID: ");
                      int vehicle_Id = sc.nextInt();
                       vehicleService.getVehicleById(vehicle_Id);
                      break;
                	 case 3:
                     	vehicleService.getAvailableVehicles();
                     	break;
                	 case 4:
                     	System.out.println("===== reservation Information Get by customerId=====");
                         System.out.print("Enter customer17 ID: ");
                         int custom= sc.nextInt();
                         reservationService.getReservationsByCustomerId(custom);
                     	break;
                     	
                     case 5:
                     	System.out.println("3. create reservation");
                     	reservation =getReservationDetails();
                 		reservationService.createReservation(reservation);
                         break;
                	}
                	
            	}
            	else
            	{
            		System.out.println("user not authenticated");
            		break;
            	}
            	break;
            case 3:
            	System.out.println("exit");
            	break;
           
            	
            }
        } while (choice<3 && choice >0);
    }
    
		public static void main(String[] args) 
    	{
    		 Main main = new Main();
    	     main.run();
      
    	}
}
