package bank.exceptions;

public class UserSignUpFailedException extends Exception {

	public UserSignUpFailedException() {
		super("User sign up failed.");
	}
}
