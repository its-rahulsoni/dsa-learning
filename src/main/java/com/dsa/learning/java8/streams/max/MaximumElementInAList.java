package com.dsa.learning.java8.streams.max;

import java.util.*;

public class MaximumElementInAList {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

        usingMethodReference(input);
        usingLambdaExpression(input);
    }

    public static void usingMethodReference(List<Integer> input){
        System.out.println("********** Method Reference **********");
        Optional<Integer> maxNumber = input.stream()
                .max(Comparator.comparingInt(Integer::intValue));

        System.out.println("Max element using Method Reference: " + maxNumber.get());
    }

    public static void usingLambdaExpression(List<Integer> input){
        System.out.println("\n********** Lambda Expression **********");
        /**
         * 1. Use max directly with Comparator.naturalOrder():
         * If you're working with simple types like integers, you can use Comparator.naturalOrder() to compare directly.
         * This uses the built-in natural ordering of integers to find the maximum.
         */
        Optional<Integer> maxNumber1 = input.stream()
                .max(Comparator.naturalOrder());
        System.out.println("Max element using 1.Using Comparator.naturalOrder: " + maxNumber1.get());

        /**
         * 2. Use a Custom Comparator for More Complex Comparisons:
         * When working with objects (custom types), you need to map the property to be compared.
         */
        Optional<Integer> maxNumber2 = input.stream()
                .max((a, b) -> Integer.compare(a, b));
        System.out.println("Max element using 2.Using Custom Comparator: " + maxNumber2.get());

        /**
         * 3. Use Comparator.comparingInt for Object Properties:
         * When working with objects (custom types), you need to map the property to be compared.
         */
        Optional<Integer> maxNumber3 = input.stream()
                .max(Comparator.comparingInt(a -> a));

        System.out.println("Max element using 3.Using Comparator.comparingInt: " + maxNumber3.get());
    }
}
