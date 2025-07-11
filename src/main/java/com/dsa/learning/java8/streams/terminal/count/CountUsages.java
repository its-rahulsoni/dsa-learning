package com.dsa.learning.java8.streams.terminal.count;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountUsages {
    public static void main(String[] args) {

        // 01. Count Total Elements in a Stream ....
        countElementsInAStream();

        // 02. Count Elements That Match a Condition ....
        countElementsThatMatchACondition();

        // 03. Count Unique Elements Using distinct() ....
        countUniqueElements();

        // 04. Count After Mapping ....
        countAfterMapping();

        // 05. Count Even Numbers ....
        countEvenNumbers();

        // 06. Count Words by Group ....
        countNumberOfWordsByStartingLetter();
    }

    /**
     * 01. Count Total Elements in a Stream:
     *
     * Explanation:
     * names.stream(): Converts the list into a stream.
     *
     * count(): Directly counts the total number of elements in the stream (4 in this case).
     */
    public static void countElementsInAStream(){
        System.out.println("***** countElementsInAStream *****");
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        long count = names.stream() // Converts the list into a stream.
                .count(); // Directly counts the total number of elements in the stream (4 in this case).

        System.out.println("Elements in the list: " + count);

        addDivider();
    }

    /**
     * 02. Count Elements That Match a Condition:
     *
     * Explanation:
     * filter(name -> name.startsWith("A")):
     * Filters the stream to include only names starting with 'A'.
     *
     * count():
     * Counts the elements after filtering (in this case, "Alice" and "Alex").
     */
    public static void countElementsThatMatchACondition(){
        System.out.println("***** countElementsThatMatchACondition *****");
        List<String> names = List.of("Alice", "Bob", "Charlie", "Alex", "David");

        long count = names.stream() // Converts the list into a stream.
                .filter(str -> str.startsWith("A")) // Filter names starting with 'A' ....
                .count(); // Directly counts the total number of elements starting from letter 'A' in the stream (2 in this case).

        System.out.println("Elements starting from letter 'A' in the list: " + count);

        addDivider();
    }

    /**
     * 03. Count Unique Elements Using distinct():
     *
     * Explanation:
     * distinct():
     * Removes duplicate elements from the stream (e.g., [2, 2] becomes [2]).
     *
     * count():
     * Counts the unique elements in the stream.
     */
    public static void countUniqueElements(){
        System.out.println("***** countUniqueElements *****");
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 5);

        long uniqueElement = numbers.stream()
                .distinct() // Eliminate duplicates ....
                .count();
        System.out.println("Unique Elements count: " + uniqueElement);

        addDivider();
    }

    /**
     * 04. Count After Mapping:
     * Count the number of distinct lengths of strings in a list.
     *
     * Explanation:
     * map(String::length):
     * Transforms each word into its length (e.g., "cat" -> 3).
     *
     * distinct():
     * Keeps only unique lengths.
     *
     * count():
     * Counts the number of unique lengths.
     */
    public static void countAfterMapping(){
        System.out.println("***** countAfterMapping *****");
        List<String> words = List.of("cat", "dog", "elephant", "ant", "apple");

        long count = words.stream()
                .map(str -> str.length())
                .distinct()
                .count();
        System.out.println("Distinct length of string: " + count);

        addDivider();
    }

    /**
     * 05. Count Even Numbers
     * Count how many even numbers are present in a list.
     *
     * Explanation:
     * filter(num -> num % 2 == 0):
     * Filters the stream to include only even numbers.
     *
     * count():
     * Counts the filtered elements (4 even numbers in this case: 2, 4, 6, 8).
     */
    public static void countEvenNumbers(){
        System.out.println("***** countEvenNumbers *****");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        long count = numbers.stream()
                .filter(a -> a % 2 == 0)
                .count();
        System.out.println("Even numbers count: " + count);

        addDivider();
    }

    /**
     * 06. Count Words by Group
     * Count the number of words by their starting letter.
     *
     * Explanation:
     * Grouping:
     * groupingBy(word -> word.charAt(0)) groups words by their starting letter.
     *
     * Counting:
     * Collectors.counting() counts the number of words in each group.
     */
    public static void countNumberOfWordsByStartingLetter(){
        System.out.println("***** countNumberOfWordsByStartingLetter *****");
        List<String> words = List.of("apple", "apricot", "banana", "blueberry", "cherry");

        Map<Character, Long> output = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0),
                        Collectors.counting()));
        System.out.println("Number Of Words By Starting Letter: " + output);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }
}
