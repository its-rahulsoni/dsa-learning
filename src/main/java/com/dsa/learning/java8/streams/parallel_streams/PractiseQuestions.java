package com.dsa.learning.java8.streams.parallel_streams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PractiseQuestions {

    public static void main(String[] args) {

        sumOfSquares();

        findMaximumNumber();

        wordFrequencyCount();

        parallelSorting();

        readDataFromFileParallely();

        printThreadNameInParallelExecution();
    }

    public static void sumOfSquares() {
        System.out.println("**** sumOfSquares() ****");

        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        int sum = list.parallelStream()
                        .mapToInt(n -> n * n)
                                .sum();

        System.out.println("Sum: " + sum);

        addDivider();
    }

    public static void findMaximumNumber() {
        System.out.println("**** findMaximumNumber() ****");

        List<Integer> list = new ArrayList<>(Arrays.asList(1,42,3,4,5));

        Optional<Integer> maxNumber = list.parallelStream()
                        .max(Comparator.comparingInt(a -> a));

        System.out.println("maxNumber: " + maxNumber.get());

        addDivider();
    }


    public static void wordFrequencyCount() {
        System.out.println("**** wordFrequencyCount() ****");

        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "orange", "banana"));

        Map<String, Long> output = list.parallelStream()
                        .collect(Collectors.groupingByConcurrent( // groupingByConcurrent ensures thread-safe grouping ....
                           str -> str,
                           Collectors.counting()
                        ));

        System.out.println("output: " + output);

        addDivider();
    }


    public static void parallelSorting() {
        System.out.println("**** parallelSorting() ****");

        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "orange", "banana"));

        List<String> output = list.parallelStream()
                        .sorted(Comparator.comparing(str -> str))
                        .collect(Collectors.toUnmodifiableList());

        System.out.println("output: " + output);

        List<String> words = Arrays.asList("Java", "Python", "C", "JavaScript", "Go");
        List<String> output2 = words.parallelStream()
                        .sorted(Comparator.comparing(a -> a.length()))
                        .collect(Collectors.toUnmodifiableList());
        System.out.println("output2: " + output2);

        addDivider();
    }



    public static void readDataFromFileParallely() {
        System.out.println("**** readDataFromFileParallely() ****");

        try(Stream<String> lines = Files.lines(Paths.get("sample.txt"))){ // Files.lines() reads lines in parallel ....
            long output = lines.parallel()
                            .flatMap(str -> Arrays.asList(str.split(" ")).stream()) // flatMap() splits lines into words ....
                            .count(); // count() aggregates results ....

           System.out.println("output: " + output);
        }catch(Exception e){
            e.printStackTrace();
        }

        addDivider();
    }


    public static void printThreadNameInParallelExecution() {
        System.out.println("**** printThreadNameInParallelExecution() ****");

        List<String> names = Arrays.asList("Rahul", "Amit", "Neha", "Sonal", "Deepak", "Priya");

        List<String> output = names.parallelStream()
                        .map(str -> {
                            String threadName = Thread.currentThread().getName();
                            System.out.println("threadName: " + threadName);
                            return str.toUpperCase();
                        })
                        .collect(Collectors.toUnmodifiableList());

        System.out.println("output: " + output);
        System.out.println("-----------------------------");

        List<String> output2 = names.stream()
                .map(str -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("threadName: " + threadName);
                    return str.toUpperCase();
                })
                .collect(Collectors.toUnmodifiableList());

        System.out.println("output2: " + output2);

        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
