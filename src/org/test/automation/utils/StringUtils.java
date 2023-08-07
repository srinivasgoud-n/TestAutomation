package org.test.automation.utils;

import java.util.Random;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.FrameWorkException;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class StringUtils extends BrowserManager{

	public static String toCamelCase(String inputString) {
		String result = "";
		if (inputString.length() == 0) {
			return result;
		}
		char firstChar = inputString.charAt(0);
		char firstCharToUpperCase = Character.toUpperCase(firstChar);
		result = result + firstCharToUpperCase;
		for (int i = 1; i < inputString.length(); i++) {
			char currentChar = inputString.charAt(i);
			char previousChar = inputString.charAt(i - 1);
			if (previousChar == ' ') {
				char currentCharToUpperCase = Character.toUpperCase(currentChar);
				result = result + "_" + currentCharToUpperCase;
			} else {
				char currentCharToLowerCase = Character.toLowerCase(currentChar);
				result = result + "_" + currentCharToLowerCase;
			}
		}
		return result;
	}

	public static int getRandomValue() throws FrameWorkException {
		Random rand = new Random();
		int r = 1;
		r = rand.nextInt(9999999);
		System.out.println(r);
		return r;
	}

	public static String getModuleName(String className) throws FrameWorkException {
		String myText = className.replace(".", "%");
		String s[] = myText.split("%");
		MODULENAME = s[s.length - 1];
		System.out.println("ModuleName: " + MODULENAME);
		return MODULENAME;
	}

	public static String getTestCaseNameFromScript(String scriptName) {
		StringBuffer sb = new StringBuffer();
		String parts[] = scriptName.split("_");
		for (int i = 0; i < parts.length; i++) {
			if (i > 1) {
				sb.append(parts[i] + " ");
			}
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String args[]) {

		System.out.println(toCamelCase("HelloHi"));
	}

}
