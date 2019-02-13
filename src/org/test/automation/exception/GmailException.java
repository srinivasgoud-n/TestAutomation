package org.test.automation.exception;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class GmailException extends Exception {
	
	public static String failureMessage = "";
	
	public GmailException(String message)
	{
		super(message);
		
		failureMessage = message;
		
	}

}
