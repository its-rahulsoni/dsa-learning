package com.dsa.learning.maths.striver.easy;

/**
 * THOUGHT PROCESS:
 */
public class FactorialOfANumber {

    public static void main(String[] args) {
        System.out.println("Approach #01: " + approach1(3));
    }

    /**
     * Time Complexity: O(N)
     */
    public static int approach1(int num){
        int result = 1;

        for(int i=1;i<=num;i++){
            result = result * i;
        }

        return result;
    }
}
