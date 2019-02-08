package org.test.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.test.automation.exception.GmailException;


public class GenerateTestNGTestSuite {
	
	public static void generateTestSuite(String className,String suiteName,String testName) throws GmailException, ClassNotFoundException, IOException {
		Class classTemp = Class.forName(className);

		Method[] methods = classTemp.getDeclaredMethods();
		System.out.println(classTemp.getPackage().getName());
		int cnt = 0;
		ArrayList<String> methodsList = new ArrayList<String>();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].toString().contains("TC")) {
				cnt++;
				String txt = methods[i].toString().replace(".", "%");
				System.out.println("txt: " + txt);
				String txt1 = txt.split("%")[5].split(" ")[0].trim().replace(")", "");
				System.out.println("txt1: " + txt1);
				String txt2 = txt1.replace("(", "");
				System.out.println("public method: " + txt2.trim());
				methodsList.add(txt2);
			}
		}

		System.out.println("Methods Available:" + cnt);

		System.out.println(methodsList.size());

		createTestSuite(className, methodsList,suiteName,testName);
	}

	private static void createTestSuite(String className, ArrayList<String> list_Methods, String suiteName, String testName)
			throws GmailException, IOException {

		String txt = className.replace(".", "&");
		String txt1[] = txt.split("&");
		String myText = txt1[txt1.length - 1];

		StringBuffer sb = new StringBuffer();

		sb.append("<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\" >");
		sb.append("<suite name=\"" + suiteName + " Module Test Suite" + "\" thread-count=\"3\"	verbose=\"1\">");
		sb.append("<test name=\"" + testName + " Module Test Cases \"> ");
		sb.append("<classes>");
		sb.append("<class name=\"" + className + "\">");
		sb.append("<methods>");
		Iterator<String> it = list_Methods.iterator();
		while (it.hasNext()) {
			sb.append("<include name=\"" + it.next() + "\" />");
		}
		sb.append("</methods>");
		sb.append("</class>");
		sb.append("</classes>");
		sb.append("</test>");
		sb.append("</suite>");

		String currentDir = System.getProperty("user.dir");
		// String folder =
		// currentDir+"\\Test Suites\\Regression Test Suite\\Teams\\";
		String fileName = currentDir + "\\TestSuites\\" + myText + "_Test_Suite.xml";
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();

		System.out.println("Done");
	}
	
	public static void main(String args[]) throws ClassNotFoundException, GmailException, IOException
	{
		generateTestSuite("org.test.automation.testcases.AutomateTestSite","TestSite Suite","AutomateTestSite Testcase");
	}


}
