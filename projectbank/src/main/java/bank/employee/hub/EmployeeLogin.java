package bank.employee.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bank.exceptions.UserNotFoundException;
import bank.util.ConnectionUtil;



public class EmployeeLogin {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static int errorMess;
	private static String username;
	private static String password;
	private static int employee_id;
	private static int id;
	private static Scanner sc = new Scanner(System.in);
	
	
	protected int getEmployeeId() {
		return employee_id;
	}
	
	
	
	
	protected void display() {
		System.out.println("Press enter to begin.");
		 sc.nextLine();
		System.out.println("Enter your username: ");
	   
		username = sc.nextLine();
	    
	    
	    
	    System.out.println("Enter your password: ");
	    password = sc.nextLine();
	    
	    check();
	    
	}
	
	
	protected  int getId() {
		return id;
	}
	protected  void setId(int id) {
		this.id = id;
	}
	
	protected static String getUsername() {
		return username;
	}
	protected static String getPassword() {
		return password;
	}



	private static boolean check() {
		// TODO Auto-generated method stub
		Connection conn = cu.getConnection();
		PreparedStatement ps;
	    ResultSet rs;
	    boolean checkUser = false;
	    
	    
	    String query = "SELECT id FROM \"SaversSavingsBank\".employee_records WHERE username =? and \"password\" = ?  ";
	    
	    try {
	        ps = conn.prepareStatement(query);
	        ps.setString(1, getUsername());
	        ps.setString(2, getPassword());
	        ps.executeQuery();
	        
	        rs = ps.getResultSet();
	        
	        if(rs.next())//if the results can go to the next line that means the combination exists
	        {
	        	
	            checkUser = true;
	            int id = rs.getInt("id");    
	            
	            System.out.println("\n Your id number is: "+ id);
	            PreparedStatement ps1 = conn.prepareStatement
	            		("update \"SaversSavingsBanker\".employee_records set "
	            				+ "last_shift = now() where id = " + id+";" );
	            int result = ps1.executeUpdate();
	            if(result != 0 ) {
	            	System.out.println("Your login has been recorded. ");
	            }
	            EmployeeEnteredApp eea = new EmployeeEnteredApp(id);
	            
	            
	        }
	        else {
do {
	        		
	    	        System.out.print("Login failed. Would you like to \n 1- Try again \n 2- Open a new account \n 3- Exit application ");
	        	      errorMess = sc.nextInt();
	        	   } while( isVal(errorMess) );
	        }
	    } catch (SQLException e) {
	       e.printStackTrace();
	       System.out.println("Login Failed");
	       
	    }
	     return checkUser;
	}
	
	
	
	
	
	private static  boolean isVal(int errorMess) {
		// TODO Auto-generated method stub
		 if(errorMess ==1 ) {
	 	      EmployeeLogin el = new EmployeeLogin();
	 	      el.display();
	 	      
	 	       return false;
	 	       
	 	   }  
		 else if (errorMess == 2){
			 
			 EmployeeRegistration er = new EmployeeRegistration();
			 try {
				
				 er.bankMan();
				 
				 
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 return false;
		 }
		 else if (errorMess == 3){
			 
				System.out.println("System shutting down. Goodbye!");
				 
				 return false;
			 }else {
	 	   return true;}}
}
