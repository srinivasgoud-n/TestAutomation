package org.test.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.FrameWorkException;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class AutomateSitePage extends BrowserManager{
	
	
	By fileUploadLink = By.linkText("File Upload");
	By uploadButton = By.id("file-upload");
	By checkBoxesLink = By.linkText("Checkboxes");
	By inputBoxes = By.cssSelector("#checkboxes input");
	
	public void clickFileUploadLink() throws FrameWorkException
	{
		Helper.click(fileUploadLink);
	}
	
	public boolean verifyUploadButton() throws FrameWorkException
	{
		return Helper.isElementDisplayed(uploadButton);
	}
	
	public void clickCheckBoxesLink() throws FrameWorkException
	{
		Helper.click(checkBoxesLink);
	}
	
	public boolean VerifyCheckBoxSelected() throws FrameWorkException
	{
		boolean flag = false;
		List<WebElement> elements = Helper.getelements(inputBoxes);
		flag = elements.get(1).isSelected();
		return flag;
	}
	
	

}
