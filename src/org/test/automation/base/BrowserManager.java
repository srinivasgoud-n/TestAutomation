package org.test.automation.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.test.automation.constants.BrowserTYPE;
import org.test.automation.exception.GmailException;
import org.test.automation.utils.ChartGenerator;
import org.test.automation.utils.DateUtils;
import org.test.automation.utils.FileUtility;
import org.test.automation.utils.PropertyReader;
import org.test.automation.utils.ReportGenerator;
import org.test.automation.utils.SendEmail;
import org.test.automation.utils.TimeUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BrowserManager {

	protected static final String fs = Helper.getFileSeparator();
	protected static WebDriver _Driver = null;
	protected static String CURRENTDIR = System.getProperty("user.dir");
	public static Logger log = Logger.getLogger(BrowserManager.class);
	protected static Properties props;
	private static String MODULENAME = "";

	protected static final String TCPASSED = "PASSED";
	protected static final String TCFAILED = "FAILED";
	protected static final String TCSKIPPED = "SKIPPED";

	private static String startTime = "";
	private static String endTime = "";

	private static String chartName = "";
	private static String baseURL = "";

	protected static final String DATE_FORMAT_NOW = "dd-MM-yyyy-hh-mm-ss";
	protected static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

	private static ArrayList<String> startTimeList = new ArrayList<String>();
	private static ArrayList<String> endTimeList = new ArrayList<String>();
	private static ArrayList<String> exceptionList = new ArrayList<String>();
	private static ArrayList<String> modulesList = new ArrayList<String>();
	private static ArrayList<String> TCList = new ArrayList<String>();
	private static ArrayList<Integer> passedList = new ArrayList<Integer>();
	private static ArrayList<Integer> failedList = new ArrayList<Integer>();
	private static ArrayList<Integer> skippedList = new ArrayList<Integer>();
	private static ArrayList<Integer> totalList = new ArrayList<Integer>();
	private static ArrayList<String> totalTimeList = new ArrayList<String>();
	private static ArrayList<String> snapShotList = new ArrayList<String>();
	private static ArrayList<String> tcNameList = new ArrayList<>();
	private static ArrayList<String> exeStatusList = new ArrayList<>();

	private int passedCount1;
	private int failedCount1;
	private int skippedCount1;
	private int totalCount1;

	private int totalpassedCount;
	private int totalfailedCount;
	private int totalskippedCount;
	private int grandTotalCount;

	@BeforeClass
	public void init() throws IOException {

		File logsFolder = new File(CURRENTDIR + "//logs");
		File reportsDir = new File(CURRENTDIR + "/Snapshots");

		if (!logsFolder.exists()) {
			logsFolder.mkdirs();
		}

		if (!reportsDir.exists()) {
			reportsDir.mkdirs();
		}

		PropertyConfigurator.configure("log4j.properties");
		startTime = DateUtils.DateTime();
		startTimeList.add(startTime);
		log.info("Execution Started at " + startTime);
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("CHROME") String browserName) throws Exception {

		startBrowser("CHROME");
		navigateToURL(PropertyReader.getProperty("baseURL"));

	}

	protected static void navigateToURL(String url) {

		_Driver.get(url);
		Helper.waitForPageLoaded();

	}

	private WebDriver startBrowser(String browserName) throws GmailException {

		switch (browserName) {
		case "CHROME":
			// killProcess("CHROME");
			if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
				log.info("Executing on Windows machine...");
				System.setProperty("webdriver.chrome.driver", CURRENTDIR + fs+ "ExecutableDrivers"+fs+"chromedriver.exe");
			} else if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
				log.info("Executing on MAC machine...");
				System.setProperty("webdriver.chrome.driver", CURRENTDIR + fs+"ExecutableDrivers"+fs+"chromedriver_MAC");
			} else if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
				log.info("Executing on Linux machine...");
				System.setProperty("webdriver.chrome.driver", CURRENTDIR + fs+"ExecutableDrivers"+fs+"chromedriver_Linux");
			}
			log.info("Launching Chrome Browser...");
			_Driver = new ChromeDriver();

			break;
		case "FIREFOX":
			// killProcess("FIREFOX");
			System.setProperty("webdriver.gecko.driver", CURRENTDIR + fs+"ExecutableDrivers"+fs+"geckodriver.exe");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			capabilities.setCapability("acceptInsecureCerts", true);
			log.info("Launching Firefox Browser...");
			_Driver = new FirefoxDriver();
			break;
		default:
			throw new GmailException("Invalid browserName");
		}
		_Driver.manage().window().maximize();
		return _Driver;

	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult, ITestContext context) throws UnknownHostException, Exception {
		String s[] = iTestResult.getName().split(" ");

		System.out.println(s[0]);

		if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			log.info("####Test Case PASSED:" + s[0]);
			exeStatusList.add("PASSED");
		} else if (iTestResult.getStatus() == ITestResult.FAILURE) {
			String txt = takeFullSnapShot();
			log.info("####Test Case FAILED:" + s[0]);
			TCList.add(s[0]);
			exeStatusList.add("FAILED");
			exceptionList.add(getModuleName() + "::" + getTestCaseNameFromScript(s[0]) + "::"
					+ GmailException.failureMessage + "::" + txt);
		}

		else {
			log.info("####Test Case SKIPPED:" + s[0]);
			exeStatusList.add("SKIPPED");

		}

		_Driver.close();
		_Driver.quit();

	}

	@AfterClass
	public void prepareTestReport(ITestContext context) throws Exception {

		for (int i = 0; i < context.getAllTestMethods().length; i++) {
			if (context.getAllTestMethods()[i].getCurrentInvocationCount() > 1) {
				if (context.getFailedTests().getResults(context.getAllTestMethods()[i]).size() > 1
						|| context.getPassedTests().getResults(context.getAllTestMethods()[i]).size() == 1) {

					context.getFailedTests().removeResult(context.getAllTestMethods()[i]);
				}
			}
		}

		String className = this.getClass().getName();
		String myText = className.replace(".", "%");
		String s[] = myText.split("%");
		MODULENAME = s[s.length - 1];
		System.out.println("ModuleName: " + MODULENAME);

		for (int i = 0; i < context.getAllTestMethods().length; i++) {
			String s2 = context.getAllTestMethods()[i].getMethodName();
			tcNameList.add(s2);
			System.out.println("MODULENAME: " + MODULENAME + "::TCName: " + s2);

		}

		System.out.println("LIST: " + exeStatusList.size());

		String tcDetails = ReportGenerator.TCwriteToHTML(MODULENAME, tcNameList, exeStatusList);

		modulesList.add(s[s.length - 1]);

		System.out.println("Number of Modules Executed So Far: " + modulesList.size());

		System.out.println("Name of the Modules Executed So Far: ");
		for (int i = 0; i < modulesList.size(); i++) {
			System.out.println(modulesList.get(i));
		}

		passedCount1 = passedCount1 + context.getPassedTests().size();
		failedCount1 = failedCount1 + context.getFailedTests().size();
		skippedCount1 = skippedCount1 + context.getSkippedTests().size();
		totalCount1 = passedCount1 + failedCount1 + skippedCount1;

		System.out.println("PASSED: " + passedCount1);
		System.out.println("FAILED: " + failedCount1);
		System.out.println("SKIPPED: " + skippedCount1);
		System.out.println("TOTAL: " + totalCount1);

		passedList.add(passedCount1);
		failedList.add(failedCount1);
		skippedList.add(skippedCount1);
		totalList.add(totalCount1);

		for (int i = 0; i < passedList.size(); i++) {
			totalpassedCount = totalpassedCount + passedList.get(i);
		}
		for (int i = 0; i < failedList.size(); i++) {
			totalfailedCount = totalfailedCount + failedList.get(i);
		}
		for (int i = 0; i < skippedList.size(); i++) {
			totalskippedCount = totalskippedCount + skippedList.get(i);
		}
		grandTotalCount = totalpassedCount + totalfailedCount + totalskippedCount;

		chartName = ChartGenerator.getChart(totalpassedCount, totalfailedCount, totalskippedCount);

		if (baseURL == "") {
			baseURL = PropertyReader.getProperty("baseURL");
		}

		endTime = DateUtils.DateTime();
		endTimeList.add(endTime);

		log.info("Execution Completed at: " + endTime);

		String timeElapsed = TimeUtils.calculateTimeDifference(startTime, endTime);
		totalTimeList.add(timeElapsed);

		log.info("Time Taken for Execution: " + timeElapsed);

		String totalTimeTaken = TimeUtils.calculateTotalTimeTaken(startTimeList, endTimeList);
		log.info("Total Time Taken for Execution: " + totalTimeTaken);

		String reportPath = "";
		reportPath = "<img src=\"" + chartName + "\">";

		ReportGenerator.writeToHTML(getBrowserType(), baseURL, modulesList, TCList, totalList, passedList, failedList,
				skippedList, totalpassedCount, totalfailedCount, totalskippedCount, grandTotalCount, totalTimeList,
				reportPath, exceptionList, snapShotList, totalTimeTaken, TimeUtils.getMessageBasedonTime(), tcDetails);

		String destpath = "";
		destpath = System.getProperty("user.dir") + fs+"TestAutomationReports" +fs+ DateUtils.DateTime();

		FileUtils.copyDirectory(new File(System.getProperty("user.dir") + fs+"Snapshots"+fs), new File(destpath));
		log.info("Folder: " + System.getProperty("user.dir") + fs+"Reports Copied to " + destpath);
		FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir") + fs+"TestReport.html"),
				new File(destpath));

		log.info("File: " + CURRENTDIR + fs+"TestReport.html Copied to " + destpath);
		try {
			FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir") + fs+"testng-failed.xml"),
					new File(destpath));

			log.info("File: " + System.getProperty("user.dir") + fs+"testng-failed.xml Copied to " + destpath);
		} catch (FileNotFoundException fnf) {
			System.out.println(System.getProperty("user.dir") + fs+"testng-failed.xml does not exist");
		}

	}

	@AfterSuite
	public void stop() throws GmailException, IOException {

		if (PropertyReader.getProperty("sendEmail").equals("true")) {
			SendEmail.sendTestReports("Test Execution Results:" + DateUtils.DateTime(),
					PropertyReader.getProperty("ToField"), PropertyReader.getProperty("CCField"),
					PropertyReader.getProperty("BCCField"));
		}

	}

	public void killProcess(String browserName) throws GmailException {

		try {
			switch (browserName) {
			case "CHROME":
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				break;
			case "FIREFOX":
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
				break;
			default:
				throw new GmailException("Invalid browserName");

			}

		} catch (IOException ioe) {
			throw new GmailException(ioe.getMessage());
		}

	}

	public String takeFullSnapShot() throws UnknownHostException, GmailException {

		String fileName = "";
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot) _Driver).getScreenshotAs(OutputType.FILE);
		} catch (Exception snf) {

			snf.printStackTrace();
			log.info("ERROR" + snf.getMessage());
		}
		fileName = "Report_Snapshot_" + getRandomValue() + ".png";
		String path = "";
		path = System.getProperty("user.dir") + fs + "Snapshots" + fs + fileName;
		if (new File(path).exists()) {
			new File(path).mkdirs();
		}
		// log.info("fileName: " + fileName);
		try {
			FileUtility.copyFile(scrFile, new File(System.getProperty("user.dir") + fs + "Snapshots" + fs + fileName));
		} catch (IOException e) {

			e.printStackTrace();
			log.info("ERROR" + e.getMessage());
		}

		path = "<a href=\"" + path + "\">ScreenShot</a>";
		return path;
	}

	public static int getRandomValue() throws GmailException {
		Random rand = new Random();
		int r = 1;
		r = rand.nextInt(9999999);
		System.out.println(r);
		return r;
	}

	public String getModuleName() throws GmailException {
		String className = this.getClass().getName();
		String myText = className.replace(".", "%");
		String s[] = myText.split("%");
		MODULENAME = s[s.length - 1];
		System.out.println("ModuleName: " + MODULENAME);
		return MODULENAME;
	}

	public String getTestCaseNameFromScript(String scriptName) {
		StringBuffer sb = new StringBuffer();
		String parts[] = scriptName.split("_");
		for (int i = 0; i < parts.length; i++) {
			if (i > 1) {
				sb.append(parts[i] + " ");
			}
		}
		return sb.toString().toUpperCase();
	}

	public static String getBrowserType() {
		String browserType = "";
		if (_Driver instanceof ChromeDriver) {
			browserType = BrowserTYPE.CHROME.toString();
		} else if (_Driver instanceof InternetExplorerDriver) {
			browserType = BrowserTYPE.INTERNETEXPLORER.toString();
		} else if (_Driver instanceof FirefoxDriver) {
			browserType = BrowserTYPE.FIREFOX.toString();
		} else if (_Driver instanceof SafariDriver) {
			browserType = BrowserTYPE.SAFARI.toString();
		}
		System.out.println(browserType);
		return browserType;
	}

	public static void main(String args[]) throws Exception {
//		FileUtility.cleanFolder(System.getProperty("user.dir") + "\\TestAutomationReports");
//		FileUtility.cleanFolder(System.getProperty("user.dir") + "\\SnapShots");
//		FileUtility.deleteFolder(System.getProperty("user.dir") + "\\test-output");
		System.out.println(System.getProperty("os.name"));
	}

}
