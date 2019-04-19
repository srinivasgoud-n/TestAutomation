package org.test.automation.UI.testcases;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.exception.FrameWorkException;
import org.test.automation.utils.ReadExcelData;
import org.test.automation.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class GmailTestUsingTestData extends BrowserManager {

	private GmailLoginPage login = new GmailLoginPage();

	private GmailComposePage gmailCompose = new GmailComposePage();

	@Test()
	public void TC_testGmailLogin() throws InterruptedException, FrameWorkException, FileNotFoundException {

		try {

			_Driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		} catch (Exception e) {

		}

		ArrayList<UserData> Data = ReadExcelData.readData(CURRENTDIR+"\\Files\\"+"TestData.xlsx", "Sheet1");
		System.out.println(Data.get(0).getUserName());
		System.out.println(Data.get(0).getPassword());
		login.enterGmailUserName(Data.get(0).getUserName());
		login.clickNextButton();
		login.enterGmailPassword(Data.get(0).getPassword());
		login.clickNextButton();

		Assert.assertTrue(login.verifyComposeButtonIsDisplayed());

	}

	@Test()
	public void TC_testGmailCompose() throws InterruptedException, FrameWorkException, FileNotFoundException {

		TC_testGmailLogin();
		gmailCompose.clickComposeButton();
		gmailCompose.enterEmailIDsInToField();
		gmailCompose.enterSubject();
		gmailCompose.enterEmailBody();
		gmailCompose.clickSendButton();

		Assert.assertTrue(gmailCompose.verifySuccessMessage());

	}


}
