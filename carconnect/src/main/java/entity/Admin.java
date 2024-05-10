package entity;

public class Admin {
    private String adminID;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String username;
    private String password;
    private String role;
    private String joinDate;
    
    public Admin() {
    	System.out.println("Default Constructor of Admin Class");
    }
    
    
    public Admin(String adminID, String firstName, String lastName, String email, long phoneNumber, String username, String password, String role, String joinDate) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.joinDate = joinDate;
    }

 // Getters and setters...
	public String getAdminID() {
		return adminID;
	}


	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
  
    
    // Methods: Authenticate(password)...
}
