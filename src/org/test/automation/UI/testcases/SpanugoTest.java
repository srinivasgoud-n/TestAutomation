package org.test.automation.UI.testcases;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.GmailException;
import org.testng.annotations.Test;

public class SpanugoTest extends BrowserManager {
	
	SpanugoTestPage page = new SpanugoTestPage();
	
	@Test
	public void testSpanugoApp() throws GmailException
	{
		page.login();
	}

}
