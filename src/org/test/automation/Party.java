package org.test.automation;

// Main Class


public class Party {
	
	//Protected method - accessing in Subclass
	
	protected String biryani = "Dum Biryani";
	
    void nonveg()
	{
	 		System.out.println("I would love to eat Chicken Biryani");
	}
    
	void veggie()
	{
		System.out.println("Fine with Veg Biryani");
	}
	
	void eggie()
	{
		System.out.println("Only Egg Biryani");		
	}
	
	//Overloading method Signature
	void eggie(int p,String Name)
	{
		System.out.println("Only Egg Biryani() overloaded " + p +"," + Name);	
		
	}
	
	//Overriding method
	
	void Add(int a, int b)
	{
	   int c = a+b;
		System.out.println("Main Class " + c);
		
	}

	
}