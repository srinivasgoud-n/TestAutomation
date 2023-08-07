package org.test.automation.utils;

public class RemoveSecondHighestNumber {
	
	static int[] numbers = {5, 8, 2, 10, 9, 3, 7};
    public static void main(String[] args) {
        
        
        System.out.println(numbers.length);
        
        while(numbers.length>2)
        {

        // Find the highest and second highest numbers
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (number > highest) {
                secondHighest = highest;
                highest = number;
            } else if (number > secondHighest && number < highest) {
            	
            	
                secondHighest = number;
                System.out.println("secondHighest: "+secondHighest);
            }
        }

        // Remove the second highest number from the array
        numbers = removeNumber(numbers, secondHighest);

        // Print the updated array
        System.out.print("Updated Array:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.print("\n");
        }
    }

    // Method to remove a specific number from an array
    private static int[] removeNumber(int[] array, int numberToRemove) {
        int[] result = new int[array.length - 1];
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != numberToRemove) {
                result[index] = array[i];
                index++;
            }
        }

        return result;
    }
}
