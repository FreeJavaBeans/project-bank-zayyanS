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
	private static Scanner scan = new Scanner(System.in);
	
	
	public OpenApp(int mybanker_id, int age1) {
		super();
		PropertyConfigurator.configure("log4j.properties");  
		 
		 int input;
		 
		 int banker_id;
		if(age1 >=16 && age1<=19){
			do {
		
		System.out.println("Welcome to Starters Savers Savings Bank. Please review our menu and choose a number between 1 - 6 \n \n -------------------------Menu-----------------------\n\n         1. View your account \n         2. Make a deposit into a checking account \n         3. Make a withdraw from your checking account \n         4. Make a deposit into a savings account \n         5. Make a withdraw from your savings account. \n         6. Log out of banking application \n \n  ----------------------------------------------------\n \n ");
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
			double deposit = scan.nextDouble();
			

			try {
				PreparedStatement ps = conn.prepareStatement("select * from \"SaversSavingsBank\".bankaccount where id = "+mybanker_id+";");
				ResultSet r = ps.executeQuery();
				if(r.next()) {
					 
					 double savingsaccountbalance = r.getDouble("savingsaccountbalance");
					if (savingsaccountbalance < deposit ){
						System.out.println("Insufficient funds. You're Savings Account does not have the correct funds to commit this action. ");
						logger.fatal("Bank account: "+mybanker_id+" has insufficient funds. ");}
					else {
			
			withdrawlSavings(deposit, mybanker_id);
			depositChecking(deposit, banker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+deposit );
		      logger.info("Bank Acount: "+ banker_id+ " recieved $"+deposit +" into checking account");
		}
		
			}
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
			
		case 3: 
			System.out.println("This withdraw will go into your savings account.");
			System.out.println("How much would you like to withdraw? ");
			double withdraw = scan.nextDouble();
			
			

			try {
				PreparedStatement ps = conn.prepareStatement("select * from \"SaversSavingsBank\".bankaccount where id = "+mybanker_id+";");
				ResultSet r = ps.executeQuery();
				if(r.next()) {
					 
					 double savingsaccountbalance = r.getDouble("checkingaccountbalance");
					if (savingsaccountbalance < withdraw ){
						System.out.println("Insufficient funds. You're Checking Account does not have the correct funds to commit this action. ");
						logger.fatal("Bank account: "+mybanker_id+" has insufficient funds. ");}
					else {
			
			withdrawlChecking(withdraw, mybanker_id);
			depositChecking(withdraw, mybanker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdraw);
			logger.info("Bank Acount: "+ mybanker_id+ " recieved $"+withdraw + " into checking account");
		}
		
			}
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
			
		case 4:
			System.out.println("This deposit will come from your checking account.");
			System.out.println("What is the banker id of the account you would like to make the deposit? ");
			 banker_id = scan.nextInt();
			System.out.println("How much would you like to deposit? ");
			double withdrawl = scan.nextDouble();
			
			
			
			try {
				PreparedStatement ps = conn.prepareStatement("select * from \"SaversSavingsBank\".bankaccount where id = "+mybanker_id+";");
				ResultSet r = ps.executeQuery();
				if(r.next()) {
					 
					 double savingsaccountbalance = r.getDouble("checkingaccountbalance");
					if (savingsaccountbalance < withdrawl ){
						System.out.println("Insufficient funds. You're Checking Account does not have the correct funds to commit this action. ");
						logger.fatal("Bank account: "+mybanker_id+" has insufficient funds. ");}
					else {
			withdrawlChecking(withdrawl, mybanker_id);
			depositSavings(withdrawl, banker_id);
			
			myBankAccount(mybanker_id);
			
			logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdrawl );
			logger.info("Bank Acount: "+ banker_id+ " recieved $"+withdrawl + " into savings account");
}
					
				}
			
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 5: 
			
			
			
			System.out.println("This withdraw will go into your checking account.");
			System.out.println("How much would you like to withdraw? ");
			double withdrawll = scan.nextDouble();
			
			
			
			
			try {
				PreparedStatement ps = conn.prepareStatement("select * from \"SaversSavingsBank\".bankaccount where id = "+mybanker_id+";");
				ResultSet r = ps.executeQuery();
				if(r.next()) {
					 
					 double savingsaccountbalance = r.getDouble("savingsaccountbalance");
					if (savingsaccountbalance < withdrawll ){
						System.out.println("Insufficient funds. You're Savings Account does not have the correct funds to commit this action. ");
						logger.fatal("Bank account: "+mybanker_id+" has insufficient funds. ");
					}
					else {
						
						
						withdrawlSavings(withdrawll, mybanker_id);
						depositChecking(withdrawll, mybanker_id);
						
						myBankAccount(mybanker_id);
						
						logger.info("Bank Acount: "+ mybanker_id+ " sent $"+withdrawll);
						logger.info("Bank Acount: "+ mybanker_id+ " recieved $"+withdrawll + " into checking account");
						
						
					}
					
				}
			
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			break;
			
		case 6:
			System.out.println("Application is now closed. Good bye!");
			break;
			
		default: 
				System.out.println("Invalid menu option. Try again \n");
				break;
			
		}
		
		}while(input !=6 );}
		
		else {
			do {
				
				System.out.println("Welcome to Savers Savings Bank. Please choose a number between 1 - 3 \n \n -------------------------Menu-----------------------\n \n         1. View your account \n         2. Make a deposit into a savings account \n         3.Log out of banking application \n \n ----------------------------------------------------\n \n ");
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
					double deposit = scan.nextDouble();
					
					
					try {
						PreparedStatement ps = conn.prepareStatement("select * from \"SaversSavingsBank\".bankaccount where id = "+mybanker_id+";");
						ResultSet r = ps.executeQuery();
						if(r.next()) {
							 
							 double savingsaccountbalance = r.getDouble("savingsaccountbalance");
							if (savingsaccountbalance < deposit ){
								System.out.println("Insufficient funds. You're Savings Account does not have the correct funds to commit this action. ");
								logger.fatal("Bank account: "+mybanker_id+" has insufficient funds. ");}
							else {
								
								
								depositSavings(deposit, banker_id);
								withdrawlSavings(deposit, mybanker_id);
								
								myBankAccount(mybanker_id);
								
								logger.info("Bank Acount: "+ mybanker_id+ " sent $"+deposit);
								logger.info("Bank Acount: "+ banker_id+ " recieved $"+deposit + " into savings account");
								
								
							}
							
						}
					
					
					
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					
					
					break;
					
				case 3: 
					
					System.out.println("Application is now closed. Good bye!");
					break;
				default: 
						System.out.println("Invalid menu option. Try again \n");
						break;
					
				}
				
				}while(input !=3 );
		}
		
	
	}
	
	public OpenApp() {
		// TODO Auto-generated constructor stub
	}

	
	protected void myBankAccount(int banker_id) {
		
		
		
		
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
					
				
				
				
			}
	
	
	
	public void ExampledepositChecking(double deposit) {
		 int banker_id = 1;
		 
		 String depositChecking = "update \"SaversSavingsBank\".bankaccount set checkinghistory = now(), checkingaccountbalance = checkingaccountbalance + ?, checkingaccountdeposit = ? where id = ?;" ;
		 PropertyConfigurator.configure("log4j.properties");
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setDouble(1,deposit);
		 ps.setDouble(2,deposit);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r !=0) {
			 System.out.println("Action complete!");
			   
			 
		      
		      logger.info("example deposit message. delete after viewing");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
			 BasicConfigurator.configure();
			 logger.info("example deposit message. delete after viewing");
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 
	 BasicConfigurator.configure();
	 logger.info("example deposit message. delete after viewing");
	 }
	 
	 
	 }
	protected void depositSavings(double deposit, int banker_id) {
		 
		PropertyConfigurator.configure("log4j.properties");
		 String depositChecking = "update \"SaversSavingsBank\".bankaccount set savingshistory = now(), savingsaccountbalance = savingsaccountbalance + ?, savingsaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setDouble(1,deposit);
		 ps.setDouble(2,deposit);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("Action complete!");
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
		 String withdrawlChecking = "update \"SaversSavingsBank\".bankaccount set checkinghistory = now(), checkingaccountbalance = checkingaccountbalance - ?, checkingaccountwithdrawl = -? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setDouble(1,withdrawl);
		 ps.setDouble(2,withdrawl);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("Action complete!");
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
		 String withdrawlChecking = "update \"SaversSavingsBank\".bankaccount set savingshistory = now(), savingsaccountbalance = savingsaccountbalance - ?, savingsaccountwithdrawl = -? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setDouble(1,withdrawl);
		 ps.setDouble(2,withdrawl);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r!=0) {
			 System.out.println("Action complete!");
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


	protected void depositChecking(double deposit, int banker_id) {
		 
		 
		 String depositChecking = "update \"SaversSavingsBank\".bankaccount set checkinghistory = now(), checkingaccountbalance = checkingaccountbalance + ?, checkingaccountdeposit = ? where id = ?;" ;
		 PropertyConfigurator.configure("log4j.properties");
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setDouble(1,deposit);
		 ps.setDouble(2,deposit);
		 ps.setInt(3, banker_id);
		 int r = ps.executeUpdate();
		 if(r !=0) {
			 System.out.println("Action complete!");
			   
			 
		      
		      //logger.info("Sample info message");
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
