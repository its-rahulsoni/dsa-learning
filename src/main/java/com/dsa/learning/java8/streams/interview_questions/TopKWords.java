package com.dsa.learning.java8.streams.interview_questions;

import java.util.*;
import java.util.stream.Collectors;

public class TopKWords {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("one", "two", "three", "two", "four", "one", "two", "three","two","two");

        int k = 2; // top 3 frequent words

        Map<String, Long> wordsFrequency = words.stream()
                .collect(Collectors.groupingBy(
                        a -> a,
                        Collectors.counting()
                ));

        System.out.println("Map: " + wordsFrequency);

        /**
         * Comparator.<Map.Entry<String, Long>, Long>
         * This is an explicit type hint to help the compiler resolve generic types in the lambda.
         *
         * Map.Entry<String, Long>: the type of elements being sorted.
         *
         * Long: the type of the value you're comparing (the result of e.getValue()).
         *
         * Without this type hint, the compiler might infer Object for e, which is why you saw the error.
         */
        List<String> output = wordsFrequency.entrySet().stream()
                .sorted(Comparator.comparingLong((Map.Entry<String, Long> e) -> e.getValue()).reversed())
                //.sorted(Comparator.<Map.Entry<String, Long>, Long>comparing(e -> e.getValue()).reversed())
                //.sorted(Comparator.comparing(Map.Entry::getValue).reversed())
                .limit(k)
                .map(e -> e.getKey())
                .toList();
        System.out.println("output: " + output);

        List<Long> outputCount = wordsFrequency.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<String,Long> e) -> e.getValue()).reversed()) // Remember the () to wrap the e .....
                .map(Map.Entry::getValue)
                .toList();
        System.out.println("outputCount: " + outputCount);


        Map<String, Long> topKWordMap = wordsFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .collect(
                   Collectors.toMap(
                           e -> e.getKey(),
                           e -> e.getValue(),
                           (e1, e2) -> e1,
                           LinkedHashMap::new
                   )
                );

        System.out.println("Top K word-frequency map: " + topKWordMap);



    }

}
