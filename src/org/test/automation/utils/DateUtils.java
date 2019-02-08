package org.test.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.test.automation.exception.GmailException;


public class DateUtils {
	
	private static final String DATE_FORMAT_NOW = "dd-MM-yyyy-hh-mm-ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	
	
	public static String DateTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		return sdf.format(date);// returns date in dd-MM-yyyy HH:mm:ss
								// format eg:12-02-2012 12:34:42
	}
	
	public static String getDay_Week() throws GmailException {
		String day_of_week = "";
		Calendar now = Calendar.getInstance();
		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
				"Saturday" };
		day_of_week = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
		if(day_of_week=="Monday")
		{
			day_of_week = day_of_week + "!!! How was your weekend ??";
		}
		else if(day_of_week=="Friday"||day_of_week=="Saturday"||day_of_week=="Sunday")
		{
			day_of_week = day_of_week + "!!! Have a Nice Weekend !!!";
		}
		return day_of_week ;
	}

}
