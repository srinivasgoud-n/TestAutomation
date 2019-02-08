package org.test.automation;

import java.util.Scanner;

public class Factorial {
	
	//While loop

	{
		Scanner x = new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n = x.nextInt();		
		if(n>=0)
		{
		int fact=1;
		while(n!=0)
		{
			fact = fact*n;
			n--;
			System.out.print(fact);
		}
		
	}
		else
		{
			System.out.println("This is a -ve number");
		}
	}
}

// for loop

/*Scanner x = new Scanner(System.in);
System.out.println("New the number");
int n = x.nextInt();
int fact=1;
for(int i=1; i<=n;i++)
{
	   fact = fact*i;
	   System.out.println(fact);
}   
}
}*/
