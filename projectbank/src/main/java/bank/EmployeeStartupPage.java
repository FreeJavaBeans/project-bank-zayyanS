package bank;

import java.util.Scanner;

import bank.employee.hub.EmployeeLogin;
import bank.employee.hub.EmployeeRegistration;
import bank.exceptions.UserNotFoundException;

public class EmployeeStartupPage {
	
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			EmployeeStartupPage esp = new EmployeeStartupPage();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
private  EmployeeStartupPage() throws UserNotFoundException {
	super();
	System.out.println("Hello. Welcome to the Starters Savers Savings Bank. We are a bank that caters to "
			+ "young adults to teach them how \n to start saving their money in order to become a fiscally responsible "
			+ "adult in the near future. Here at SSSB we \n cater to people between the ages of 10 and 19. We are the young adult safe banking way. "
			+ "As an employee of this bank \n you will have access to view all of our bankers information. You are now a trusted employee and confidentiality \n is a must!"
			+ " To continue and open an account with us, please press '1', if you would like to be directed to the login \n page please press '2'. If you would like to exit the application please press '3'. \n \n "
			+ "1. Open a new account \n 2. Login \n 3. Exit application \n \n ");
		int j = sc.nextInt();
		switch(j) {
		case 1:
			EmployeeRegistration nbr= new EmployeeRegistration();
			nbr.bankWoman();
			
			break;
		case 2: 
			EmployeeLogin ei = new EmployeeLogin();
			ei.display();
			
			
			break;
		case 3: 
			System.out.println("Application terminating. Goodbye.");
		default:
			break;
		}

	

	

	
		
	}}
