package bank;

import java.util.Scanner;

import bank.banker.hub.BankerLogin;
import bank.banker.hub.BankerRegistration;
import bank.exceptions.UserNotFoundException;

public class BankerStartUpPage {

	private static Scanner sc = new Scanner(System.in);
	
	
	
	
	
	public static void main(String[] args) {
		try {
			BankerStartUpPage esp = new BankerStartUpPage();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
public  BankerStartUpPage() throws UserNotFoundException {
	super();
	System.out.println("Hello. Welcome to the Starters Savers Savings Bank. We are a bank that caters to "
			+ "young adults to teach them how \n to start saving their money in order to become a fiscally responsible "
			+ "adult in the near future. Here at SSSB we \n cater to people between the ages of 10 and 19. We are the young adult safe banking way. "
			+ "If you are between the \n years 16 and 19 on your signup date you will qualify for both a checking and savings account. At the point of \n signup each account will automatically have a gift of $5. Many of our customers sign up in store and can deposit \n more money right away."
			+ " To continue and open an account with us, please press '1', if you would like to be directed \n to the login page please press '2'. If you would like to exit the application please press '3'. \n \n "
			+ "---------------------Menu-------------------\n\n         1. Open a new account \n         2. Login \n         3. Exit application \n \n --------------------------------------------");
		int j = sc.nextInt();
		switch(j) {
		case 1:
			BankerRegistration nbr= new BankerRegistration();
			nbr.display();
			
			break;
		case 2: 
			
			BankerLogin.display();
			
			
			break;
		case 3: 
			System.out.println("Application terminating. Goodbye.");
		default:
			break;
		}

	

	

	
		
	}}


