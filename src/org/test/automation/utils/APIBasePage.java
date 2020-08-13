package org.test.automation.utils;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class APIBasePage {
	protected DateTimeFormatter dtf;
	protected LocalDateTime now;
	protected ExtentReports extent;
	protected static ExtentTest test;
	protected static String api_url, access_token, tenant_id, reportHtml, requestBody = null;
	protected int passCount = 0, failCount = 0;
	protected JsonPath jsonPathEvaluator;
	protected static Response response = null;
	protected static final String DATE_FORMAT_NOW = "dd-MM-yyyy-hh-mm-ss";
	protected static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	private static String startTime = "";
	private static String endTime = "";

	@BeforeClass
	public void setup() throws FileNotFoundException {
		startTime = DateUtils.getCurrentTimeStamp();
		reportHtml = "ExtentReports/" + this.getClass().getName() + "-" + startTime + ".html";
		extent = new ExtentReports(reportHtml, false);

		System.out.println("Test Started at: " + startTime);
		extent.addSystemInfo("Execution Started At ", startTime);

		api_url = PropertyReader.getProperty("api_url");

		System.out.println("BASE_URL: " + api_url);

		System.out.println("ACCESS_TOKEN: " + access_token);

		System.out.println("TENANT_ID: " + tenant_id);

		extent.addSystemInfo("Environment", "Test").addSystemInfo("User Name", "Srinivas");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			passCount++;
			test.log(LogStatus.PASS, "Test Case Passed is " + result.getName());

		}

		if (result.getStatus() == ITestResult.FAILURE) {
			failCount++;
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Details of Failed Testcase: " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// Ending the test
		extent.endTest(test);

	}

	@AfterClass
	public void dispose() throws ParseException, Exception {

		// Send Email with the Automation Report
		// Utility.sendMail(reportHtml,passCount,failCount,this.getClass().getSimpleName()+"
		// Report");
		endTime = DateUtils.getCurrentTimeStamp();
		System.out.println("Test Completed at: " + endTime);
		extent.addSystemInfo("Execution Completed At ", endTime);

		String timeElapsed = TimeUtils.calculateTimeDifference(startTime, endTime);

		System.out.println("Time taken for execution: " + timeElapsed);

		extent.addSystemInfo("Time taken for execution", timeElapsed);

		// flush writes everything to the log file
		extent.flush();
		// closing the extent report
		extent.close();
		// Ending the test
		extent.endTest(test);
	}

}
