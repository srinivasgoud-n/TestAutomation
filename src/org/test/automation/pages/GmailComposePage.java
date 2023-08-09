package org.test.automation.pages;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.FrameWorkException;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class GmailComposePage extends BrowserManager {

	By composeButton = By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']");

	By toFieldTextBox = By.name("to");

	By subjectTextBox = By.name("subjectbox");

	By mailBody = By.className("editable");

	By sendButton = By.xpath("//*[text()='Send']");

	By successMessage = By.id("link_vsm");

	public void clickComposeButton() throws FrameWorkException {

		log.info("click compose button");
		Helper.click(composeButton);

	}

	public void enterEmailIDsInToField() throws FrameWorkException {
		log.info("enter emailIDs in To field");
		Helper.enterText(toFieldTextBox, "aakula.sudhakar@gmail.com");
	}

	public void enterSubject() throws FrameWorkException {
		log.info("enter subject");
		Helper.enterText(subjectTextBox, "hi");
	}

	public void enterEmailBody() throws FrameWorkException {
		log.info("enter email body");
		Helper.enterText(mailBody, "hi");
	}

	public void clickSendButton() throws FrameWorkException {
		log.info("click send button");
		Helper.click(sendButton);
	}

	public boolean verifySuccessMessage() throws FrameWorkException {
		log.info("Check whether success message is displayed");
		return Helper.isElementDisplayed(successMessage);
	}

}
