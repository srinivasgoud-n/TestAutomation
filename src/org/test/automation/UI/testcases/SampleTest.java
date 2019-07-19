package org.test.automation.UI.testcases;

import java.io.File;
import java.net.UnknownHostException;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.FrameWorkException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.*;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class SampleTest extends BrowserManager{
	
	ExtentReports extent;
	ExtentTest logger;
	
	
	@Test
	public void testMethod() throws UnknownHostException, FrameWorkException
	{
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		_Driver.get("https://service-qa.np.spanugo.com/innominds");
		
		System.out.println(_Driver.getTitle());
		takeFullSnapShot();
		
		 extent
         .addSystemInfo("Host Name", "SoftwareTestingMaterial")
         .addSystemInfo("Environment", "Automation Testing")
         .addSystemInfo("User Name", "Rajkumar SM");
         //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
         //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
         extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
         
         logger = extent.startTest("passTest");
         Assert.assertTrue(true);
         //To generate the log when the test case is passed
         logger.log(LogStatus.PASS, "Test Case Passed is passTest");
		
		
		
		
		
	}

}
