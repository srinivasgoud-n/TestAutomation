package org.test.automation;

import java.util.Scanner;

public class PrimeNumber {
	
public static void main (String[] args)
	
	{
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n = x.nextInt();
		
		for(int i=1;i<=n;i++)
		{
		   boolean flag=true;
		   for(int j=2;j<=i-1;j++)
			{
				if(i%j==0)
				{
					flag = false;
					break;
				}
			}
			if(flag ==true)
			{
				System.out.print(i + " ");
			}
			}
		}
	}
	


		/*public static boolean isPrime(int n) {
		if(n == 1 || n == 0)
		{
		return false;
	    }
		if(n==2)
		{
		return true;
        }
		for(int i=2;i<n;i++) 
		{
		if(n%i==0) 
		{
		return false;
		}
		}
		return true;
		}
		
		public static void main(String args[]) 
		{
			for(int i=1; i<=100; i++) {
			if(isPrime(i)==true) {
			System.out.println(i);
			}
			}
			}
			}
*/