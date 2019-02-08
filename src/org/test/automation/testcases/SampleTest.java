package org.test.automation.testcases;

import java.net.UnknownHostException;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.GmailException;
import org.testng.annotations.Test;

public class SampleTest extends BrowserManager{
	
	
	@Test
	public void testMethod() throws UnknownHostException, GmailException
	{
		_Driver.get("http://www.facebook.com");
		
		System.out.println(_Driver.getTitle());
		takeFullSnapShot();
		
		
	}

}
