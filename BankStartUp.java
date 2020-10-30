package bank.launcher;
import java.sql.Connection;
import java.util.Scanner;

import bank.log.LogIn;

public class BankStartUp extends LogIn{

		static Scanner sc = new Scanner(System.in);
		
		
		public static void main(String[] args) {
			System.out.println("Hello. Welcome to the Starters Savings Savings Bank. We are a bank that caters to "
					+ "young adults to teach them how \n to start saving their money in order to become a fiscally responsible "
					+ "adult in the near future. Here at SSSB we \n cater to people between the ages of 10 and 19. We are the young adult safe banking way. If you are over the age of \n "
					+ "16 you will be able open a checking account "
					+ "with us as well. \n"
					+ "If you would like to continue and open an account with us, or already have an account please press '1'. \n "
					+ "If not press any other key to exit our page.");
			int i = sc.nextInt(); 
			switch(i) {
			case 1:
				System.out.println("If you need to open an account please press '0', if you would like to be directed to the login page please press 1. \n Press any other key to exit.");
				int j = sc.nextInt();
				switch(j) {
				case 0:
					//NewBanker
					
					break;
				case 1: 
					LogIn.runLogIn();
					break;
				default:
					break;
				}
			default: 
				break;
			}
		}
		
			
			}


