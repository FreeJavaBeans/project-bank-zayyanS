package bank.user.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bank.exceptions.UserSignUpFailedException;
import bank.util.ConnectionUtil;

public final  class BankerRegistration implements UserRegistration{

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static Scanner sc = new Scanner(System.in);
	private String name;
	private String firstName;
    private String lastName;
    private String age;
    private int age1;
    private String username;
    private String password;
    private String passwordAgain;
    private String email;
    private String contact1;
    //private Long contact1;
    private String guardianname;
    private String guardianage;
    private int age2;
    private String guardiancontact;
    private String address;
    
	
	
	public void display() {
		// TODO Auto-generated method stub
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
	//age must be under 20 years old and kids over 16 can open a checking account
			 
		if(age1>=20) {
			System.out.println("Sorry, but you do not fit the criteria to open an account with us. If you have any questions or concerns please contact your local bank and speak ask to speak with a manager. ");
		}
		
		
	
		System.out.println("Email address: ");
		email = sc.nextLine();
		
		
		
		System.out.println("What is you phone number we can contact you at: ");
		contact1 = sc.nextLine();
		
		
		
		System.out.println("What address do you live at: ");
	    address = sc.nextLine();
	    
		System.out.println("What is your guardian's name: ");
guardianname = sc.nextLine();
			
		    System.out.println("How old is your guardian: ");
		    guardianage = sc.nextLine();
		    age2 = Integer.parseInt(guardianage);
		    
		    System.out.println("With what number can we contact your guardian: ");
		  guardiancontact = sc.nextLine();
		  
		  System.out.println("------------------------------------------------------ \n ");
		  CorrectInfo();
		  
		  System.out.println("------------------------------------------------------ \n ");
		  username = firstName.substring(0,1)+ lastName;
			username = username.toLowerCase();
			
	System.out.println("\n Your banking app username is : " + username);
			do {
			System.out.println("\n Please enter a password you would like to use: ");
			password = sc.nextLine();
			
			System.out.println("\n Re-enter a password: ");
			 passwordAgain = sc.nextLine();}
			
			while(!passwordAgain.equals(password)); 
		
			try {
				UserDAO();
			} catch (UserSignUpFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				UserSignUpDAO();
			}
			catch(UserSignUpFailedException e) {
				e.printStackTrace();
			}
			CustomerNewAccount ca = new CustomerNewAccount();
			if(age1<16) {
				ca.newBankAccount( );
				
			}else {ca.newBankAccountPlus();}
		    
		}

	public static void main(String[] args) {
		BankerRegistration br = new BankerRegistration();
		br.display();	}
	
	
	







public void UserSignUpDAO() throws UserSignUpFailedException{
	Connection con = cu.getConnection();
	 
	 
	 
	 String newUSerInfo = "INSERT INTO \"SaversSavingsBank\".banker_records( username, \"password\") VALUES (?, ?) ;";
	 


			        try {

			            // Step 2:Create a statement using connection object
			            PreparedStatement preparedStatement = con.prepareStatement(newUSerInfo);
			            
			            preparedStatement.setString(1, getUsername());
			            preparedStatement.setString(2, getPassword());
			          
int result = preparedStatement.executeUpdate();
			           


			            
       System.out.println(preparedStatement);
       System.out.println(getUsername());
       
       // Step 3: Execute the query or update query
        
         
         
       if(result !=0) {
       	
       	System.out.println("User information recorded and user sign up complete.");
       	 
       }
    		       
      
			            else {
			            	throw new UserSignUpFailedException();
			            }

			        } catch (SQLException e) {
			            // process sql exception
			        	e.printStackTrace();
			            System.out.println("Connection Failed.");
			            
			        }
}


	public void UserDAO() throws UserSignUpFailedException {
		// TODO Auto-generated method stub
		Connection con = cu.getConnection();
		int result;
		 String registerUser = "INSERT INTO \"SaversSavingsBank\".banker( first_name, last_name, \"age\", email, \"contact#\", address, guardianname, guardian_age, \"guardian#\") VALUES(?, ?, ?, ?, ?, ?, ? ,? ,?);";

				        try {

				            // Step 2:Create a statement using connection object
				            PreparedStatement preparedStatement = con.prepareStatement(registerUser);
				            
				            preparedStatement.setString(1, getFirstName());
				            preparedStatement.setString(2, getLastName());
				            preparedStatement.setInt(3, getAge1());
				            preparedStatement.setString(4, getEmail());
				            preparedStatement.setString(5, getContact1());
				            preparedStatement.setString(6, getAddress());
				            preparedStatement.setString(7, getGuardianname());
				            preparedStatement.setInt(8, getAge2());
				            preparedStatement.setString(9, getGuardiancontact());
				              result = preparedStatement.executeUpdate();
				           
 

    			            
            System.out.println(preparedStatement);
           
            
            // Step 3: Execute the query or update query
             
              
              
           if(result != 0) {
            	
            	System.out.println("User information recorded and user sign up complete.");
            	 
            }
         		       
           
				            else {
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
			
			
			//LoginEmployee.check(username,password);
			
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















	public String getFirstName() {
		return firstName;
	}















	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}















	public String getLastName() {
		return lastName;
	}



public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}










	public void setLastName(String lastName) {
		this.lastName = lastName;
	}















	public int getAge1() {
		return age1;
	}















	public void setAge1(int age1) {
		this.age1 = age1;
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















	public String getEmail() {
		return email;
	}















	public void setEmail(String email) {
		this.email = email;
	}















	public String getContact1() {
		return contact1;
	}















	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}















	public String getGuardianname() {
		return guardianname;
	}















	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}















	public String getGuardianage() {
		return guardianage;
	}















	public void setGuardianage(String guardianage) {
		this.guardianage = guardianage;
	}















	public int getAge2() {
		return age2;
	}















	public void setAge2(int age2) {
		this.age2 = age2;
	}















	public String getGuardiancontact() {
		return guardiancontact;
	}















	public void setGuardiancontact(String guardiancontact) {
		this.guardiancontact = guardiancontact;
	}















	public String getAddress() {
		return address;
	}















	public void setAddress(String address) {
		this.address = address;
	}



	

}
