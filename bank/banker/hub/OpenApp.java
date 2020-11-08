package bank.banker.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import bank.util.ConnectionUtil;

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
	
	
	
	
	public OpenApp(int mybanker_id, int age1) {
		super();
		 Scanner scan = new Scanner(System.in);
		 int input;
		 
		 int banker_id=2;
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
			float deposit = scan.nextInt();
			withdrawlSavings(deposit, mybanker_id);
			depositChecking(deposit, banker_id);
			
			myBankAccount(mybanker_id);
			break;
			
		case 3: 
			System.out.println("This withdraw will go into your savings account.");
			System.out.println("How much would you like to deposit? ");
			float withdraw = scan.nextInt();
			withdrawlChecking(withdraw, mybanker_id);
			depositChecking(withdraw, mybanker_id);
			
			myBankAccount(mybanker_id);
			break;
			
		case 4:
			System.out.println("This deposit will come from your checking account.");
			System.out.println("How much would you like to deposit? ");
			int withdrawl = scan.nextInt();
			withdrawlChecking(withdrawl, mybanker_id);
			depositSavings(withdrawl, banker_id);
			
			myBankAccount(mybanker_id);
			break;
			
		case 5: 
			System.out.println("This withdraw will go into your checking account.");
			System.out.println("How much would you like to withdraw? ");
			float withdrawll = scan.nextInt();
			withdrawlSavings(withdrawll, mybanker_id);
			depositChecking(withdrawll, mybanker_id);
			
			myBankAccount(mybanker_id);
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
					float deposit = scan.nextInt();
					depositSavings(deposit, banker_id);
					withdrawlSavings(deposit, mybanker_id);
					
					myBankAccount(mybanker_id);
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
	
	
	
	protected void depositChecking(float deposit, int banker_id) {
		 
		 
		 String depositChecking = "update bankaccount set checkingaccountdeposithistory = now(), checkingaccountbalance = checkingaccountbalance + ?, checkingaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setFloat(1,deposit);
		 ps.setFloat(2,deposit);
		 ps.setInt(3, banker_id);
		 ResultSet r = ps.executeQuery();
		 if(r.next()) {
			 System.out.println("Deposit complete!");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 }
	 
	 
	 }
	protected void depositSavings(float deposit, int banker_id) {
		 
		 
		 String depositChecking = "update bankaccount set savingsaccountdeposithistory = now(), savingsaccountbalance = savingsaccountbalance + ?, savingsaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(depositChecking);
		 ps.setFloat(1,deposit);
		 ps.setFloat(2,deposit);
		 ps.setInt(3, banker_id);
		 ResultSet r = ps.executeQuery();
		 if(r.next()) {
			 System.out.println("Deposit complete!");
		 }
		 else {
			 System.out.println("Deposit imcomplete.");
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 }
	 
	 
	 }
	protected void withdrawlChecking(float withdrawl, int banker_id) {
		 
		 
		 String withdrawlChecking = "update bankaccount set checkingaccountwithdrawhistory = now(), checkingaccountbalance = checkingaccountbalance - ?, checkingaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setFloat(1,withdrawl);
		 ps.setFloat(2,withdrawl);
		 ps.setInt(3, banker_id);
		 ResultSet r = ps.executeQuery();
		 if(r.next()) {
			 System.out.println("withdrawl complete!");
		 }
		 else {
			 System.out.println("withdrawl imcomplete.");
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
	 }
	 
	 }	 
	protected void withdrawlSavings(float withdrawl, int banker_id) {
		 
		 
		 String withdrawlChecking = "update bankaccount set savingsaccountwithdrawhistory = now(), savingsaccountbalance = savingsaccountbalance - ?, savingsaccountdeposit = ? where id = ?;" ;
	
	 try {
		 PreparedStatement ps = conn.prepareStatement(withdrawlChecking);
		 ps.setFloat(1,withdrawl);
		 ps.setFloat(2,withdrawl);
		 ps.setInt(3, banker_id);
		 ResultSet r = ps.executeQuery();
		 if(r.next()) {
			 System.out.println("withdrawl complete!");
		 }
		 else {
			 System.out.println("withdrawl imcomplete.");
		 }
		 
		 
	 }catch(SQLException e)
	 { e.printStackTrace();
	 System.out.println("SQL imcomplete");
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
