package bank.banker.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;



import bank.util.ConnectionUtil;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;  
public class OpenApp {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static Connection conn = cu.getConnection();
	private String email;
	private static int age;
	private float checkingAccountBalance;
	private double checkingAccountWithdrawl;
	private double checkingAccountDeposit;
	private float savingsAccountBalance;
	private double savingsAccountWithdrawl;
	private double savingsAccountDeposit;
	
	
	private static final Logger logger = LogManager.getLogger(OpenApp.class); 
	
	
	
	public OpenApp(int mybanker_id, int age1) {
		super();
		PropertyConfigurator.configure("log4j.properties");  
		 Scanner scan = new Scanner(System.in);
		 int input;
		 
		 int banker_id;
		if(age1 >=16 && age1<=19){
			do {
		
		System.out.println("Welcome to Savers Savings Bank. Please choose a number between 1 - 5 \n 1. View your account \n 2. Make a deposit into a checking account \n 3. Make a withdraw from your checking account \n 4. Make a deposit into a savings account \n 5. Make a withdraw from your savings account. \n 6. Exit app");
		input = scan.nextInt();
		
		switch(input) {
		case 1: 
			myBankAccount(mybanker_id);
			break;
			
		case 2: 
			System.out.println("This deposit will come from your savings account. ");
			System.out.println("What is the banker id of the account you would like to make the deposit? ");
			 banker_id = scan.nextInt();
			System.out.println("How much would you like to deposit? ");
			double deposit = scan.nextInt();
			withdrawlSavings(deposit, mybanker_id);
			depositChecking(deposit, banker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+deposit );
		      logger.info("Bank Acount: "+ banker_id+ " recieved $"+deposit +"into checking account");
			
			break;
			
		case 3: 
			System.out.println("This withdraw will go into your savings account.");
			System.out.println("How much would you like to deposit? ");
			double withdraw = scan.nextInt();
			withdrawlChecking(withdraw, mybanker_id);
			depositChecking(withdraw, mybanker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdraw);
			logger.info("Bank Acount: "+ mybanker_id+ " recieved $"+withdraw + "into checking account");
			
			break;
			
		case 4:
			System.out.println("This deposit will come from your checking account.");
			System.out.println("What is the banker id of the account you would like to make the deposit? ");
			 banker_id = scan.nextInt();
			System.out.println("How much would you like to deposit? ");
			double withdrawl = scan.nextInt();
			withdrawlChecking(withdrawl, mybanker_id);
			depositSavings(withdrawl, banker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdrawl );
			logger.info("Bank Acount: "+ banker_id+ " recieved $"+withdrawl + "into savings account");
			
			break;
			
		case 5: 
			System.out.println("This withdraw will go into your checking account.");
			System.out.println("How much would you like to withdraw? ");
			double withdrawll = scan.nextInt();
			withdrawlSavings(withdrawll, mybanker_id);
			depositChecking(withdrawll, mybanker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdrawll);
			logger.info("Bank Acount: "+ mybanker_id+ " recieved $"+withdrawll + "into checking account");
			
			
			break;
			
		case 6:
			System.out.println("Application terminated. Good bye!");
			break;
			
		default: 
				System.out.println("Invalid menu option. Try again \n");
				break;
			
		}
		
		}while(input !=6 );}
		
		else {
			do {
				
				System.out.println("Welcome to Savers Savings Bank. Please choose a number between 1 - 3 \n 1. View your account \n 2. Make a deposit into a savings account \n 3.Exit app");
				input = scan.nextInt();
				
				switch(input) {
				case 1: 
					myBankAccount(mybanker_id);
					
					
					break;
					
				case 2: 
					
					System.out.println("This deposit will come from your savings account. ");
					System.out.println("What is the banker id of the account you would like to make the deposit? ");
					banker_id = scan.nextInt();
					System.out.println("How much would you like to deposit? ");
					double deposit = scan.nextInt();
					depositSavings(deposit, banker_id);
					withdrawlSavings(deposit, mybanker_id);
					
					myBankAccount(mybanker_id);
					
					logger.info("Bank Acount: "+ mybanker_id+ " sent $"+deposit);
					logger.info("Bank Acount: "+ banker_id+ " recieved $"+deposit + "into savings account");
					
					break;
					
				case 3: 
					
					System.out.println("Application terminated. Good bye!");
					break;
				default: 
						System.out.println("Invalid menu option. Try again \n");
						break;
					
				}
				
				}while(input !=3 );
		}
		
	scan.close();
	}
	
	public OpenApp() {
		// TODO Auto-generated constructor stub
	}

	
	protected void myBankAccount(int banker_id) {
		Scanner sc = new Scanner(System.in);
		
		
		
			try {
					String showMe = "select * from \"SaversSavingsBank\".bankaccount where id = ?;";
					
					PreparedStatement ps = conn.prepareStatement(showMe);
					ps.setInt(1, banker_id);
					ResultSet r = ps.executeQuery();
					
					
					while(r.next()) {
						int id = r.getInt("id");
						String email = r.getString("email");
						int age = r.getInt("age");
						float CheckingAccountBalance = r.getFloat("checkingAccountBalance");
						float cdeposit = r.getFloat("checkingAccountDeposit");
						float cwithdraw = r.getFloat("checkingAccountWithdrawl");
						float setSavingsAccountBalance = r.getFloat("savingsAccountBalance");
						float sdeposit = r.getFloat("savingsAccountDeposit");
						float swithdraw = r.getFloat("savingsAccountWithdrawl");
					if(age < 16) {
						String b = "My Account Info: \n Id: " + id + "\n Age: " + age +
								"\n Email: " 
						+ email +
						"\n Savings Account Balance: "
						+ savingsAccountBalance + "\n Recent deposit: "+sdeposit +"\n Recent withdraw: " + swithdraw+"\n \n \n";
						System.out.println(b);
					}else {
					String b = "My Account Info: \n Id: " + id + "\n Age: " + age +
							"\n Email: " 
					+ email +
					"\n Checking Account Balance: " 
					+ CheckingAccountBalance+ "\n Recent deposit: "+cdeposit + "\n Recent withdraw: " + cwithdraw+ "\n Savings Account Balance: "
					+ setSavingsAccountBalance + "\n Recent deposit: "+sdeposit +"\n Recent withdraw: " + swithdraw+"\n \n \n";
					System.out.println(b);
					}
					//myBankingAccount.add(b);
					}
				}
					
					
					catch(SQLException e) {
						e.printStackTrace();
					}
					
			sc.close();	
				
				
			}
	
	
	
