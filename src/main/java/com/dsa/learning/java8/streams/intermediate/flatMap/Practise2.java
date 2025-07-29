package com.dsa.learning.java8.streams.intermediate.flatMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practise2 {

    public static void main(String[] args) {

        flattenList();

        mergeWordsFromMultipleSentences();

        unwrapNestedOptionals();

        convertMapToList();
    }


    public static void flattenList() {
        System.out.println("**** flattenList() ****");

        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2),
                List.of(3, 4, 5),
                List.of(6)
        );

        List<Integer> output = nestedNumbers.stream()
                        .flatMap(subStream -> subStream.stream())
                        .toList();

        System.out.println("output: " + output);

        addDivider();
    }

    public static void mergeWordsFromMultipleSentences() {
        System.out.println("**** mergeWordsFromMultipleSentences() ****");

        List<String> sentences = List.of(
                "Hello world",
                "Java streams",
                "flatMap example"
        );

        String output = sentences.stream()
                        .flatMap(str -> List.of(str.split(" ")).stream())
                        .collect(Collectors.joining(" "));

        System.out.println("output: " + output);

        addDivider();
    }


    public static void unwrapNestedOptionals() {
        System.out.println("**** unwrapNestedOptionals() ****");

        Optional<Optional<String>> nestedOptional = Optional.of(Optional.of("value"));

        /**
         * What does the lambda inner -> inner mean?
         * inner here is the inner Optional<String>, because nestedOptional contains an Optional<String>.
         *
         * The lambda just returns that inner Optional as is.
         */
        Optional<String> output = nestedOptional.flatMap(inner -> inner);

        System.out.println("output: " + output.get());

        addDivider();
    }

    /**
     * To convert a Map's Value to stream, we apply a stream on its values() only - map.values().stream(). ....
     *
     * Also, we can apply stream on Map's entrySet(). In this approach inside flatMap, we will have object of entrySet type.
     * We can use this entrySet object to fetch keys and values.
     */
    public static void convertMapToList() {
        System.out.println("**** convertMapToList() ****");

        Map<String, List<Integer>> map = Map.of(
                "A", List.of(1, 2),
                "B", List.of(3, 4)
        );

        List<Integer> output = map.values().stream()
                        .flatMap(subList -> subList.stream())
                                .toList();

        List<Integer> output2 = map.entrySet().stream()
                        .flatMap(entry -> entry.getValue().stream())
                                .toList();

        System.out.println("output: " + output);
        System.out.println("output2: " + output2);

        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
