package org.test.automation.UI.testcases;


import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.FrameWorkException;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class CheckForAppChanges extends BrowserManager {

	@Test
	public void validateAppChanges() throws FrameWorkException {
		try {
			By element = By.id("username");
			Helper.getelement(element);

		} catch (FrameWorkException nse) {
			throw new SkipException("Skipping test execution...");
		}
	}

}
