package bank.banker.hub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bank.util.ConnectionUtil;

public class CustomerNewAccount {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static Connection conn = cu.getConnection();
	
	//Where a customer's bank account will be made
	
	protected void newBankAccountPlus(){
		Scanner sc = new Scanner(System.in);
		//if the person is 16 or over
		String newAccount = "insert into \"SaversSavingsBank\".bankAccount(email, \"age\", checkingAccountBalance)"
				+ "values(? ,? , 5.00);";
		try {PreparedStatement ps = conn.prepareStatement(newAccount);
		System.out.println("Type in your email address again: ");
		String email = sc.nextLine();
		System.out.println("Type in your age again: ");
		int age = sc.nextInt();
		
		ps.setString(1, email);
		ps.setInt(2, age);
		int r = ps.executeUpdate();
		if(r !=0) {
			System.out.println("An account was made, your current balance is: \n Checking Account: 5.00 \n Savings Account: 5.00.");
		}
		else {
			System.out.println("Account not made.");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Account creation failed.");
		}
		
		
		
	}
	
	
	
protected void newBankAccount( ){
	Scanner sc = new Scanner(System.in);
	//if the person is under 16
	String newAccount = "insert into \"SaversSavingsBank\".bankAccount(email, \"age\", savingsAccountBalance)"
			+ "values(? ,?, 10.00 );";
	try {PreparedStatement ps = conn.prepareStatement(newAccount);
	System.out.println("Type in your email address again: ");
	String email = sc.nextLine();
	System.out.println("Type in your age again: ");
	int age = sc.nextInt();
	
	ps.setString(1, email);
	ps.setInt(2, age);
	int r = ps.executeUpdate();
	if(r != 0) {
		System.out.println("An account was made, your current balance is: \n Savings Account: 10.00.");
	}
	else {
		System.out.println("Account not made.");
	}
	
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println("Account creation failed.");
	}
	
	
	}
}
