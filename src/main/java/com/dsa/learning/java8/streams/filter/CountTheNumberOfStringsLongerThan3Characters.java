package com.dsa.learning.java8.streams.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountTheNumberOfStringsLongerThan3Characters {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("rahul", "akash","mubarik","sagar","manoj","rs","a"));

        List<String> output = input.stream()
                .filter(a -> a.length() > 3)
                .collect(Collectors.toList());

        // Use filter + count to identify strings longer than 3 characters ....
        long count = input.stream()
                .filter(a -> a.length() > 3)
                .count();

        System.out.println("Using filter() - Strings greater than 3 chars, Count: " + count + " List: " + output);
    }
}
