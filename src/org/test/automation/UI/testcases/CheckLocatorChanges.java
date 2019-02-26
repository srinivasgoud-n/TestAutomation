package org.test.automation.UI.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.GmailException;
import org.testng.annotations.Test;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class CheckLocatorChanges extends BrowserManager {

	By txtUserName = By.id("username");
	By txtPassword = By.id("password");

	@Test
	public void verify() throws InterruptedException, GmailException {
		Helper.enterText(txtUserName, "admin");
		Helper.enterText(txtPassword, "admin");
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "verify" })
	public void TC_1() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "verify" })
	public void TC_2() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "verify" })
	public void TC_3() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "verify" })
	public void TC_4() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

}
