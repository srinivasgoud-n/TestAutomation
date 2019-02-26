package org.test.automation.UI.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.exception.GmailException;
import org.testng.annotations.Test;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class Normalexeflow extends BrowserManager {

	By txtUserName = By.id("username");
	By txtPassword = By.id("password");
	boolean flag = false;

	@Test
	public void TC_1() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void TC_2() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void TC_3() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}
	
	@Test
	public void TC_4() throws InterruptedException, GmailException {

		_Driver.findElement(txtUserName).sendKeys("admin");
		_Driver.findElement(txtPassword).sendKeys("admin");

		Thread.sleep(3000);
	}

	

}
