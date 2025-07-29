package com.dsa.learning.java8.streams.terminal.collect.Collectors;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsPractising {

    public static void main(String[] args) {

        characterFrequency();
    }

    /**
     *
     */
    public static void characterFrequency(){
        System.out.println("**** characterFrequency() ****");

        String input = "banana";

        Map<Character, Long> output = input.chars()
                        .mapToObj(ch -> (char)ch)
                                .collect(Collectors.groupingBy(
                                   ch -> ch,
                                   Collectors.counting()
                                ));
        System.out.println("output: " + output);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }
}
