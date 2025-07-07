package com.dsa.learning.maths.striver.easy;

/**
 * THOUGHT PROCESS:
 * To find LARGEST digit, we should separate out all the digits from the initial number.
 * This can be achieved by dividing the number by 10 - to reduce the original number by 1 digit in each iteration.
 * We can then check each digit for it being largest.
 */
public class LargestDigitInANumber {

    public static void main(String[] args) {
        System.out.println("Largest Digit: " + calculateOddDigits(123783));
    }

    public static int calculateOddDigits(int number){
        int largestDigit = 0;
        int currentDigit;

        while(number > 0){
            currentDigit = number % 10;
            number = number / 10;

            if(currentDigit > largestDigit){
                largestDigit = currentDigit;
            }
        }

        return largestDigit;
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
