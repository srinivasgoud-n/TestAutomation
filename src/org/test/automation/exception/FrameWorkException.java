package org.test.automation.exception;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class FrameWorkException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String failureMessage = "";
	
	public FrameWorkException(String message)
	{
		super(message);
		
		failureMessage = message;
		
	}

}
