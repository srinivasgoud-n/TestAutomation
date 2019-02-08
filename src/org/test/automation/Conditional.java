package org.test.automation;

import java.util.Scanner;

public class Conditional 
{

	public static void main(String[] args)
 {     
// Reading the input from the user and checking the condition
		
		int a;
		Scanner	x = new Scanner(System.in);
		System.out.println("Enter the Value for a");
		a = x.nextInt();
		
/*//Simple If conditional Statement
		
		int a=20;
        if(a < 6)
	    {
	    	System.out.println("Given Number is less than 6");
	    }
        else 
        {
        	System.out.println("Given Number is greater than 6 ");
        }*/
		
		
//Simple if-else
		
		/*if(a < 6)
		{
			System.out.println("Given Number is less than 6");
		}
		else
		{
			System.out.println("Given Number is greater than 6 ");
		}*/
		
		
//if-else if
		
		if(a < 6)
		{
			System.out.println("Given Number is less than 6");
		}
		else if(a>6)
		{
			System.out.println("Given Number is greater than 6 ");
		}
		else if (a==6)
		{
			System.out.println("The same is the given value");
		}
		
//else-if ladder
		
		/*if(a < 6)
		{
			System.out.println("Given Number is less than 6");
		}
		else if(a>6)
		{
			System.out.println("Given Number is greater than 6 ");
		}
		else if (a>10)
		{
			System.out.println("Value is greater than 10");
		}
		else if (a<10)
		{
			System.out.println("Less than 10");
		}
		else if (a==10)
		{
			System.out.println("Reminder is Zero");
		}*/
		
		
	}
 
 }
