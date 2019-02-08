package org.test.automation;

import java.util.Scanner;

public class SwitchConditionWObreak

{
	public static void main(String[] args)
	
	{
		String car;
		
		Scanner c = new Scanner(System.in);
		System.out.println("Enter the Name of the Car");
		car = c.nextLine();
		
		switch(car)
		{
		case "Swift":
			System.out.println("Maruti Make");
		case "i20":
			System.out.println("Hyundai Make");
		case "Nano":
			System.out.println("Tata Make");
		}

	}

}
