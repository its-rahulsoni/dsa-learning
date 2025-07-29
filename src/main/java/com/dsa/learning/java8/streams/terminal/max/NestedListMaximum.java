package com.dsa.learning.java8.streams.terminal.max;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

        usingMaxOperation(nestedLists);
        usingReduceOperation(nestedLists);
    }


    public static void usingMaxOperation(List<List<Integer>> nestedLists){
        Optional<List<Integer>> output = nestedLists.stream()
                .max(Comparator.comparingInt(list -> list.stream().mapToInt(Integer::intValue).sum())); // Method Reference ....

        Optional<List<Integer>> output2 = nestedLists.stream()
                .max(Comparator.comparingInt(list -> list.stream().mapToInt(a -> Integer.valueOf(a)).sum())); // Lambda Expression ....

        System.out.println("UsingMaxOperation Largest List: " + output.get() + " **** " + output2.get());

        // Handle optional result
        // output.ifPresent(max -> System.out.println("Max Sublist: " + max));
        // Output: Max Sublist: [6, 7, 8]

        OptionalInt max = nestedLists.stream()
                .mapToInt(subList -> subList.stream().mapToInt(a -> a).sum())
                .max();
        System.out.println("max: " + max.getAsInt());
    }

    /**
     * How This Works:
     *
     * reduce() Combines Elements:
     *
     * reduce() iteratively takes two elements of the stream (l1 and l2) and uses your logic to return one of them based on a condition.
     * Your Binary Operator:
     *
     * l1 (first stream element) and l2 (second stream element) represent the two lists being compared.
     * Computes a and b, the sums of the integers in l1 and l2.
     * Logic: If a > b, you return l1 (bigger sum); otherwise, you return l2.
     * Final Result:
     *
     * reduce() iterates through the stream, comparing each pair of sublists, and keeps the one with the greatest sum.
     * After processing all elements, the "largest" (based on your logic) sublist is returned wrapped in an Optional.
     *
     * ------------------------------------------------------
     * How max() Differs
     * max() works differently than reduce() because it does not reduce the stream into a single result through custom aggregation logic but rather
     * selects the maximum element based on the comparison logic of a Comparator.
     *
     * When you write:
     * Optional<List<Integer>> output3 = nestedLists.stream()
     *                 .max((l1, l2) -> {
     *                     int a = l1.stream().mapToInt(Integer::intValue).sum();
     *                     int b = l2.stream().mapToInt(Integer::intValue).sum();
     *                     return a > b ? l1 : l2;
     *                 });
     * Problem:
     *  max() expects a Comparator that returns:
     *      0 if elements are equal.
     *      A negative value if the first element should come before the second.
     *      A positive value if the first element should come after the second.
     *  You returned l1 or l2 (i.e., a List<Integer>), which violates the Comparator contract (int compare(T o1, T o2)).
     * This is why your max() code failed.
     *
     * ------------------------------------------------------
     * Why reduce() Works
     * reduce() does not require you to return an int like max() does. Instead:
     *
     * reduce() expects you to return one of the two elements (l1 or l2).
     * This matches your logic:
     *      return a > b ? l1 : l2;
     * Here, you're correctly returning a List<Integer> (either l1 or l2), which satisfies the contract for reduce().
     *
     * ------------------------------------------------------
     * When to Use reduce() or max()?
     *
     * Use reduce():
     * When you need custom aggregation logic, where the resulting element might depend on a custom operation beyond mere comparison.
     * Example: Finding an element with a customized tie-breaking rule or combining elements (not just selecting the largest one).
     *
     * Use max():
     * When you strictly need the element with the maximum value (e.g., by sum or any comparable property).
     * Example: Finding the sublist with the largest sum, where a Comparator can directly express the logic.
     */
    public static void usingReduceOperation(List<List<Integer>> nestedLists){
        Optional<List<Integer>> output = nestedLists.stream()
                .reduce((l1, l2) -> {
                    int a = l1.stream().mapToInt(Integer::intValue).sum();
                    int b = l2.stream().mapToInt(Integer::intValue).sum();
                    return a > b ? l1 : l2;
                });
        System.out.println("UsingReduceOperation Largest List: " + output.get());
    }

}
