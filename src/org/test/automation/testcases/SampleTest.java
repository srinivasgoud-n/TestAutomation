package org.test.automation.testcases;

import org.test.automation.base.BrowserManager;
import org.testng.annotations.Test;

public class SampleTest extends BrowserManager{
	
	
	@Test
	public void testMethod()
	{
		_Driver.get("http://www.facebook.com");
		
		System.out.println(_Driver.getTitle());
		
		
	}

}
