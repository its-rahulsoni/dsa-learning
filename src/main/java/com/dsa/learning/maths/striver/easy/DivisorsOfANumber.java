package com.dsa.learning.maths.striver.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * THOUGHT PROCESS:
 * Approach #01: To find all such digits, we can start dividing the concerned number from 2 to (n-1). 1 & n will always be the divisor.
 *
 * Approach #02: When we divide an original number by a number, then the remainder of that division result is also a number that divides the original number.
 *               Now, the question is till where do we need to test for divisors?
 *               The answer is - until the Square Root of the original number.
 * This property is symmetric about the square root of n by traversing just the first half we can avoid redundant iteration and computations improving the efficiency of the algorithm.
 */
public class DivisorsOfANumber {

    public static void main(String[] args) {
        System.out.println("Divisors form Approach #01: " + approach1(40));
        System.out.println("\n*****************************\n");
        System.out.println("Divisors form Approach #02: " + approach2(40));

        System.out.println("sq rt: " + Math.sqrt(50));
    }

    public static List<Integer> approach1(int num){
        List<Integer> divisorsList = new ArrayList<>();
        divisorsList.add(1);

        for(int i=2; i< num-1; i++){
            if(num % i == 0){
                divisorsList.add(i);
            }
        }

        divisorsList.add(num);

        return divisorsList;
    }

    public static List<Integer> approach2(int num){
        List<Integer> divisorsList = new ArrayList<>();
        divisorsList.add(1);

        int squareRoot = (int)Math.sqrt(num);

        for(int i=2; i<= squareRoot; i++){
            if(num % i == 0){
                divisorsList.add(i);
                divisorsList.add(num/i);
            }
        }

        divisorsList.add(num);

        // Now to sort the results, we can SORT the list using Java's in-built function.
        Collections.sort(divisorsList);

        return divisorsList;
    }
}

/**
 * Time Complexity:
 *
 * For sorting:     O(k * log(k))
 * For Square Root: O(Sqrt(n))
 *
 * Final TC: O(Sqrt(n)) + O(k * log(k)) -> O(1) + O(k * log(k)) -> O(k * log(k))
 *
 * TC for O(Sqrt(n)):
 * Theoretical: O(log(log(N)))
 * Practical: O(1) - Constant
 */
