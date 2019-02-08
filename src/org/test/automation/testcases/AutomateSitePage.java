package org.test.automation.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.GmailException;

public class AutomateSitePage extends BrowserManager{
	
	
	By fileUploadLink = By.linkText("File Upload");
	By uploadButton = By.id("file-upload");
	By checkBoxesLink = By.linkText("Checkboxes");
	By inputBoxes = By.cssSelector("#checkboxes input");
	
	public void clickFileUploadLink() throws GmailException
	{
		Helper.click(fileUploadLink);
	}
	
	public boolean verifyUploadButton() throws GmailException
	{
		return Helper.isElementDisplayed(uploadButton);
	}
	
	public void clickCheckBoxesLink() throws GmailException
	{
		Helper.click(checkBoxesLink);
	}
	
	public boolean VerifyCheckBoxSelected() throws GmailException
	{
		boolean flag = false;
		List<WebElement> elements = Helper.getelements(inputBoxes);
		flag = elements.get(1).isSelected();
		return flag;
	}
	
	

}
