package com.dsa.learning.java8.streams.intermediate.peek;

import java.util.List;

public class PeekUsages {

    public static void main(String[] args) {

        // 01. Debugging a Filtered Stream ....
        debuggingAFilteredStream();

        // 02. Conditional Logging ....
        conditionalLogic();

        // 03. Debugging Nested Streams ....
        debuggingNestedStreams();
    }

    /**
     * 01. Debugging a Filtered Stream
     * Use peek() to observe intermediate steps in a stream that filters numbers.
     */
    public static void debuggingAFilteredStream(){
        System.out.println("**** debuggingAFilteredStream() ****");

        List<Integer> numbers = List.of(10, 15, 20, 25, 30);

        List<Integer> output = numbers.stream()
                        .peek(n -> System.out.println("Number before any transformation: " + n))
                        .map(n -> n * 2)
                        .peek(n -> System.out.println("Number after any transformation: " + n + "\n"))
                        .toList();

        System.out.println("Output: " + output);
        addDivider();
    }


    /**
     * 02. Conditional Logging
     * Log only specific elements during processing. Here, we print only Even numbers using peek().
     */
    public static void conditionalLogic(){
        System.out.println("**** conditionalLogic() ****");

        List<Integer> numbers = List.of(10, 15, 20, 25, 30);

        List<Integer> output = numbers.stream()
                .peek(n -> {
                    if(n % 2 == 0)
                        System.out.println("Number before any transformation: " + n);
                })
                .map(n -> n * 2)
                .toList();

        System.out.println("Output: " + output);
        addDivider();
    }


    /**
     * 03. Debugging Nested Streams
     * Use peek() to debug the intermediate stages of nested streams.
     */
    public static void debuggingNestedStreams(){
        System.out.println("**** debuggingNestedStreams() ****");

        List<List<Integer>> nestedList = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        List<Integer> output = nestedList.stream()
                        .peek(subList -> System.out.println("subList: " + subList)) // The stream here represents each of the sub-list ....
                        .flatMap(subList -> subList.stream().peek(sub -> System.out.println("sub: " + sub))) // This flatens the internal sub-list elements ....
                        .toList();

        System.out.println("Output: " + output);
        addDivider();
    }



    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
