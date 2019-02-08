package org.test.automation;

import java.util.Scanner;

public class PN {
	
	public static void main (String[] args)
	
	{
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n = x.nextInt();
		
		if(n>=0)
		{
		int c=1;
		while(n!=0)
		{
			c = c*n;
			n--;
		}
		System.out.print(c);
	}
		else
		{
			System.out.println("This is a -ve number");
		}
	}
}

	
