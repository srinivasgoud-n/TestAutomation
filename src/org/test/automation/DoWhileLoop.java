package org.test.automation;

import java.util.Scanner;

public class DoWhileLoop 

{
	public static void main(String[] args)
	
	{
        int a;
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the Value of a");
		a=x.nextInt();
		
		do
		{
			System.out.println("Value is " + a);
			++a;
		}
		while(a<10);
	}

}
