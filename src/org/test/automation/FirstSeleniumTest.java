package org.test.automation;

/*import org.testng.annotations.Test;

public class FirstSeleniumTest {
	
	
	@Test
	public void testing()
	{
		System.out.println("TESTNG");
		
	}
	
	

}*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
	
	
	public void setup()
	{
		
	}

	@Test
	public void testing() {

		/*
		 * WebDriver driver = new FirefoxDriver();
		 * System.out.println("Hello Google..."); driver.get("http://google.com");
		 */

		String currentDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", currentDir + "\\ExecutableDrivers\\chromedriver.exe");

		// Initialize browser
		WebDriver driver = new ChromeDriver();

		// Open facebook
		driver.get("http://www.facebook.com");

		// Maximize browser

		driver.manage().window().maximize();
	}
	
	public void tearDown()
	{
		
	}
}