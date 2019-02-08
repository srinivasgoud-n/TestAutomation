package org.test.automation.testcases;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.exception.GmailException;
import org.test.automation.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailTest extends BrowserManager {
	
	private GmailLoginPage login = new GmailLoginPage();

	private GmailComposePage gmailCompose = new GmailComposePage();

	@Test()
	public void TC_testGmailLogin() throws InterruptedException, GmailException, FileNotFoundException {

		try {

			_Driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		} catch (Exception e) {

		}

		login.enterGmailUserName(PropertyReader.getProperty("gmailUserName"));
		login.clickNextButton();
		login.enterGmailPassword(PropertyReader.getProperty("gmailPassword"));
		login.clickNextButton();

		Assert.assertTrue(login.verifyComposeButtonIsDisplayed());

	}

	@Test()
	public void TC_testGmailCompose() throws InterruptedException, GmailException, FileNotFoundException {

		TC_testGmailLogin();
		gmailCompose.clickComposeButton();
		gmailCompose.enterEmailIDsInToField();
		gmailCompose.enterSubject();
		gmailCompose.enterEmailBody();
		gmailCompose.clickSendButton();

		Assert.assertTrue(gmailCompose.verifySuccessMessage());

	}
	
	@Test()
	public void TC_testGmailLoginFail() throws InterruptedException, GmailException, FileNotFoundException {

		try {

			_Driver.findElement(By.xpath("//a[text()='Sign In1']")).click();
		} catch (Exception e) {

		}

		login.enterGmailUserName(PropertyReader.getProperty("gmailUserName"));
		login.clickNextButton();
		login.enterGmailPassword(PropertyReader.getProperty("gmailPassword"));
		login.clickNextButton();

		Assert.assertTrue(login.verifyComposeButtonIsDisplayed());

	}

}
