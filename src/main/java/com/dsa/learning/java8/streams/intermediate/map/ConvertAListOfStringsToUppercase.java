package com.dsa.learning.java8.streams.intermediate.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertAListOfStringsToUppercase {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("rahul", "akash","mubarik","sagar","manoj"));

        System.out.println("******* Approach #01 ********");

        /**
         * map(): We Use the map operation to transform each string into uppercase using the String::toUpperCase method.
         *        This operation does not modify the original list but applies a transformation.
         *
         * collect(): Combines the processed elements into a new List.
         */
        List<String> outputUsingLambdaExpression = input.stream()
                .map(a -> a.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Output with Uppercase Strings: " + outputUsingLambdaExpression);

        System.out.println("******* Approach #02 ********");

        List<String> outputUsingMethodReference = input.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Output with Uppercase Strings: " + outputUsingMethodReference);


    }

}
