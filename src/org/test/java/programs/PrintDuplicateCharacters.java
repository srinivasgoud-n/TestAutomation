package org.test.java.programs;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class PrintDuplicateCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String text = "Hello World !";
		String str = "beautiful beach";
		char[] arr = text.toCharArray();
		
		HashMap<Character, Integer> charCountMap
        = new HashMap<Character, Integer>();
		
		for(int i=0;i<text.length();i++)
		{
			for(int j=i+1;j<text.length();j++)
//			System.out.println(i+": "+text.charAt(i));
			if(arr[i] == arr[j])
			{
//				System.out.println(arr[j]);
			}
		}
		
		for(char c:arr)
		{
			if(charCountMap.containsKey(c))
			{
				charCountMap.put(c, charCountMap.get(c)+1);
			}
			else
			{
				charCountMap.put(c, 1);
			}
		}
		
//		// Print no of occurences of each char
//		for (Map.Entry entry : charCountMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
		
		for(int i=0;i<text.length();i++)
		{
			for(int j=i+1;j<text.length();j++)
//			System.out.println(i+": "+text.charAt(i));
			if(arr[i] == arr[j])
			{
				
			}
		}

	}

}
