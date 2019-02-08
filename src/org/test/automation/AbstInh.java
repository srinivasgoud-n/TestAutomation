package org.test.automation;

public class AbstInh extends AbstClass

{
	void S1()
	{
		System.out.println("This is a abs. class");
	}
public static void main(String[] args)
{
	AbstInh p = new AbstInh();
	
	p.S1();
}
}
