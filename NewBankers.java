package bank.users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import bank.exceptions.UserNotFoundException;
import bank.util.ConnectionUtil;

public  class NewBankers  {

static Scanner sc = new Scanner(System.in);
		


//jdbc:postgresql://localhost:5432/postgres
//loopback ip address: 127.0.0.1

	private String name;
	private String first_name;
	private String last_name;
	
	private String address;
	
	private String email;
	
	private String age;
	private int ageInt;
	private String birthdate;
	
	private String guardian_name;
	private String guardian_email;
	
	private String guardian_age ;
	private int ageInt1;
	
	private String guardian_birthdate;
	private String guardian_address;
	
	
	
	
	private NewBankers() {
		super();
		
		System.out.println("Hello. Please fill in all of the information. \n Name: " );
		name = sc.nextLine();
		
		
name = name.toUpperCase();
		
		String[] name1 = name.split(" ");
		
		if(name1.length <2 || name1.length> 2) {
		System.out.println("Please enter a valid name, first and last name only. \n If you have more than one first or last name, use '-' to separate them."); 
		NewBankers nb = new NewBankers();
		}
		else {
			 first_name = name1[0];
			 last_name = name1[1];}
		
		System.out.println("Age: ");
		age =  sc.nextLine();
		 ageInt = Integer.parseInt(age);
		
		
		
		System.out.println("Birthday: ");
		birthdate= sc.nextLine();
		
		System.out.println("Home address: ");
		address = sc.nextLine();
		
		System.out.println("Email address: ");
		email = sc.nextLine();
		
		System.out.println("-----------------------------------------------");
		System.out.println("Guardian's name: ");
		guardian_name = sc.nextLine();
		
		System.out.println("Guardian's email address: ");
		guardian_email= sc.nextLine();
		System.out.println("Guardian's age: ");
		guardian_age= sc.nextLine();
		ageInt1=Integer.parseInt(guardian_age);
		
		
		System.out.println("Guardian's birthday: ");
		guardian_birthdate= sc.nextLine();
		System.out.println("Guardian's home address: ");
		guardian_address= sc.nextLine();
		
		
		
		System.out.println("Please review. Is all of your information correct? 'y/n' \n" );
		String yn = sc.nextLine();
		if(yn.equals("y")) {
			System.out.println("cool");
		}
		else if(yn.equals("n")) {
			NewBankers nb = new NewBankers();
		}
		else {
			
			 do {
	   	      // Displaying a message on the screen
	   	   System.out.println("I'm sorry I did not catch that. ");
	   	   yn = sc.nextLine();
	   	   } while( isVal(yn) );

		}
	
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		NewBankers nb = new NewBankers();
		
		
	}



	 private boolean isVal(String yn) {
		// TODO Auto-generated method stub
		
		if(yn.equals("y")) {
	 		//what ever happens after make a user name and password
	 		   
	 		   return false;
	 		  
	 	   }
	 	   else if(yn.equals("n")){
	 		  NewBankers nb = new NewBankers();
	 		  return false; 
	 	   }  else return true;
		
	}



	



	protected String getFirst_name() {
		return first_name;
	}



	protected void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	protected String getLast_name() {
		return last_name;
	}



	protected void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	protected String getAddress() {
		return address;
	}



	protected void setAddress(String address) {
		this.address = address;
	}



	protected String getEmail() {
		return email;
	}



	protected void setEmail(String email) {
		this.email = email;
	}



	protected int getAge() {
		return ageInt;
	}



	protected void setAge(int ageInt) {
		this.ageInt = ageInt;
	}



	protected String getBirthdate() {
		return birthdate;
	}



