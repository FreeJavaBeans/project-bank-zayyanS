package bank.employee.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import bank.util.ConnectionUtil;
import bank.banker.hub.OpenApp;
import bank.user.hub.BankerRegistration;

public class EmployeeEnteredApp {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static Connection conn = cu.getConnection();
	
	
	
	public static void main(String[] args) {
		EmployeeEnteredApp eea = new EmployeeEnteredApp(1);
		
	}
	
	public EmployeeEnteredApp(){
		super();
	}
	
	
	
	protected EmployeeEnteredApp(int employee_id) {
		super();
		 Scanner scan = new Scanner(System.in);
		 int input;
		
			do {
		
		System.out.println("Welcome Employee "+employee_id+" to the Savers Savings Bank Employee Appplication.\n Please look at our menu and choose a number between 1 -10.  \n \n 1. View your account "
				+ "\n 2. View all employee accounts \n 3. View one employee account \n 4. View all our customer information "
				+ "\n 5. View one of our customer's information \n 6. View all customer bank accounts \n 7. See a log of all transactions \n 8. View one customer's bank account "
				+ "\n 9. Terminate an employee record \n10. Terminate a customer account \n11. Exit app");
		 input = scan.nextInt();
		
		switch(input) {
		case 1: 
			seeMyInfo(employee_id);
			break;
			
		case 2: 
			
			seeAllEmployeeInfo();
			break;
			
		case 3: 
			System.out.println("Which employee's information would you like to see? ");
			int employ = scan.nextInt();
			
			seeOneEmployeeInfo(employ);
			break;
			
		case 4:
			seeAllCustomersInfo();
			break;
			
		case 5: 
			System.out.println("Which customer's information would you like to see? ");
			int ban = scan.nextInt();
			seeOneCustomerInfo(ban);
			break;
			
		case 6:
			seeAllCustomerFileInfo();
			break;
		case 7: 
			
			logTransactionFiles();
			break;
		case 8: 
			System.out.println("Which customer's bank account would you like to see? ");
			int ba = scan.nextInt();
			seeOneCustomerFileInfo(ba);
			break;
		case 9: 
			System.out.println("Which employee account will you be terminating? ");
			int delemp = scan.nextInt();
			terminateEmployeeFile(delemp, employee_id);
			
			break;
		case 10: 
			
			System.out.println("Which customer account will you be terminating? ");
			int delban = scan.nextInt();
			rejectCustomerFiles(delban, employee_id);
			break;
		case 11: 
			System.out.println("Application terminated. Good bye!");
			break;
			
		default: 
				System.out.println("Invalid menu option. Try again \n");
				break;
			
		}
		
		}while(input !=10 );
		
		
	}
	
	
	
	
	
	
private void logTransactionFiles() {
		// TODO Auto-generated method stub
		

		try {
			String log = "select * from \"SaversSavingsBank\".logs;";
			PreparedStatement ps = conn.prepareStatement(log);
			ResultSet r = ps.executeQuery();
			while(r.next()){
				String id = r.getString("id");
				String date = r.getString("dated");
				String logger = r.getString("logger");
				String level = r.getString("level");
				String message = r.getString("message");
				
				
				String b = "\n Id: "+ id + "\n Date: " + date +"\n Logger: "+ logger + "\n Level: "+level + "\n Message: "+ message+ "\n ";
				System.out.println(b);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

private  Set<EmployeeRegistration> seeAllEmployeeInfo() {
Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello Geoff. To see all of the employee information please enter your username and password below: \n Username:");
		String username = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();
		String bankman = "select * from \"SaversSavingsBank\".employee_records where username = 'gwiggins' and \"password\" = ?;";//input username but not the password
	PreparedStatement ps1;
	
	Set<EmployeeRegistration> allEmployeesInfo = new HashSet<EmployeeRegistration>();
	try {
		ps1 = conn.prepareStatement(bankman);
		ps1.setString(1, password);
		ResultSet r1 = ps1.executeQuery();
		
		
		
		if(r1.next()) {
		
		
			String all = "select * from \"SaversSavingsBank\".employees order by id;";
			PreparedStatement ps = conn.prepareStatement(all);
			ResultSet r = ps.executeQuery();
			String allInfo = "select * from \"SaversSavingsBank\".employee_records ;"; 
			PreparedStatement prepStat = conn.prepareStatement(allInfo);
			ResultSet rs = prepStat.executeQuery();
			
			while(r.next() && rs.next()) {
				
				int id = r.getInt("id");
				String first_name = r.getString("first_name");
				String last_name = r.getString("last_name");
				int age = r.getInt("age");
				String email = r.getString("email");
				long contact = r.getLong("contact");
				String jobtitle = r.getString("jobtitle");
				
				
				String user = rs.getString("username");
				String pass = rs.getString("password");
				
				String b = "\n Employee Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ email+
						"\n Contact Number: " + contact + "\n Jobtitle: "+ jobtitle + "\n Username: "+ user + "\n Password: "+ pass + "\n ";
				System.out.println(b);
			}}
		else {
			System.out.println("query not executed");
		}}
			
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			
	sc.close();	
		
		return allEmployeesInfo;
		
	}
	
private  Set<EmployeeRegistration> seeOneEmployeeInfo(int employees_id) {
Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello Geoff. To see all of the employee information please enter your username and password below: \n Username:");
		String username = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();
		String bankman = "select * from \"SaversSavingsBank\".employee_records where username = 'gwiggins' and \"password\" = ?;";//input username but not the password
	PreparedStatement ps1;
	
	Set<EmployeeRegistration> oneEmployeesInfo = new HashSet<EmployeeRegistration>();
	try {
		ps1 = conn.prepareStatement(bankman);
		ps1.setString(1, password);
		ResultSet r1 = ps1.executeQuery();
		
		
		
		if(r1.next()) {
		
		
			String all = "select * from \"SaversSavingsBank\".employees where id = ?;";
			PreparedStatement ps = conn.prepareStatement(all);
			ps.setInt(1, employees_id);
			ResultSet r = ps.executeQuery();
			
			String allInfo = "select * from \"SaversSavingsBank\".employee_records where id = ? ;"; 
			PreparedStatement prepStat = conn.prepareStatement(allInfo);
			prepStat.setInt(1, employees_id);
			ResultSet rs = prepStat.executeQuery();
			
			while(r.next() && rs.next()) {
				
				int id = r.getInt("id");
				String first_name = r.getString("first_name");
				String last_name = r.getString("last_name");
				int age = r.getInt("age");
				String email = r.getString("email");
				long contact = r.getLong("contact");
				String jobtitle = r.getString("jobtitle");
				
				
				String user = rs.getString("username");
				String pass = rs.getString("password");
				
				String b = "\n Employee Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ email+
						"\n Contact Number: " + contact + "\n Jobtitle: "+ jobtitle + "\n Username: "+ user + "\n Password: "+ pass + "\n ";
				System.out.println(b);
			}}
		else {
			System.out.println("query not executed");
		}}
			
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			
	sc.close();	
		
		return oneEmployeesInfo;
		
	}
private  Set<EmployeeRegistration> seeMyInfo( int employee_id) {
Scanner sc = new Scanner(System.in);
	
	
	Set<EmployeeRegistration> me = new HashSet<EmployeeRegistration>();
	try {
		
			String all = "select * from \"SaversSavingsBank\".employees where id = ?;";
			PreparedStatement ps = conn.prepareStatement(all);
			ps.setInt(1,employee_id);
			
			ResultSet r = ps.executeQuery();
			
			String allInfo = "select * from \"SaversSavingsBank\".employee_records where id = ?;"; 
			PreparedStatement prepStat = conn.prepareStatement(allInfo);
			prepStat.setInt(1,employee_id);
			
			ResultSet rs = prepStat.executeQuery();
			
			
			while(r.next() && rs.next()) {
				
				int id = r.getInt("id");
				String first_name = r.getString("first_name");
				String last_name = r.getString("last_name");
				int age = r.getInt("age");
				String email = r.getString("email");
				long contact = r.getLong("contact");
				String jobtitle = r.getString("jobtitle");
				
				
				String user = rs.getString("username");
				String pass = rs.getString("password");
				
				String b = "\n Employee Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ email+
						"\n Contact Number: " + contact + "\n Jobtitle: "+ jobtitle + "\n Username: "+ user + "\n Password: "+ pass + "\n ";
				System.out.println(b);
			}
		}
			
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			
	sc.close();	
		
		return me;
	    
	}

	private  Set<BankerRegistration> seeAllCustomersInfo() {
Scanner sc = new Scanner(System.in);
		
		
	Set<BankerRegistration> allBankerInfo = new HashSet<BankerRegistration>();
	try {
		
		
		
			String all = "select * from \"SaversSavingsBank\".banker order by id;";
			PreparedStatement ps = conn.prepareStatement(all);
			ResultSet r = ps.executeQuery();
			String allInfo = "select * from \"SaversSavingsBank\".banker_records ;"; 
			PreparedStatement prepStat = conn.prepareStatement(allInfo);
			ResultSet rs = prepStat.executeQuery();
			
			while(r.next() && rs.next()) {
				
				int id = r.getInt("id");
				String first_name = r.getString("first_name");
				String last_name = r.getString("last_name");
				int age = r.getInt("age");
				String email = r.getString("email");
				long contact = r.getLong("contact#");
				String address = r.getString("address");
				String guardn = r.getString("guardianname");
				int guarda = r.getInt("guardian_age");
				String gunum = r.getString("guardian#");
				
				
				String user = rs.getString("username");
				String pass = rs.getString("password");
				
				String b = "\n Banker Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ email+
						"\n Contact Number: " + contact + "\n Address: "+ address + "\n "
								+ "Guardian Name: "+guardn+ "\n Guardian Age: "+guarda + "\n Guardian Contact Number: " + gunum+ "\n Username: "+ user + "\n Password: "+ pass + "\n ";
				System.out.println(b);
			}}
		
			
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			
	sc.close();	
		
		return allBankerInfo;
	}
	
	private  Set<BankerRegistration> seeOneCustomerInfo(int banker_id) {
		Scanner sc = new Scanner(System.in);
		
		
		Set<BankerRegistration> oneBankerInfo = new HashSet<BankerRegistration>();
		try {
			
			
			
				String all = "select * from \"SaversSavingsBank\".banker where id = ?;";
				PreparedStatement ps = conn.prepareStatement(all);
				ps.setInt(1, banker_id);
				ResultSet r = ps.executeQuery();
				String allInfo = "select * from \"SaversSavingsBank\".banker_records where id = ? ;"; 
				PreparedStatement prepStat = conn.prepareStatement(allInfo);
				prepStat.setInt(1, banker_id);
				ResultSet rs = prepStat.executeQuery();
				
				while(r.next() && rs.next()) {
					
					int id = r.getInt("id");
					String first_name = r.getString("first_name");
					String last_name = r.getString("last_name");
					int age = r.getInt("age");
					String email = r.getString("email");
					long contact = r.getLong("contact#");
					String address = r.getString("address");
					String guardn = r.getString("guardianname");
					int guarda = r.getInt("guardian_age");
					String gunum = r.getString("guardian#");
					
					
					String user = rs.getString("username");
					String pass = rs.getString("password");
					
					String b = "\n Banker Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ email+
							"\n Contact Number: " + contact + "\n Address: "+ address + "\n "
									+ "Guardian Name: "+guardn+ "\n Guardian Age: "+guarda + "\n Guardian Contact Number: " + gunum+ "\n Username: "+ user + "\n Password: "+ pass + "\n ";
					System.out.println(b);
				}}
			
				
				
				catch(SQLException e) {
					e.printStackTrace();
				}
				
		sc.close();	
			
			return oneBankerInfo;
	}

	private  Set<BankerRegistration> seeOneCustomerFileInfo(int banker_id) {
Scanner sc = new Scanner(System.in);
		
		
		Set<BankerRegistration> oneBankerFileAccount = new HashSet<BankerRegistration>();
		try {
			
			
			
				String all = "select * from \"SaversSavingsBank\".banker where id = ?;";
				PreparedStatement ps = conn.prepareStatement(all);
				ps.setInt(1, banker_id);
				ResultSet r = ps.executeQuery();
				String allInfo = "select * from \"SaversSavingsBank\".bankaccount where id = ? ;"; 
				PreparedStatement prepStat = conn.prepareStatement(allInfo);
				prepStat.setInt(1, banker_id);
				ResultSet rs = prepStat.executeQuery();
				
				while(r.next() && rs.next()) {
					
					int id = r.getInt("id");
					String first_name = r.getString("first_name");
					String last_name = r.getString("last_name");
					int age = r.getInt("age");
					
					String guardn = r.getString("guardianname");
					
					
					float CheckingAccountBalance = rs.getFloat("checkingAccountBalance");
					float cdeposit = rs.getFloat("checkingAccountDeposit");
					float cwithdraw = rs.getFloat("checkingAccountWithdrawl");
					float setSavingsAccountBalance = rs.getFloat("savingsAccountBalance");
					float sdeposit = rs.getFloat("savingsAccountDeposit");
					float swithdraw = rs.getFloat("savingsAccountWithdrawl");
					
					String b = "\n Banker Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age 
							+ "\n Guardian Name: "+guardn+ "\n  Checking Account Balance: "
								+ CheckingAccountBalance+ "\n Recent deposit: "+cdeposit + "\n Recent withdraw: " + cwithdraw+ "\n Savings Account Balance: "
									+ setSavingsAccountBalance + "\n Recent deposit: "+sdeposit +"\n Recent withdraw: " + swithdraw+ "\n ";
					System.out.println(b);
				}}
			
				
				
				catch(SQLException e) {
					e.printStackTrace();
				}
				
		sc.close();	
			
			return oneBankerFileAccount;
	}

	private  Set<BankerRegistration> seeAllCustomerFileInfo() {
Scanner sc = new Scanner(System.in);
		
		
		Set<BankerRegistration> allBankerFileAccount = new HashSet<BankerRegistration>();
		try {
			
			
			
				
				PreparedStatement ps = conn.prepareStatement
						("select * from \"SaversSavingsBank\".banker order by id;");
				ResultSet r = ps.executeQuery();
				
				PreparedStatement prepStat = conn.prepareStatement
						("select * from \"SaversSavingsBank\".bankaccount ;");
				ResultSet rs = prepStat.executeQuery();
				
				while(r.next() && rs.next()) {
					
					int id = r.getInt("id");
					String first_name = r.getString("first_name");
					String last_name = r.getString("last_name");
					int age = r.getInt("age");
					
					String guardn = r.getString("guardianname");
					
					
					float CheckingAccountBalance = rs.getFloat("checkingAccountBalance");
					float cdeposit = rs.getFloat("checkingAccountDeposit");
					float cwithdraw = rs.getFloat("checkingAccountWithdrawl");
					float setSavingsAccountBalance = rs.getFloat("savingsAccountBalance");
					float sdeposit = rs.getFloat("savingsAccountDeposit");
					float swithdraw = rs.getFloat("savingsAccountWithdrawl");
					
					String b = "\n Banker Account: "+ id + "\n Name: " + first_name +" "+ last_name + "\n Age: "+age 
							+ "\n Guardian Name: "+guardn+ "\n  Checking Account Balance: "
								+ CheckingAccountBalance+ "\n Recent deposit: "+cdeposit + "\n Recent withdraw: " + cwithdraw+ "\n Savings Account Balance: "
									+ setSavingsAccountBalance + "\n Recent deposit: "+sdeposit +"\n Recent withdraw: " + swithdraw+ "\n ";
					System.out.println(b);
				}}
			
				
				
				catch(SQLException e) {
					e.printStackTrace();
				}
				
		sc.close();	
			
			return allBankerFileAccount;
			}
	
private  void rejectCustomerFiles(int banker_id, int id) {
		Scanner sc = new Scanner(System.in);
		
		 
			
			 System.out.println("What is your name? ");
			    String name = sc.nextLine();
			    
			    System.out.println("What is the banker's name? ");
			    String bname = sc.nextLine();
			    
			    System.out.println("What is the banker's email? ");
			    String email = sc.nextLine();
			    
			    System.out.println("What is your reason for rejecting this banker's account? ");
			    String reason = sc.nextLine();
		try {	    
		
		PreparedStatement p1s = conn.prepareStatement("insert into \"SaversSavingsBank\".rejected_applicants"
				+ "(employee_id, employee_name ,banker_id, banker_name, banker_email, reason ) "
				+ "values( ? , ? ,? ,?, ?, ?);");
		p1s.setInt(1, id);
		p1s.setString(2, name);
		p1s.setInt(3, banker_id);
		p1s.setString(4, bname);
		p1s.setString(5, email);
		p1s.setString(6, reason);
		
		
		int r = p1s.executeUpdate();	
		
		if(r!=0) {
			
			System.out.println("\n Please input your employee_id: ");
			   int  employee_id = sc.nextInt();
			   sc.nextLine();
			   if(id == employee_id) {
			   System.out.println("Enter password: ");
			   String password = sc.nextLine();
			   
			 
			  
			   PreparedStatement ps2 = conn.prepareStatement
					   ("select * from employee_records where employee_id = ? and "
					   		+ "\"password\" = ? ;");
			   ps2.setInt(1, id);
			   ps2.setString(2, password);
			   ResultSet results1 = ps2.executeQuery();
			  
			   if(results1.next()) {
				   System.out.println("Are you sure you want to delete this user 'y/n'? ");
				   String yn = sc.nextLine();
				   
				   if(yn.equals("y")) {
				
				    PreparedStatement preparedStatement1 = conn.prepareStatement
				    		("update \"SaversSavingsBank\".banker set \"Active\" = 'N' where id = ?;");
			        preparedStatement1.setInt(1, banker_id);
			        preparedStatement1.executeQuery();
			        
			       
			        PreparedStatement preparedStatement2 = conn.prepareStatement
			        		("delete \"SaversSavingsBank\".banker_records where "
				    		+ "banker_id = ? );");
			        preparedStatement2.setInt(1, banker_id);
			        preparedStatement1.executeQuery();
			        
			         
			        PreparedStatement preparedStatement3 = conn.prepareStatement
			        		("delete \"SaversSavingsBank\".bankaccount where "
				    		+ "banker_id = ? );");
			        preparedStatement3.setInt(1, banker_id);
			        preparedStatement1.executeQuery();
			        
			        
			        
							
							PreparedStatement ps = conn.prepareStatement
									("select * from \"SaversSavingsBank\".banker ;");
							ResultSet resultset = ps.executeQuery();
							
							while(resultset.next()) {
								int bank_id = resultset.getInt("id");
								String first_name = resultset.getString("first_name");
								String last_name = resultset.getString("last_name");
								int age = resultset.getInt("age");
								String bankemail = resultset.getString("email");
								long contact = resultset.getLong("contact#");
								String address = resultset.getString("address");
								String guardn = resultset.getString("guardianname");
								int guarda = resultset.getInt("guardian_age");
								String gunum = resultset.getString("guardian#");
								
								
								String user = resultset.getString("username");
								String pass = resultset.getString("password");
								
								String b = "\n Banker Account: "+ bank_id + "\n Name: " + 
								first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ bankemail+
										"\n Contact Number: " + contact + "\n Address: "+ 
								address + "\n Guardian Name: "+guardn+ "\n Guardian Age: "+guarda + 
								"\n Guardian Contact Number: " + gunum+ "\n Username: "+ user +
								"\n Password: "+ pass + "\n ";
								
								System.out.println(b);}
				   }
					else if(yn.equals("n")) {System.out.println("Thank you. Goodbye.");}
				   
				  else {System.out.println("Sorry your response was not understood. Goodbye.");}
				  
			   }else {System.out.println("Sorry your id and password do not match. Try again soon.");}
		
		}}}catch(SQLException e) {
						e.printStackTrace();
					}
			  
		sc.close();}
	
	
	

private  void terminateEmployeeFile(int emp_id, int id) {
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Hello Geoff. To terminate an employee's account please enter your username and password below: \n Username:");
	String username = sc.nextLine();
	System.out.println("Password: ");
	String password = sc.nextLine();
	String bankman = "select * from \"SaversSavingsBank\".employee_records where username = 'gwiggins' and \"password\" = ?;";//input username but not the password
PreparedStatement ps1;

Set<EmployeeRegistration> allEmployeesInfo = new HashSet<EmployeeRegistration>();
try {
	ps1 = conn.prepareStatement(bankman);
	ps1.setString(1, password);
	ResultSet r1 = ps1.executeQuery();
	if(username.equals("gwiggins")) {
		if(r1.next()) {
		    
			
			
			   System.out.println("Are you sure you want to delete this user 'y/n'? ");
			   String yn = sc.nextLine();
			   if(yn.equals("y")) {
					
				    PreparedStatement preparedStatement1 = conn.prepareStatement
				    		("update \"SaversSavingsBank\".employee set \"Active\" = 'N' where id = ?;");
			        preparedStatement1.setInt(1, emp_id);
			        preparedStatement1.executeQuery();
			        
			       
			        PreparedStatement preparedStatement2 = conn.prepareStatement
			        		("update \"SaversSavingsBank\".employee_records set \"password\" = null where id = ?;");
			        preparedStatement2.setInt(1, emp_id);
			        preparedStatement1.executeQuery();
			        
			         
			        
							PreparedStatement ps = conn.prepareStatement
									("select * from \"SaversSavingsBank\".employee ;");
							ResultSet resultset = ps.executeQuery();
							
							while(resultset.next()) {
								int bank_id = resultset.getInt("id");
								String first_name = resultset.getString("first_name");
								String last_name = resultset.getString("last_name");
								int age = resultset.getInt("age");
								String bankemail = resultset.getString("email");
								long contact = resultset.getLong("contact#");
								String address = resultset.getString("address");
								String guardn = resultset.getString("guardianname");
								int guarda = resultset.getInt("guardian_age");
								String gunum = resultset.getString("guardian#");
								
								
								String user = resultset.getString("username");
								String pass = resultset.getString("password");
								
								String b = "\n Banker Account: "+ bank_id + "\n Name: " + 
								first_name +" "+ last_name + "\n Age: "+age + "\n Email: "+ bankemail+
										"\n Contact Number: " + contact + "\n Address: "+ 
								address + "\n Guardian Name: "+guardn+ "\n Guardian Age: "+guarda + 
								"\n Guardian Contact Number: " + gunum+ "\n Username: "+ user +
								"\n Password: "+ pass + "\n ";
								
								System.out.println(b);
								}
				   }
				   else if(yn.equals("n")) {System.out.println("Thank you. Application terminating. Goodbye.");}
				   else {System.out.println("Sorry your response was not understood. Application terminating. Goodbye.");}
			   
		}
		else {System.out.println("Sorry your response was not understood. Application terminating. Goodbye.");}
	
	}
	else{ 
		System.out.println("Sorry you do not have authorization to complete this action. Application terminating. Goodbye. ");
	
	}}
	catch(SQLException e) {
					e.printStackTrace();
				}
		 
	sc.close();
}


}

	
	
	
	
	

