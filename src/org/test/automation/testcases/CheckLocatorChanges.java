package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.GmailException;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class CheckLocatorChanges extends BrowserManager {

	By txtUserName = By.id("username");
	By txtPassword = By.id("password");
	boolean flag = false;

	
	@Test
	public void verify() throws InterruptedException, GmailException {
		System.out.println(txtUserName);
		System.out.println(txtPassword);
		flag = Helper.verifyElementLocator(txtUserName);
		if (flag) {
			_Driver.findElement(txtUserName).sendKeys("admin");
			_Driver.findElement(txtPassword).sendKeys("admin");
		} else {
			throw new SkipException("Locator Changed");
		}
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods= {"verify"})
	public void TC_1() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods= {"TC_1"})
	public void TC_2() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods= {"TC_2"})
	public void TC_3() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods= {"TC_3"})
	public void TC_4() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	

}
