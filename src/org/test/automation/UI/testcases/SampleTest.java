package org.test.automation.UI.testcases;

import java.net.UnknownHostException;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.FrameWorkException;
import org.testng.annotations.Test;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class SampleTest extends BrowserManager{
	
	
	@Test
	public void testMethod() throws UnknownHostException, FrameWorkException
	{
		_Driver.get("http://www.facebook.com");
		
		System.out.println(_Driver.getTitle());
		takeFullSnapShot();
		
		
	}

}
