package org.test.automation;

import java.util.Scanner;

public class TestSample
{
   public static void main(String[] args)
   {
	   Scanner x = new Scanner(System.in);
	   System.out.println("New the number");
	   int n = x.nextInt();
	   
	   for(int i=n;i>=1;i--)
	   {
		 System.out.print(i+ " ");
	   }   
}
}
