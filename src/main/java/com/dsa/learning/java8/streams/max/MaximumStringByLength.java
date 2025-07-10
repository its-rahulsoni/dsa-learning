package com.dsa.learning.java8.streams.max;

import java.util.*;

public class MaximumStringByLength {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("rahul", "akash","mubarik","sagar","manoj"));

        Optional<String> output = input.stream()
                .max((a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Max length String: " + output.get());
    }
}
