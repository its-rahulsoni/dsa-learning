package com.dsa.learning.java8.streams.functional_interfaces;

import java.util.function.BiPredicate;

/**
 * How to Use BiPredicate Functional Interface?
 *
 * Problem:
 * Check if the sum of two numbers is greater than 10 using BiPredicate.
 *
 * Explanation (Line by Line):
 * BiPredicate<T, U>:
 * A predicate for evaluating conditions with two arguments (T and U).
 *
 * Lambda Expression ((num1, num2) -> (num1 + num2) > 10):
 * Checks if the sum of num1 + num2 exceeds 10.
 *
 * sumGreaterThan10.test(...):
 *
 * Applies the predicate (5 + 6 = 11 → true).
 */
public class BiPredicateFunctionalInterface {

    public static void main(String[] args) {

        BiPredicate<Integer, Integer> sumGreaterThan10 = (n1, n2) -> (n1 + n2) > 10;

        if(sumGreaterThan10.test(2,3)){
            System.out.println("Sum is Greater than 10");
        } else {
            System.out.println("Sum is Less than 10");
        }
    }

}
