package bank.employee.hub;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import bank.exceptions.UserNotFoundException;
import bank.exceptions.UserSignUpFailedException;
import bank.user.hub.UserRegistration;
import bank.util.ConnectionUtil;

public  class EmployeeRegistration implements UserRegistration{

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static Scanner sc = new Scanner(System.in);
	private String name;
	private String firstName;
    private String lastName;
    private String age;
    private int age1;
    private String username;
    private String password;
    private String email;
    private String contact;
    private Long contact1;
    private String jobtitle;
    
	protected void bankWoman() throws UserNotFoundException {
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
		Connection conn = cu.getConnection();
		
		System.out.println("Hello Zayyan. To see all of the employee information please enter your username and password below: \n Username:");
		String username = sc.nextLine();
		
		System.out.println("Password: ");
		String password = sc.nextLine();
		if(username.equals("zswaby")) {
	PreparedStatement ps1;
	
	
	try {
		ps1 = conn.prepareStatement
				("select * from \"SaversSavingsBank\".employee_records where username = 'zswaby' and \"password\" = ?;");
		ps1.setString(1, password);
		ResultSet r1 = ps1.executeQuery();
		
		
		
		if(r1.next()) {
			display();
		}else {
			
			System.out.println("Application terminating.");
			throw new UserNotFoundException();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
		else {
			System.out.println("You are not authorized to complete this action. \n Application terminating");
		}
	
	}
	
	
	
	 public void display() {
		// TODO Auto-generated method stub
		 
		 System.out.println("Hello. Welcome to the Starters Savings Savings Bank. We are a bank that caters to "
			+ "young adults to teach them how \n to start saving their money in order to become a fiscally responsible "
			+ "adult in the near future. Here at SSSB we \n cater to people between the ages of 10 and 19. We are the young adult safe banking way. "
			+ "As an employee of this bank \n you will have access to view all of our bankers information. You are now a trusted employee and confidentiality \n is a must!"
			+ " To continue and open an account with us, please press enter.");
		 sc.nextLine();
		System.out.println("Hello. Please fill in all of the information. \n Name: " );
		name = sc.nextLine();
		name = name.toUpperCase();
		String[] name1 = name.split(" ");
		
		if(name1.length <2 || name1.length> 2) {
		System.out.println("Please enter a valid name, first and last name only. \n If you have more than one first or last name, use '-' to separate them."); 
		}
		else {
			 firstName = name1[0];
			 lastName = name1[1];
			 }
			 
		
		System.out.println("Age: ");
		age =  sc.nextLine();
		 age1 = Integer.parseInt(age);
	
		
	
		System.out.println("Email address: ");
		email = sc.nextLine();
		
		
		
		System.out.println("What is you phone number we can contact you at: ");
		contact = sc.nextLine();
		contact1 = Long.parseLong(contact);
		
		if(contact1 < 1000000000) {
			System.out.println("Please enter a valid phone numberr. ");
			contact = sc.nextLine();
			contact1 = Long.parseLong(contact);
		}
		else {
		
		System.out.println("What is your hired jobtitle: ");
		jobtitle = sc.nextLine();
		System.out.println("------------------------------------------------------ \n ");
		  CorrectInfo();
		  
		  System.out.println("------------------------------------------------------ \n ");
		
		username = firstName.substring(0,1)+ lastName;
		username = username.toLowerCase();
		
System.out.println("\n Your company username is : " + username);
		
		System.out.println("\n Please enter a password you would like to use: ");
		password = sc.nextLine();
	
		try {
			UserDAO();
		} catch (UserSignUpFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
	 }

	

	public void UserDAO() throws UserSignUpFailedException {
		// TODO Auto-generated method stub
		
		 String registerUser = "INSERT INTO \"SaversSavingsBank\".employees(first_name, last_name,\"age\", email, contact, jobtitle,activity) VALUES(?, ?, ?, ?, ?, ?, 'Y');";
		 
		 String newUSerInfo = "INSERT INTO \"SaversSavingsBank\".employee_records( username, \"password\") VALUES (?, ?);";



				        try(Connection con = cu.getConnection()) {

				            // Step 2:Create a statement using connection object
				            PreparedStatement preparedStatement = con.prepareStatement(registerUser);
				            
				            preparedStatement.setString(1, getFirstName());
				            preparedStatement.setString(2, getLastName());
				            preparedStatement.setInt(3, getAge1());
				            preparedStatement.setString(4, getEmail());
				            preparedStatement.setLong(5, getContact1());
				            preparedStatement.setString(6, getJobtitle());
				            int result = preparedStatement.executeUpdate();
				    			            PreparedStatement ps = con.prepareStatement(newUSerInfo);
				    			            
				    			            ps.setString(1, getUserName());
				    			            ps.setString(2, getPassword());
				    			           int results = ps.executeUpdate();
				    			            
				            System.out.println(preparedStatement);
				            System.out.println(getUserName());
				            
				            // Step 3: Execute the query or update query
				              
				              
				            if(result!=0 && results!=0) {
				            	System.out.println("User information recorded and user sign up complete.");
				            	
				            }
				            else if (result!=0) {
				            	System.out.println("User information recorded BUT user sign up incomplete.");
				            }
				            
				            else if (results!=0) {
				            	System.out.println("User information NOT recorded BUT user sign up IS complete.");
				            }
				            else{
				            	throw new UserSignUpFailedException();
				            }

				        } catch (SQLException e) {
				            // process sql exception
				        	e.printStackTrace();
				            System.out.println("Connection Failed.");
				            
				        }
				       
		
		
	}

	

	public void CorrectInfo() {
		// TODO Auto-generated method stub
		System.out.println("Please review. Is all of your information correct? 'y/n' " );
		String yn = sc.nextLine();
		if(yn.equals("y")) {
			System.out.println("cool");
			//take to employee log in to try and login
			
			
			
			
		}
		else if(yn.equals("n")) {
			
			display();
			
		}
		else {
			
			 do {
	   	      // Displaying a message on the screen
	   	   System.out.println("I'm sorry I did not catch that. ");
	   	   yn = sc.nextLine();
	   	   } while( isValid(yn) );}
	}

	
	
	public boolean isValid(String yn) {
		// TODO Auto-generated method stub
		if(yn.equals("y")) {
	 		
	 		   
	 		   return true;
	 		  
	 	   }
	 	   else if(yn.equals("n")){
	 		display();
	 		  return true; 
	 	   }  else return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected String getFirstName() {
		return firstName;
	}
	protected String getLastName() {
		return lastName;
	}
protected int getAge1() {
		return age1;
	}
protected String getEmail() {
		return email;
	}
protected Long getContact1() {
		return contact1;
	}
protected String getJobtitle() {
		return jobtitle;
	}
protected String getUserName() {
	return username;
}
protected String getPassword() {
	return password;
}
protected void setUsername(String username) {
	this.username = username;
}
protected void setPassword(String password) {
	this.password = password;
}
protected void setFirstName(String firstName) {
	this.firstName = firstName;
}
protected void setLastName(String lastName) {
	this.lastName = lastName;
}
protected void setAge1(int age1) {
	this.age1 = age1;
}
protected void setEmail(String email) {
	this.email = email;
}
protected void setContact1(Long contact1) {
	this.contact1 = contact1;
}
protected void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}
protected String getName() {
	return name;
}
protected void setName(String name) {
	this.name = name;
}


}
