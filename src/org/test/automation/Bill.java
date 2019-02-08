package org.test.automation;

//Inheritance Class


public class Bill extends Party 

{
	void Add(int a, int b)
	{
	   int c = a+b;
		System.out.println("From Sub Class " + c);
		
	}
  public static void main(String[] args)
  
  {
		Party a = new Party();
		a.eggie();
		a.nonveg();
		a.veggie();
		System.out.println(a.biryani);
		
						
		//Overloading
		a.eggie(5, "Praveen");
//System.out.println(a);  
		
		//overriding
		a.Add(10, 3);  
		
		Bill p1 = new Bill();
		p1.Add(12, 13);
		
		
  }

}
