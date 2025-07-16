package com.dsa.learning.java8.streams.terminal.collect;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupingByLength {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("cat", "elephant", "dog", "whale");

        Map<Integer, List<String>> output = words.stream()
                .collect(Collectors.groupingBy(a -> a.length()));
        System.out.println("Output: " + output);
    }
}