	protected void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}



	protected String getGuardian_name() {
		return guardian_name;
	}



	protected void setGuardian_name(String guardian_name) {
		this.guardian_name = guardian_name;
	}



	protected String getGuardian_email() {
		return guardian_email;
	}



	protected void setGuardian_email(String guardian_email) {
		this.guardian_email = guardian_email;
	}



	protected int getGuardian_age() {
		return ageInt1;
	}



	protected void setGuardian_age(int ageInt1) {
		this.ageInt1 = ageInt1;
	}



	protected String getGuardian_birthdate() {
		return guardian_birthdate;
	}



	protected void setGuardian_birthdate(String guardian_birthdate) {
		this.guardian_birthdate = guardian_birthdate;
	}



	protected String getGuardian_address() {
		return guardian_address;
	}



	protected void setGuardian_address(String guardian_address) {
		this.guardian_address = guardian_address;
	}

	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	

	public void inputNewBankers() throws UserNotFoundException {
		//get a connection
		Connection conn = cu.getConnection();
		
		
		try {
			//prepare a sql statement to get all food "select * from food;"
			PreparedStatement prepstate = conn.prepareStatement("select * from new_bankers;");
			
	
			//execute the statement
			ResultSet results = prepstate.executeQuery();
			
			

			//loop through result set building food objects
			//calling first() or next() will take us to the first row
			while(results.next()) {
				NewBankers nb = new NewBankers();
				nb.setFirst_name(results.getString("first_name"));
				nb.setLast_name(results.getString("last_name"));
				nb.setAge(results.getInt("age"));
				//nb.setBirthdate(results.getString("birtdate"));
				nb.setEmail(results.getString("email"));
				nb.setAddress(results.getString("address"));
				nb.setGuardian_name(results.getString("guardian_name"));
				nb.setGuardian_email(results.getString("guardian_email"));
				nb.setGuardian_age(results.getInt("guardian_age"));
				//nb.setGuardian_birthdate(results.getString("guardian_birthdate"));
				nb.setGuardian_address(results.getString("guardian_address"));
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all information failed");
		}
		
		
		//return that set
		throw new UserNotFoundException();
	}

	
	
	
	
	
	
}
	
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			/* String first_Name;
			 String middle;
			 String last_Name;
			
			
			 System.out.println("What is your name? (first and last)");
			
			 String name = sc.nextLine();
			 name = name.toUpperCase();
			
			String[] name1 = name.split(" ");
			
			if(name1.length <2 || name1.length >2) {
			System.out.println("Please enter your first last name only. \n If you have more than one first name use a '-' to separate. ");
			name = sc.nextLine();
			}
			else {
				 first_Name = name1[0];
				 last_Name = name1[1];
				 }
			
			
			System.out.println("How old are you? ");
			String age = sc.nextLine();
			int ageInt = Integer.parseInt(age);
			if(ageInt >=20) {
				System.out.println("I'm sorry but you do not qualify for the programs offered by our organization. Goodbye. ");
			}
			
			System.out.println("When is your birthday? In form YYYY-MM-DD");
			//this will need a try catch block??
			String birt = sc.nextLine();
			String[] birthday = birt.split("-");
			int month = Integer.parseInt(birthday[1]);
			int year = Integer.parseInt(birthday[0]);
			int day = Integer.parseInt(birthday[2]);
			
			
			
				
			
			
			System.out.println("What is your email address? ");
			String email = sc.nextLine();
			
			System.out.println("");
			
			
			
			System.out.println("---------------------------------------------------------");
			
		System.out.println("Is all of your information correct? 'y/n' \n" + first_Name  + "\n"+last_Name + "\n"+ageInt+ "\n"+birt+ "\n"+ email);
		String yn = sc.nextLine();
		if(yn.equals("y")) {
			System.out.println("cool");
		}
		else if(yn.equals("n")) {
			newPerson();
		}
		else {
			
			 do {
	   	      // Displaying a message on the screen
	   	   System.out.println("I'm sorry I did not catch that. ");
	   	   yn = sc.nextLine();
	   	   } while( isVal(yn) );

		}
		}
		
		private static boolean isVal(String yn){
		 	   if(yn.equals("y")) {
		 		//what ever happens after
		 		   
		 		   return true;
		 		  
		 	   }
		 	   else if(yn.equals("n")){
		 		   newPerson(); 
		 		  return true; 
		 	   }  else return false;
		}
		
		
			
			
		

		public static void newUser() {
			// TODO Auto-generated method stub
			
		}

		
		
		
		
	}*/


