package bank.user.hub;

import bank.exceptions.UserSignUpFailedException;

public interface UserRegistration {

	
	public void display();
	
	
	public void UserDAO() throws UserSignUpFailedException;
	
	
	
	public void CorrectInfo();
	
	public boolean isValid(String yn);
	
	
	
	
	
}
