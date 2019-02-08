package org.test.automation.base;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.automation.exception.GmailException;

public class Helper extends BrowserManager {

	public static int EXPLICIT_TIMEOUT = 30;
	public static boolean flag = false;

	public static WebElement getelement(By locator) throws GmailException {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(_Driver, 15);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element = _Driver.findElement(locator);
		} catch (TimeoutException toe) {
			throw new GmailException("Locator not available: " + locator);
		}

		return element;

	}

	public static List<WebElement> getelements(By locator) throws GmailException {
		List<WebElement> elements = null;

		WebDriverWait wait = new WebDriverWait(_Driver, 5);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			elements = _Driver.findElements(locator);
		} catch (TimeoutException toe) {
			throw new GmailException("Locator not available: " + locator);
		}

		return elements;

	}

	public static void click(By locator) throws GmailException {
		getelement(locator).click();
		waitForPageLoaded();
	}

	public static void enterText(By locator, String value) throws GmailException {
		getelement(locator).sendKeys(value);
	}

	public static boolean isElementDisplayed(By locator) throws GmailException {
		return getelement(locator).isDisplayed();
	}

	public static String getCSSValue(By locator, String attribute) throws GmailException {
		return getelement(locator).getCssValue(attribute);
	}

	public static ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			try {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return window.jQuery != undefined || jQuery.active == 0");
			} catch (Exception e) {
				return true;
			}
		}
	};

	public static ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {

			return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete");
		}
	};
	public static ExpectedCondition<Boolean> xhrLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return ((JavascriptExecutor) driver).executeScript("return XMLHttpRequest.DONE").toString().equals("4");
		}
	};

	static HttpURLConnection con = null;

	public static boolean browserResponse() {
		try {
			HttpURLConnection.setFollowRedirects(true);

			con = (HttpURLConnection) new URL(_Driver.getCurrentUrl()).openConnection();
			con.setRequestMethod("HEAD");

			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	public static void waitForPageLoaded() {
		try {
			// 0.5 second delay beyond which we could say slow performance
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(_Driver, EXPLICIT_TIMEOUT);
			flag = wait.until(jQueryLoad) && wait.until(jsLoad) && wait.until(xhrLoad);
			browserResponse();

		} catch (Exception e) {
			// System.out.println("wait for page to load exception");
			browserResponse();
		}
	}

	public static void navigateBack() {
		_Driver.navigate().back();
		waitForPageLoaded();
	}
	
	public static String getOSType()
	{
		return System.getProperty("os.name");
	}
	
	public static String getFileSeparator()
	{
		String OS = System.getProperty("os.name");
		String fs = "";
		if(OS.equalsIgnoreCase("WINDOWS"))
		{
			fs = "\\";
		}
		else if(OS.equalsIgnoreCase("MAC")||OS.equalsIgnoreCase("LINUX"))
		{
			fs = "//";
		}
		return fs;
	}

}
