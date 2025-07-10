package com.dsa.learning.java8.streams.terminal.reduce;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Given a list of sublists (List<List<Integer>>), find the sublist with the highest sum.
 */
public class NestedListMaximum {
    public static void main(String[] args) {
        List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8),
                List.of(2)
        );

        usingReduceOperation(nestedLists);
        usingMaxOperation(nestedLists);
    }

    public static void usingReduceOperation(List<List<Integer>> nestedLists){
        Optional<List<Integer>> output = nestedLists.stream()
                .reduce((l1, l2) -> {
                    int a = l1.stream().mapToInt(Integer::intValue).sum();
                    int b = l2.stream().mapToInt(Integer::intValue).sum();
                    return a > b ? l1 : l2;
                });
        System.out.println("UsingReduceOperation Largest List: " + output.get());
    }


    public static void usingMaxOperation(List<List<Integer>> nestedLists){
        Optional<List<Integer>> output = nestedLists.stream()
                .max(Comparator.comparingInt(list -> list.stream().mapToInt(Integer::intValue).sum()));
        System.out.println("UsingMaxOperation Largest List: " + output.get());

        // Handle optional result
        // output.ifPresent(max -> System.out.println("Max Sublist: " + max));
        // Output: Max Sublist: [6, 7, 8]
    }



}
