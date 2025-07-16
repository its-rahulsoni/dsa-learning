package com.dsa.learning.java8.functional_interfaces;

import java.util.function.UnaryOperator;

/**
 * What is UnaryOperator in Java?
 * UnaryOperator is a specialized functional interface in Java introduced in Java 8. It is part of the java.util.function package and represents an operation
 * that takes a single argument (input) and produces a result of the same type as the argument. It is essentially a specialized version of the
 * Function<T, R> interface for cases where the input and output types are the same (i.e., Function<T, T>).
 *
 * Key Characteristics of UnaryOperator:
 * Single Argument: It operates on one input.
 * Same Input and Output Type: The type of the input must be the same as the type of the result.
 * Functional Interface: It has a single abstract method, so you can use it in lambda expressions or method references.
 */
public class UnaryOperatorImpl {

    public static void main(String[] args) {

        calculateSquareOfNumber();
    }

    public static void calculateSquareOfNumber(){
        System.out.println("**** chainingBiFunctionUsingAndThen() ****");

        int number = 5;
        UnaryOperator<Integer> unaryToSquareNumber = x -> x * x;

        System.out.println("Square of a number using Unary Operator: " + unaryToSquareNumber.apply(number));

    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }
}
