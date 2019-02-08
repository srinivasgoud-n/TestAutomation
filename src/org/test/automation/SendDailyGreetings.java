package org.test.automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.test.automation.exception.GmailException;
import org.test.automation.utils.DateUtils;
import org.test.automation.utils.SendInlineImagesInEmail;
import org.test.automation.utils.TimeUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendDailyGreetings {
	
//	String toField = "\"Srinivas Goud Nakka\"<snakka@innominds.com>,\"Pradeep Shivaraju\" <pshivaraju@innominds.com>";
	String toField = "";
	String CCField = "";
	String BCCField = "Srinivas Goud Nakka<snakka@innominds.com>";
	ArrayList<String> list = new ArrayList<>();
	
	public static int getRandomValueIn(int range) throws GmailException {
		Random rand = new Random();
		int r = 1;
		r = rand.nextInt(range);
		if (r == 0) {
			r = rand.nextInt(range);
		}
		boolean res = r <= range;
		Assert.assertTrue(res);
		System.out.println(r);
		return r;
	}
	
	@Test
	public void sendGreetings()throws GmailException, IOException
	{
		list.add("1.jpg");
		list.add("2.jpg");
		list.add("3.jpg");
		SendInlineImagesInEmail.sendGreetings(TimeUtils.getMessageBasedonTime()+" Have a Nice "+DateUtils.getDay_Week(), toField, CCField, BCCField,System.getProperty("user.dir")+"//Files//"+list.get(getRandomValueIn(list.size())));
		
	}

}
