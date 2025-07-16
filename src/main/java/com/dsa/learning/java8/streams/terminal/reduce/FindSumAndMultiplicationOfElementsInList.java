package com.dsa.learning.java8.streams.terminal.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * What is reduce()?
 * The reduce() method performs a reduction (aggregation) on the stream’s elements using:
 *
 * An identity value (optional starting point).
 * An accumulator function (to combine elements).
 */
public class FindSumAndMultiplicationOfElementsInList {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

        System.out.println("Sum of all array elements: " + calculateSum(input));
        System.out.println("Mult of all array elements: " + calculateMult(input));
    }

    public static int calculateSum(List<Integer> input){
        // Reduce with identity value 0 (initial sum)
        int output1 = input.stream()
                .reduce(0, (a,b) -> a + b); // // Combine elements with sum using Lambda Exp ....

        //System.out.println("Sum of all array elements: " + output1);

        int output2 = input.stream()
                .reduce(0, Integer::sum); // Combine elements using Integer.sum() Method Reference ....

        //System.out.println("Sum of all array elements: " + output2);

        return output1;
    }

    public static int calculateMult(List<Integer> input){
        return input.stream()
                .reduce(1 , (a,b) -> a * b);
    }
}
