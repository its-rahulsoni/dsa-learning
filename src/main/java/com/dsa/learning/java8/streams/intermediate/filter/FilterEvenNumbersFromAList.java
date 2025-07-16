package com.dsa.learning.java8.streams.intermediate.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterEvenNumbersFromAList {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

        /**
         * filter(): This line applies the filter operation with a predicate that removes out all the Odd numbers and keeps only the Even numbers.
         *
         * collect(): This line Collect the results from above filter method and returns them into a new list ....
         */
        List<Integer> output = input.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Output containing only EVEN integers: " + output);

    }

}
