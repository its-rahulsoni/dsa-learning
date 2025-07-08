package com.dsa.learning.string.striver.easy;

/**
 * THOUGHT PROCESS:
 * We're converting the string into a character array and using just 1-LOOP, we're replacing characters starting from 1st index and last index using 2 pointer approach.
 * Then we increment the 1st pointer(i) and decrement the 2nd pointer (j).
 */
public class ReverseAString {
    public static void main(String[] args) {
        String name = "RAHUL-SONI";
        String reversedString = reverseString(name);
        System.out.println("Reversed String is: " + reversedString);
    }

    public static String reverseString(String str){
        char[] charArray = str.toCharArray();
        int arrLength = charArray.length;
        int i = 0;
        int j = arrLength - 1;
        char temp;

        do {
            temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }while(i<j);

        return String.valueOf(charArray);
        // return charArray.toString(); Will not work, reason is mentioned in CharacterReadMe.md file ....
    }
}
