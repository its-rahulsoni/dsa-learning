package com.dsa.learning.java8.streams.intermediate.map;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUsages {

    public static void main(String[] args) {

        // 01. Convert Strings to Uppercase ....
        convertStringsToUppercase();

        // 02. Square Each Number ....
        squareOfEachNumber();

        // 03. Extract Property from Objects ....
        extractPropertyFromObjects();

        // 04. Convert List of Numbers to Strings ....
        convertListOfNumbersToStrings();

        // 05. Format Strings for Display ....
        formatStringsForDisplay();

        // 06. Map with Conditional Transformation .....
        mapWithConditionalTransformation();

        // 07. Combine Maps with Data
        combineMapsWithData();
    }

    /**
     * 01. Convert Strings to Uppercase
     * Problem: Convert a list of lowercase strings to uppercase.
     */
    public static void convertStringsToUppercase(){
        System.out.println("**** convertStringsToUppercase() ****");

        List<String> names = List.of("alice", "bob", "charlie");

        List<String> output = names.stream()
                        .map(s -> s.toUpperCase()) // Convert to uppercase ....
                        .toList();

        System.out.println("Upper case strings: " + output);

        addDivider();
    }


    /**
     * 02. Square Each Number
     * Problem: Calculate the square of each number in a list.
     */
    public static void squareOfEachNumber(){
        System.out.println("**** squareOfEachNumber() ****");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> output = numbers.stream()
                .map(n -> n * n)
                .toList();

        System.out.println("Square Of Each Number: " + output);

        addDivider();
    }


    /**
     * 03. Extract Property from Objects
     * Problem: Extract the names of employees from a list of Employee objects.
     *
     * What’s Happening Here?
     * Stream Creation:
     *
     * employees.stream() creates a Stream<Employee>.
     * Your stream initially contains elements of type Employee:
     *
     *
     * Stream<Employee> -> [Employee("Alice", 101), Employee("Bob", 102), Employee("Charlie", 103)]
     * Mapping Transformation:
     *
     * The lambda e -> e.getName() is the mapping function. This function:
     * Takes each Employee object (e) in the stream.
     * Calls e.getName() on it, extracting the employee's name (of type String).
     * Replaces the original Employee object with the result of e.getName() in the output stream.
     * After applying map(e -> e.getName()), the transformed stream becomes:
     *
     *
     *
     * Stream<String> -> ["Alice", "Bob", "Charlie"]
     * Terminal Operation (toList()):
     *
     * Finally, .toList() collects the Stream<String> into a List<String>.
     */
    public static void extractPropertyFromObjects(){
        System.out.println("**** extractPropertyFromObjects() ****");

        List<Employee> employees = List.of(
                new Employee("Alice", 101),
                new Employee("Bob", 102),
                new Employee("Charlie", 103)
        );

        List<String> output = employees.stream()
                        .map(e -> e.getName()) // Transformation of our Stream content - from an Employee object to a String object ....
                                .toList();

        System.out.println("Employee Names: " + output);

        addDivider();
    }


    /**
     * 04. Convert List of Numbers to Strings.
     *
     * Problem: Convert a list of integers to their string representation.
     *
     * Scenario:
     * Use when working on data formatting tasks such as converting primitive types to strings.
     */
    public static void convertListOfNumbersToStrings(){
        System.out.println("**** convertListOfNumbersToStrings() ****");

        List<Integer> numbers = List.of(1, 2, 3, 4);

        List<String> output = numbers.stream()
                        .map(num -> String.valueOf(num))
                                .collect(Collectors.toUnmodifiableList());
        System.out.println("String numbers: " + output);

        addDivider();
    }

    /**
     * 05. Format Strings for Display
     *
     * Problem: Add a prefix to each string in a list.
     *
     * Scenario:
     * Use when working on data formatting tasks such as converting primitive types to strings.
     */
    public static void formatStringsForDisplay(){
        System.out.println("**** formatStringsForDisplay() ****");

        List<String> items = List.of("apple", "banana", "cherry");

        List<String> output = items.stream()
                        .map(str -> "PRE-" + str)
                                .toList();

        System.out.println("String with prefix: " + output);

        addDivider();
    }


    /**
     * 06. Map with Conditional Transformation
     *
     * Problem: Convert negative numbers to positive, keep positive numbers unchanged.
     *
     * Scenario:
     * Use map() when you need to apply conditional logic to transform elements.
     */
    public static void mapWithConditionalTransformation(){
        System.out.println("**** mapWithConditionalTransformation() ****");

        List<Integer> numbers = List.of(-10, 15, -20, 25);

        List<Integer> output = numbers.stream()
                .map(n -> {
                    if (n < 0)
                        n = n * -1;
                    return n;
                })
                .toList();

        System.out.println("Positive Numbers: " + output);

        addDivider();
    }

    /**
     * 07. Combine Maps with Data
     *
     * Problem: Generate labels for a list of employees based on their IDs and a predefined mapping of roles.
     *
     * Scenario:
     * Works for combining data from multiple sources.
     *
     * Note: This is an example of combining 2 streams ....
     */
    public static void combineMapsWithData(){
        System.out.println("**** combineMapsWithData() - This is an example of combining 2 streams ****");


        List<Employee> employees = List.of(
                new Employee("Alice", 101),
                new Employee("Bob", 102),
                new Employee("Charlie", 103)
        );

        // If roleMap was in the form of - list, we could have converted it to a stream ....
        Map<Integer, String> roleMap = Map.of(
                101, "Manager",
                102, "Developer",
                103, "Analyst"
        );

        List<String> output = employees.stream()
                        .map(e -> e.getName() + roleMap.get(e.getId()))
                                .toList();

        System.out.println("Emp label: " + output);

        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    static class Employee {
        String name;
        int id;

        Employee(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
