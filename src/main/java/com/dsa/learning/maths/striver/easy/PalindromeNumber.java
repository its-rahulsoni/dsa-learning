package com.dsa.learning.maths.striver.easy;

/**
 * A palindrome number is a number which reads the same both left to right and right to left. Ex: 121, 101, 949, 767.
 *
 * THOUGHT PROCESS:
 * To check for pallindrome, we should Reverse the initial number.
 *
 * REVERSING THE NUMBER:
 * This can be achieved by dividing the number by 10.
 * We can then multiply the new number by 10 and add this fetched digit to it.
 * The main concept here is - when a new digit is added to a number, its added at the last of it, i.e. we can say at the tenth of the existing number.
 *
 * Now, we can check whether the 2 numbers are equal for Pallindrome.
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println("Is Palindrome Number: " + checkForPalindromeNumber(757));
    }

    public static boolean checkForPalindromeNumber(int number){
        int reversedNumber = reverseTheGivenNumber(number);

        if(reversedNumber == number){
            return true;
        } else {
            return false;
        }
    }

    public static int reverseTheGivenNumber(int number){
        int currentDigit = -1;
        int newNumber = 0;

        while(number > 0){
            currentDigit = number % 10;
            number = number / 10;

            newNumber = newNumber * 10 + currentDigit;
        }

        return newNumber;
    }

    /**
     * Time Complexity:
     * We're reducing the number in our loop by a factor of 10.
     * So, at the kth iteration the final value of the number will be 1 (number > 0, condition).
     *
     * 1 = N/10^k
     * N = 10^k
     * logN = log(10^k)
     * logN = k * log(10)
     * logN = k
     *
     * Hence, the final Time Comlexity will be log(N).
     */

}
