package com.dsa.learning.java8.streams.intermediate.limit;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class LimitUsages {

    public static void main(String[] args) {

        // 01. Find the First N Even Numbers in an Infinite Stream ....
        firstNEvenNumbersInsideInfiniteStream();

        // 02. Find Top N Longest Strings in a List ....
        findTopNLongestStringsInAList();

        // 03. Sum of Top N Numbers After Sorting ....
        sumOfTopNNumbersAfterSorting();

        // 04. Paginate through a data set.
        paginateThroughADataSet();

        // 05. Limit Elements from Infinite Random Numbers and Filter ....
        printNEvenNumbersFromInfiniteRandomIntStream();

        // 06. Pick the First N Unique Elements from a Random Stream ....
        printNUniqueNumbersFromInfiniteRandomIntStream();
    }

    /**
     * 01. Find the First N Even Numbers in an Infinite Stream.
     *
     * Finding elements in an infinite stream is a common use case for limit(). You can use it to truncate an otherwise unbounded stream.
     *
     * Explanation:
     *
     * Stream.iterate(2, n -> n + 2) generates an unbounded stream of even numbers.
     * limit(10) ensures we only take the first 10 even numbers.
     */
    public static void firstNEvenNumbersInsideInfiniteStream(){
        System.out.println("**** combineTwoStreams() ****");

        Stream.iterate(2, n -> n + 2) // Infinite stream of even numbers
                        .limit(4) // Limit to the first 10 numbers
                        .forEach(System.out::println);

        addDivider();
    }


    /**
     * 02. Find Top N Longest Strings in a List
     *
     *
     *
     * Explanation:
     *
     */
    public static void findTopNLongestStringsInAList(){
        System.out.println("**** findTopNLongestStringsInAList() ****");

        List<String> strings = List.of("banana", "apple", "cherry", "blueberry", "pineapple", "pear");

        List<String> output = strings.stream()
                        .sorted((a, b) -> Integer.compare(a.length(), b.length())) // Sorting: Small -> Large strings ....
                        .limit(3)
                        .toList();
        System.out.println("Top N Smallest Strings in a List: " + output);

        List<String> output2A = strings.stream()
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))// Sorting: Large -> Small strings ....
                .limit(3)
                .toList();

        List<String> output2B = strings.stream()
                .sorted(Comparator.comparing(a -> a.length()))// Sorting: Large -> Small strings ....
                .limit(3)
                .toList();

        List<String> output2C = strings.stream()
                .sorted(Comparator.comparingInt(a -> a.length()))// Sorting: Large -> Small strings ....
                .limit(3)
                .toList();

        System.out.println("Top N Longest Strings in a List: " + output2C);


        addDivider();
    }

    /**
     * 03. Sum of Top N Numbers After Sorting
     */
    public static void sumOfTopNNumbersAfterSorting() {
        System.out.println("**** sumOfTopNNumbersAfterSorting() ****");

        List<Integer> numbers = List.of(5, 1, 9, 4, 7, 10, 3);

        long sum = numbers.stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(3)
                        .mapToInt(n -> n)// This converts n(int) inside Integer Object into a primitive int n ....
                        .sum(); // Sum() works only on primitive int and not on Integer objects ....

        System.out.println("Output: " + sum);

        addDivider();
    }


    /**
     * 04. Paginate through a data set.
     * When working with large datasets (e.g., in a web app), use limit() to implement pagination.
     *
     * Explanation:
     * skip() skips the elements corresponding to previous pages.
     * limit() ensures only pageSize elements are shown.
     */
    public static void paginateThroughADataSet() {
        System.out.println("**** paginateThroughADataSet() ****");

        List<String> data = List.of(
                "Alice", "Bob", "Cathy", "Daniel", "Elena",
                "Frank", "George", "Helen", "Iris", "Jack");

        // Pagination parameters
        int pageSize = 3;
        int page = 2; // Get the second page (zero-based)

        data.stream()
            .skip(5)
            .limit(4)
            .forEach(System.out::println);
        
        addDivider();
    }

    /**
     * 05. Limit Elements from Infinite Random Numbers and Filter.
     * Generate an infinite stream of random numbers, filter them for even values, and take the first 5 even ones.
     *
     * Explanation:
     * Random numbers are generated infinitely, but we need only 5.
     * Using filter() ensures only even numbers are passed to limit().
     */
    public static void printNEvenNumbersFromInfiniteRandomIntStream() {
        System.out.println("**** printNEvenNumbersFromInfiniteRandomIntStream() ****");

        new Random().ints(0, 100)
                        .filter(n -> n % 2 == 0)
                                .limit(5)
                                        .forEach(System.out::println);


        addDivider();
    }


    /**
     * 06. Pick the First N Unique Elements from a Random Stream.
     * We need to extract the first n unique elements from an infinite random number stream.
     *
     * Explanation:
     * distinct() keeps only unique numbers.
     * limit(5) ensures uniqueness is respected while truncating the stream.
     */
    public static void printNUniqueNumbersFromInfiniteRandomIntStream() {
        System.out.println("**** printNUniqueNumbersFromInfiniteRandomIntStream() ****");

        Stream.generate(() -> new Random().nextInt(100))
                        .distinct()
                                .limit(5)
                                        .forEach(System.out::println);

        addDivider();
    }



    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
