package com.dsa.learning.java8.streams.terminal.collect.Collectors;

import com.dsa.learning.models.EmployeeDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectingAndThenUsage {
    public static void main(String[] args) {

        // 01. Convert a List into an Immutable List ....
        convertAListIntoAnImmutableList();

        // 02. Count Words After Post-Processing ....
        countWordsAfterPostProcessing();

        // 03. Grouping and Post-Processing the Group ....
        groupingAndPostProcessingGroup();

        // 04. Aggregating Salaries to Custom Type ....
        aggregatingSalariesToCustomType();
    }

    /**
     * 01. Convert a List into an Immutable List.
     *
     * Problem:
     * You want to collect elements from a stream into a list, but the final result must be an immutable list.
     */
    public static void convertAListIntoAnImmutableList(){
        System.out.println("**** convertAListIntoAnImmutableList() ****");

        List<String> names = List.of("Akash", "Sagar", "Mubarik", "Rahul", "Manoj");

        List<String> immutableListWithUpperCase = names.stream()
                        .map(a -> a.toUpperCase())
                                .collect(Collectors.collectingAndThen(
                                        Collectors.toList(), // Base collector ....
                                        List::copyOf // Transformation to immutable list using Method Reference ....
                                ));
        System.out.println("Immutable List With Upper Case: " + immutableListWithUpperCase);

        List<String> immutableListWithUpperCase2 = names.stream()
                .map(a -> a.toUpperCase())
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(), // Base collector ....
                        list -> List.copyOf(list) // Transformation to immutable list using Lambda Expression ....
                ));

        addDivider();
    }

    /**
     * 02. Count Words After Post-Processing.
     * Problem:
     * You want to collect words into a single String, but you also want to compute the length of the resulting concatenated string.
     *
     * Explanation:
     * Collectors.joining(" ") combines words into a single String with spaces ("Java Streams Are Cool").
     * String::length computes the length of that string, which is 21.
     */
    public static void countWordsAfterPostProcessing(){
        System.out.println("**** countWordsAfterPostProcessing() ****");

        List<String> words = List.of("Java", "Streams", "Are", "Cool");

        int countOfWords = words.stream()
                .collect(Collectors.collectingAndThen(
                     Collectors.joining(" "), // Combine the words into a single string (with space delimiter) ....
                     str -> str.length() // Compute the length of this concatenated string ....
                ));
        System.out.println("Count Of Words in resulting string (including white-spaces): " + countOfWords);

        addDivider();
    }

    /**
     * 03. Grouping and Post-Processing the Group.
     *
     * Problem:
     * Group items but collect the result into an immutable map.
     *
     * Thought Process:
     * Combine grouping logic (groupingBy(String::length)) and post-processing to make the map immutable.
     * This ensures no accidental modifications are allowed after grouping.
     */
    public static void groupingAndPostProcessingGroup(){
        System.out.println("**** GroupingAndPostProcessingGroup() ****");

        List<String> words = List.of("apple", "banana", "cherry", "dog", "cat");

        Map<Integer, List<String>> output = words.stream()
                        .collect(Collectors.collectingAndThen(
                                Collectors.groupingBy(str -> str.length()), // Base grouping logic ....
                                Map::copyOf // Make the result map immutable using Method Reference ....
                        ));
        System.out.println("Map: " + output);

        Map<Integer, List<String>> output2 = words.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(str -> str.length()), // Base grouping logic ....
                        map -> Map.copyOf(map) // Make the result map immutable using Lambda Expression ....
                ));

        addDivider();
    }

    /**
     * 04. Aggregating Salaries to Custom Type.
     *
     * Problem:
     * Compute the total salary of employees, but return it as a custom result type (String).
     */
    public static void aggregatingSalariesToCustomType(){
        System.out.println("**** AggregatingSalariesToCustomType() ****");

        List<EmployeeDTO> employees = createNewEmployeesList();

        String salaryInString = employees.stream()
                        .collect(Collectors.collectingAndThen(
                           Collectors.summingDouble(e -> e.getSalary()), // Aggregate salaries ....
                           salary -> String.valueOf(salary) // Converts the aggregated result into a formatted string ....
                        ));
        System.out.println("Salary in String: " + salaryInString);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    public static List<EmployeeDTO> createNewEmployeesList(){
        List<EmployeeDTO> employees = List.of(
                new EmployeeDTO(1, 25, "Alice", 50000),
                new EmployeeDTO(2, 48, "Bob", 60000),
                new EmployeeDTO(3, 35, "Charlie", 45000),
                new EmployeeDTO(4, 20, "Diana", 75000)
        );
        return employees;
    }

}
