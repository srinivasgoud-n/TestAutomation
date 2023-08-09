package org.test.automation.pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.FrameWorkException;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class GmailLoginPage extends BrowserManager {

	By usernameTextField = By.id("identifierId");

	By nextButton = By.xpath("//span[text()='Next']");

	By passwordTextField = By.name("password");
	
	By gmailComposeButton = By.xpath("//*[text()='COMPOSE']");

	public void enterGmailUserName(String sUsername) throws FrameWorkException, FileNotFoundException {
		log.info("Enter username");
		Helper.enterText(usernameTextField, sUsername);
	}

	public void clickNextButton() throws FrameWorkException {
		log.info("click next button");
		Helper.click(nextButton);
	}

	public void enterGmailPassword(String sPassword) throws FrameWorkException, FileNotFoundException {
		log.info("Enter password");
		Helper.enterText(passwordTextField, sPassword);
	}
	
	public boolean verifyComposeButtonIsDisplayed() throws FrameWorkException
	{
		log.info("Check whether Gmail Compose button is displayed");
		return Helper.isElementDisplayed(gmailComposeButton);
	}

}
