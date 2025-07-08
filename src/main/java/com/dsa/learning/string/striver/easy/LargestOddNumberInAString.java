package com.dsa.learning.string.striver.easy;

/**
 * PROBLEM: Given a string s, representing a large integer, the task is to return the largest-valued odd integer (as a string)
 * that is a substring of the given string s.
 * The number returned should not have leading zero's. But the given input string may have leading zero.
 *
 * EXAMPLES:
 * Input : s = "5347"
 * Output : "5347"
 * Explanation : The odd numbers formed by given strings are --> 5, 3, 53, 347, 5347.
 * So the largest among all the possible odd numbers for given string is 5347.
 *
 * THOUGHT PROCESS:
 * Approach #01: Brute Force approach, where we use 2 loops to create multiple sub-strings and then check for odd number formed.
 *               And then find the highest ODD number.
 *
 * Approach #02: The highest no formed will be the one starting from the 1st digit upto the last digit.
 *               Now we just need to find the first ODD digit starting from the END.
 *               This string/digit from index 0 till this ODD no index - will be our Output.
 */
public class LargestOddNumberInAString {
    public static void main(String[] args) {
        String str = "5368";
        System.out.println("Largest Odd No using Approach #01: " + approach1(str));
        System.out.println("Largest Odd No using Approach #02: " + approach2(str));
    }

    public static int approach1(String str){
        int output = -1;
        int length = str.length();
        String temp;
        int oddNo;

        for(int i=0;i<length;i++){
            for(int j = i+1;j<length+1;j++){
                // NOTE: The substring method considers end part of string, to be a char at the prev index of j.
                // Ex: If j=4 here, so the 3rd character will be considered as ending string char.
                temp = str.substring(i,j);
                oddNo = Integer.parseInt(temp);
                if(oddNo % 2 != 0){
                    if(oddNo > output){
                        output = oddNo;
                    }
                }
            }
        }

        return output;
    }

    public static int approach2(String str){
        int output = -1;
        char[] arr = str.toCharArray();
        int ch,temp;

        for(int i=str.length()-1; i>=0; i--){
            ch = arr[i] - '0';
            if(ch % 2 == 1){
                temp = Integer.parseInt(str.substring(0, i+1));

                if(temp > output){
                    output = temp;
                }
            }
        }
        return output;
    }
}
