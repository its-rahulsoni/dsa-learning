package com.dsa.learning.java8.functional_interfaces;

import java.util.function.Function;

/**
 * Chaining Function:
 * A Function transforms an input to an output. You can chain multiple Function interfaces using andThen() and compose().
 */
public class CombineMultipleFunctions {

    public static void main(String[] args) {

        // andThen(Function after): Executes the current function, then applies the next function on the result ....
        usingAndThen();

        // compose(Function before): Applies the specified function first, then executes the current function on the result ....
        usingCompose();
    }

    /**
     * andThen: First squares the number (5 * 5 = 25), then doubles it (25 * 2 = 50).
     */
    public static void usingAndThen(){
        System.out.println("**** usingAndThen() ****");

        int number = 3;

        // Function Interface #01 - Computes the square of a number ....
        Function<Integer, Integer> squareFI = num -> num * num; // num -> {return num * num;}; Will also work.

        // Function Interface #02 - Convert integer into a string ....
        Function<Integer, String> intToStringFI = num -> "Sqaure: " + "Output is: " + String.valueOf(num);

        /**
         * Function Composition (andThen):
         *
         * Combines two functions so that the result of the FIRST function becomes the input for the SECOND.
         */
        Function<Integer, String> squareThenStringConversion = squareFI.andThen(intToStringFI);

        // Apply the chained Function ....
        System.out.println("First we square the number: " + number + ", and now we convert it into String for final result: " + squareThenStringConversion.apply(number));

        addDivider();
    }


    /**
     * compose: First doubles the number (5 * 2 = 10), then squares it (10 * 10 = 100).
     */
    public static void usingCompose(){
        System.out.println("**** usingCompose() ****");

        int number = 3;

        // Function Interface #01 - Computes the square of a number ....
        Function<Integer, Integer> squareFI = num -> num * num; // num -> {return num * num;}; Will also work.

        // Function Interface #02 - Double the number ....
        Function<Integer, Integer> doubleValue = n -> n * 2; // Double the number

        /**
         * Function Composition (compose):
         *
         * Combines two functions so that the result of the SECOND function becomes the input for the FIRST.
         */
        Function<Integer, Integer> doubleThenSquare = doubleValue.compose(squareFI);

        // Apply the chained Function ....
        System.out.println("First we double number: " + number + ", and now we Square it for final result: " + doubleThenSquare.apply(number));

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
