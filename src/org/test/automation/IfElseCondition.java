package org.test.automation;

import java.util.Scanner;

public class IfElseCondition {

	public static void main(String[] args) {
		
// Reading the input from the user and checking the condition
		
		int a;
		Scanner	x = new Scanner(System.in);
		System.out.println("Enter the Value for a");
		a = x.nextInt();
		
		if(a < 6)
		{
			System.out.println("Given Number is less than 6");
		}
		else
		{
			System.out.println("Given Number is greater than 6 ");
		}

	}

}
