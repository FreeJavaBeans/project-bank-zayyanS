package bank.banker.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bank.user.hub.BankerRegistration;
import bank.util.ConnectionUtil;

public class BankerLogin {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static int errorMess;
	private static String username;
	private static String password;
	private static int id;
	private static Scanner sc = new Scanner(System.in);
	
	

	
	
	
	public static void display() {
		
		System.out.println("Press enter to begin.");
		String space= sc.nextLine();
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
	    
	    
	    String query = "SELECT * FROM \"SaversSavingsBank\".\"banker_records\" WHERE username =? and \"password\" = ?  ";
	    
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
	            int ageOfMe = rs.getInt("age");
	            
	            System.out.println("\n Your id number is: "+ id + "\n");
	            
	            OpenApp cm = new OpenApp(id, ageOfMe);
	            
	            //open the banker where they can do stuff
	            
	            
	        }
	        else {
do {
	        		
	    	        System.out.print("\n Login failed. Would you like to: \n \n 1. Try again \n 2. Create a new account  \n 3 - Exit the application ");
	        	      errorMess = sc.nextInt();
	        	   } while( isVal(errorMess) );
	        }
	    } catch (SQLException e) {
	       e.printStackTrace();
	       System.out.println("Login Failed");
	       
	    }
	     return checkUser;
	}
	
	
	
	
	
	private static  boolean isVal(int errorMess)  {
		// TODO Auto-generated method stub
		 if(errorMess ==1 ) {
	 	       display();
	 	       return false;
	 	       
	 	   }  
		 else if (errorMess==2) {
			 BankerRegistration br = new BankerRegistration();
			 br.display();
			 return false;
		 }
		 else if (errorMess == 3){
			System.out.println("System shutting down. Goodbye!");
			 
			 return false;
		 }else {
	 	   return true;}}
}
