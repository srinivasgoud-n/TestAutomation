package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.GmailException;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Normalexeflow extends BrowserManager {

	By txtUserName = By.id("username");
	By txtPassword = By.id("password");
	boolean flag = false;

	@Test
	public void existing1() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void existing2() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void existing3() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void existing4() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	

}
