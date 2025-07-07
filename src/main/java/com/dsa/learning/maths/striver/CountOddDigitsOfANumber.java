package com.dsa.learning.maths.striver;

/**
 * THOUGHT PROCESS:
 * To count ODD/EVEN digits, we should separate out all the digits from the initial number.
 * This can be achieved by dividing the number by 10 - to reduce the original number by 1 digit in each iteration and then taking a modulo of this digit to check if its even or odd.
 * We can then count the number of odd/even numbers till be get 0 as left over number.
 */
public class CountOddDigitsOfANumber {

    public static void main(String[] args) {
        System.out.println("Total number of digits: " + calculateOddDigits(123793));
    }

    public static int calculateOddDigits(int number){
        int count = 0;
        int currentDigit = -1;

        while(number > 0){
            currentDigit = number % 10;
            number = number / 10;

            if(currentDigit % 2 != 0){
                count++;
            }
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
