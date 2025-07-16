package com.dsa.learning.java8.functional_interfaces;

import java.util.function.BiFunction;

/**
 * How to Use BiFunction to Compute a Custom Result?
 * Problem:
 * Write a program using BiFunction to add two numbers and return their square as the result.
 *
 * Explanation (Line by Line):
 *
 * BiFunction<T, U, R>:
 * Takes two inputs (Integer, Integer) and produces one output (Integer).
 * Single method: apply(T t, U u).
 *
 * Lambda Expression ((num1, num2) -> ...):
 * Computes the sum of num1 and num2, squares the result ((3 + 4)^2 = 49).
 *
 * apply(i, j):
 * Passes inputs to the lambda for computation.
 */
public class BiFunctionImpl {

    public static void main(String[] args) {

        addTwoNumbersAndThenComputeItsSquare();
    }

    public static void addTwoNumbersAndThenComputeItsSquare(){
        System.out.println("**** addTwoNumbersAndThenComputeItsSquare() ****");

        int i = 4;
        int j = 8;

        BiFunction<Integer, Integer, Integer> addAndSquare = (a, b) -> (a + b) * (a + b);

        int result = addAndSquare.apply(i, j);

        System.out.println("Result of addition adn then square for i: " + i + ", j: " + j + ", result: " + result);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
