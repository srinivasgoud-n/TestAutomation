package org.test.automation.UI.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.FrameWorkException;
import org.testng.annotations.Test;

public class SpanugoTestPage extends BrowserManager {

	private By loginUserName = By.id("username");
	private By loginPassword = By.id("password");
	private By loginSubmitBtn = By.id("kc-login");
	private By dashboardPage = By.xpath("//app-dashboard");

	@Test
	public void login() throws FrameWorkException {
		_Driver.get("https://service-qa.np.spanugo.com/engtest/");
		Helper.waitForPagetoLoad();
		_Driver.findElement(loginUserName).sendKeys("admin");
		_Driver.findElement(loginPassword).sendKeys("admin");
		_Driver.findElement(loginSubmitBtn).click();
		Helper.waitForPagetoLoad();
		Helper.getelement(dashboardPage);
	}

}