	protected void depositChecking(double deposit, int banker_id) {
		 
		 
		 String depositChecking = "update \"SaversSavingsBank\".bankaccount set checkingHistory = now(), checkingaccountbalance = checkingaccountbalance + ?, checkingaccountdeposit = ? where id = ?;" ;
		 PropertyConfigurator.configure("log4j.properties");
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setDouble(1,deposit);
		 ps.setDouble(2,deposit);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r !=0) {
			 System.out.println("Deposit complete!");
			   
			 
		      
		      //logger.debug("Sample debug message");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
			 BasicConfigurator.configure();
			 logger.error("Bank Acount: "+ banker_id+ " failed to recieve a deposit of $"+deposit);
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 
	 BasicConfigurator.configure();
	 logger.fatal("Bank Acount: "+ banker_id+ " ULTIMATELY FAILED to recieve a deposit of $"+deposit);
	 }
	 
	 
	 }
	protected void depositSavings(double deposit, int banker_id) {
		 
		PropertyConfigurator.configure("log4j.properties");
		 String depositChecking = "update \"SaversSavingsBank\".bankaccount set savingsaccounthistory = now(), savingsaccountbalance = savingsaccountbalance + ?, savingsaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setDouble(1,deposit);
		 ps.setDouble(2,deposit);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("Deposit complete!");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
			 BasicConfigurator.configure();
			 logger.error("Bank Acount: "+ banker_id+ " failed to recieve a deposit of $"+deposit);
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 
	 BasicConfigurator.configure();
	 logger.fatal("Bank Acount: "+ banker_id+ " ULTIMATELY FAILED to recieve a deposit of $"+deposit);
	 }
	 
	 
	 }
	protected void withdrawlChecking(double withdrawl, int banker_id) {
		 
		PropertyConfigurator.configure("log4j.properties");
		 String withdrawlChecking = "update \"SaversSavingsBank\".bankaccount set checkingaccounthistory = now(), checkingaccountbalance = checkingaccountbalance - ?, checkingaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setDouble(1,withdrawl);
		 ps.setDouble(2,withdrawl);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("withdrawl complete!");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
			 BasicConfigurator.configure();
			 logger.error("Bank Acount: "+ banker_id+ " failed to recieve a deposit of $"+withdrawl);
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 
	 BasicConfigurator.configure();
	 logger.fatal("Bank Acount: "+ banker_id+ " ULTIMATELY FAILED to recieve a deposit of $"+withdrawl);
	 }
	 
	 }	 
	protected void withdrawlSavings(double withdrawl, int banker_id) {
		 
		PropertyConfigurator.configure("log4j.properties");
		 String withdrawlChecking = "update \"SaversSavingsBank\".bankaccount set savingsaccounthistory = now(), savingsaccountbalance = savingsaccountbalance - ?, savingsaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setDouble(1,withdrawl);
		 ps.setDouble(2,withdrawl);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("withdrawl complete!");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
			 BasicConfigurator.configure();
			 logger.error("Bank Acount: "+ banker_id+ " failed to recieve a deposit of $"+withdrawl);
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 
	 BasicConfigurator.configure();
	 logger.fatal("Bank Acount: "+ banker_id+ " ULTIMATELY FAILED to recieve a deposit of $"+withdrawl);
	 }
	 }	 



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public static int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public float getCheckingAccountBalance() {
		return checkingAccountBalance;
	}



	public void setCheckingAccountBalance(float checkingAccountBalance) {
		this.checkingAccountBalance = checkingAccountBalance;
	}



	public double getCheckingAccountWithdrawl() {
		return checkingAccountWithdrawl;
	}



	public void setCheckingAccountWithdrawl(double checkingAccountWithdrawl) {
		this.checkingAccountWithdrawl = checkingAccountWithdrawl;
	}



	public double getCheckingAccountDeposit() {
		return checkingAccountDeposit;
	}



	public void setCheckingAccountDeposit(double checkingAccountDeposit) {
		this.checkingAccountDeposit = checkingAccountDeposit;
	}



	public float getSavingsAccountBalance() {
		return savingsAccountBalance;
	}



	public void setSavingsAccountBalance(float savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
	}



	public double getSavingsAccountWithdrawl() {
		return savingsAccountWithdrawl;
	}



	public void setSavingsAccountWithdrawl(double savingsAccountWithdrawl) {
		this.savingsAccountWithdrawl = savingsAccountWithdrawl;
	}



	public double getSavingsAccountDeposit() {
		return savingsAccountDeposit;
	}



	public void setSavingsAccountDeposit(double savingsAccountDeposit) {
		this.savingsAccountDeposit = savingsAccountDeposit;
	}
	 
}
