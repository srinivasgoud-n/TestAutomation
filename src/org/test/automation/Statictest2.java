package org.test.automation;

public class Statictest2 {
	
public static void main(String[] args) {
		int a=101;
		
		System.out.println(a);
		
		Statictest1.a =23;
        
		System.out.println(Statictest1.a);

	}

}
