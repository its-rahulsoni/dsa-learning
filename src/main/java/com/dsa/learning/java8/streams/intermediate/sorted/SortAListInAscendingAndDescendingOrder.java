package com.dsa.learning.java8.streams.intermediate.sorted;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortAListInAscendingAndDescendingOrder {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 3, 1);

        List<Integer> output = numbers.stream()
                .sorted()
                .toList();
        System.out.println("Using just sorted(): " + output);

        System.out.println("-----------------------------");
        List<Integer> output2 = numbers.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("Using Comparator's Natural Ordering with sorted(): " + output2);

        System.out.println("-----------------------------");
        List<Integer> output3 = numbers.stream()
                .sorted((a, b) -> Integer.compare(a, b))
                .toList();
        System.out.println("Using Comparator with (a, b) -> Integer.compare(a, b) with sorted(): " + output3);

        System.out.println("-----------------------------");
        List<Integer> output4 = numbers.stream()
                .sorted(Comparator.comparingInt(a -> a))
                .toList();
        System.out.println("Using Comparator with Comparator.comparingInt(a -> a) with sorted(): " + output4);

        System.out.println("-----------------------------");
        List<Integer> output5 = numbers.stream()
                .sorted(Comparator.comparingInt(a -> Integer.valueOf(a)))
                .toList();
        System.out.println("Using Comparator with Comparator.comparingInt(a -> Integer.valueOf(a)) with sorted(): " + output5);

        System.out.println("-----------------------------");
        List<Integer> output6 = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Using Comparator's Reverse Ordering with sorted(): " + output6);
    }
}
