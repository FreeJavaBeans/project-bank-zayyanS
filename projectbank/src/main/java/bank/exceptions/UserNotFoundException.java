package bank.exceptions;

public class UserNotFoundException extends Exception{
	
	public UserNotFoundException() {
		super("User not found. Please try again or create an account.");
	}

}
