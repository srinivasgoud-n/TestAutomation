package org.test.automation;

import java.util.Scanner;

public class WhileLoop {

	public static void main(String[] args) 
	
	{
		int a;
		
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the Value of a");
		a=x.nextInt();
		
		while(a%1==0)
		{
			System.out.println("This is divided by 1");
			break;
		

	}
  }
}


