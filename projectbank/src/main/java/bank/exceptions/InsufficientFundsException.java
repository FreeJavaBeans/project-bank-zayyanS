package bank.exceptions;

public class InsufficientFundsException extends Exception {

	public InsufficientFundsException () {
		super("This account does not have to correct funds to complete this action.");
	}
}
