package com.dsa.learning.java8.streams.functional_interfaces;

import java.util.function.Function;

/**
 * How to Chain Functions Using andThen in Functional Interfaces?
 *
 * Problem:
 * Write a program to compute the square of a number and then transform it into a string using chained functions.
 */
public class ChainFunctionsUsingAndThen {

    public static void main(String[] args) {

        // Function Interface #01 - Computes the square of a number ....
        Function<Integer, Integer> squareFI = num -> num * num; // num -> {return num * num;}; Will also work.

        // Function Interface #02 - Convert integer into a string ....
        Function<Integer, String> intToStringFI = num -> "Sqaure: " + "Output is: " + String.valueOf(num);

        /**
         * Function Composition (andThen):
         *
         * Combines two functions so that the result of the first function becomes the input for the second.
         */
        Function<Integer, String> outputFI = squareFI.andThen(intToStringFI);

        // Apply the chained Function
        System.out.println(outputFI.apply(7));
    }

}
