package com.dsa.learning.maths.striver;

/**
 * THOUGHT PROCESS:
 * To count digits, we should separate out all the digits from the initial number.
 * This can be achieved by dividing the number by 10.
 * We can then count the number of steps required till be get 0 as left over number.
 */
public class CountAllDigitsOfANumber {

    public static void main(String[] args) {
        System.out.println("Total number of digits: " + calculateDigits(123));
    }

    public static int calculateDigits(int number){
        int count = 0;

        while(number > 0){
            number = number / 10;
            count++;
        }

        return count;
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
