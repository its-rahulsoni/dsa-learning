package com.dsa.learning.maths.striver.easy;

/**
 * An armstrong number is a number which is equal to the sum of the digits of the number, raised to the power of the number of digits.
 *
 * THOUGHT PROCESS:
 * First, we find out the no of digits in this number.
 * Then, we separate out each digit in the number and calculate the Power of this no to the no of digits of the original no (calculated above).
 * Now, we sum the power of all such individual digits and this is our Calculated No.
 * Finally, we compare these 2 numbers for being Armstrong.
 */
public class ArmstrongNumber {

    public static void main(String[] args) {
        int number = 153;

        int noOfDigits = CountAllDigitsOfANumber.calculateDigits(number);

        int armNo = calculateArmstrongNumber(number, noOfDigits);

        if(number == armNo){
            System.out.println("ARMSTRONG NUMBER!");
        } else {
            System.out.println("NOT a ARMSTRONG NUMBER!");
        }
    }

    public static int calculateArmstrongNumber(int number, int digitsCount){
        int armNo = 0;
        int tempNo;

        while(number > 0){
            tempNo = number % 10;
            number = number / 10;

            armNo = armNo + (int)Math.pow(tempNo, digitsCount);
        }
        System.out.println("Armstrong No calculated: " + armNo);
        return armNo;
    }

}
