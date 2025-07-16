package com.dsa.learning.java8.streams.functional_interfaces;

public class BasicCustomFI {

    public static void main(String[] args) {

        /**
         * We're basically adding the implementation of our custom functional interface's method using lambda.
         * This is same as we would have implemented this interface and then override its method to add an implementation.
         */
        SquareCalculator squareCalculator = no -> no * no;

        // Now, we're invoking/calling this method ....
        int squareRoot = squareCalculator.calculateSquare(10);

        System.out.println("squareRoot: " + squareRoot);
    }

}

@FunctionalInterface
interface SquareCalculator{
    int calculateSquare(int number); // Abstract method for square calculation ....
}
