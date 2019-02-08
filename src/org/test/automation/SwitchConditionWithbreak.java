package org.test.automation;

import java.util.Scanner;

public class SwitchConditionWithbreak {

	public static void main(String[] args)
	
	{
      String car;
    
      Scanner c = new Scanner(System.in);
      System.out.println("Enter the name of the Car");
      car = c.nextLine();
      
      switch(car)
      {
      case "Range Rover":
    	  System.out.println("Land Rover make");
    	  break;
      
      case "A6":
    	  System.out.println("Audi make");
    	  break;
    	  
      case "Fortuner":
    	  System.out.println("Toyota make");
    	  break;
      }


	}

}
