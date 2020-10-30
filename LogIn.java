package bank.log;

import java.util.Scanner;

import bank.users.NewBankers;


public class LogIn  {

	 
	
//I think this can be done with SQL
	public static void main(String[] args) {
		runLogIn();
		
	}
	
	

	protected static void runLogIn(){
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (System.in);//I think this has to connect with SQL. if the user name and password are in that specific file use that by new "filename" for now it can be System.in
	    Scanner keyboard = new Scanner (System.in);
		 int errorMess = scan.nextInt();
	    
	    String user = scan.nextLine();
	    String pass = scan.nextLine(); // looks at selected file in scan
System.out.println("Hello welcome to Starters Savings Savings Bank. To login in please enter your username and password below. \n Username: ");
	    String inpUser = keyboard.nextLine();
	    System.out.println("Password: ");
	    String inpPass = keyboard.nextLine(); // gets input from user
	    
	    
	    
	    
	    
	    if (inpUser.equals(user) && inpPass.equals(pass)) {
	        System.out.print("your login message");
	    } else {
	        
	        if(errorMess == 1) {
	        	//open the new user application i think this is also SQL??
	        	//NewBankers.newPerson();
	        }
	        else if(errorMess ==2) {
	        	runLogIn();
	        }
	        else {
	        	System.out.println("Input Error, please try again");
	        	do {
	        		
	    	        System.out.print("Would you like to 1- create a new account or 2- try again? ");
	        	     errorMess = scan.nextInt();
	        	   } while( isVal(errorMess) );

	        	    //if else or switch
	        	

	        	
	        	
	        }
	    }
		
	    
	}
	private static  boolean isVal(int errorMess) {
		// TODO Auto-generated method stub
		 if(errorMess ==1 ) {
	 	       //go to newbankers
	 	      // break; this does not work, figure out why!
	 	       return false;
	 	       
	 	   }  
		 else if (errorMess == 2){
			 runLogIn();
			 return false;
		 }
	 	   return true;}



	
	
	
}
