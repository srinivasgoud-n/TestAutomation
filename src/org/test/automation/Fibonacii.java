package org.test.automation;

import java.util.Scanner;

public class Fibonacii {

	public static void main(String[] args) 
	{
		
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the valyue of n");
		int n = x.nextInt();
		int a=0; int b=1; int c;
		System.out.print(a + " ");
		System.out.print(b + " ");
		c = a+b;	
		System.out.print(c + " ");
		for(int i=2;i<=n;i++)
		{
			a=b;
			b=c;
			c = a+b;
			System.out.print(c + " ");
		}

	}

}
