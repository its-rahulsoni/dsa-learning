package com.dsa.learning.java8.streams.terminal.collect.Collectors;

import com.dsa.learning.models.EmployeeDTO;
import com.dsa.learning.models.Transaction;
import com.dsa.learning.preparation_2023.interviews.streams.pojo.Employee;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorClassMethods {
    public static void main(String[] args) {

        // 1. Collectors.toList() ....
        toList();

        // 2. Collectors.toSet() ....
        toSet();

        // 3. Collectors.joining() ....
        joining();

        // 4. Collectors.summingInt() ....
        summingIntAndDouble();

        // 5. Collectors.averagingInt() ....
        averagingInt();

        // 6. Collectors.counting() ....
        counting();

        // 7. Collectors.summarizingInt() ....
        summarizingInt();

        // 8. Collectors.mapping() ....
        mapping();

        // 9. Collectors.groupingBy ....
        groupingElements();

        // 10. Partitioning Elements ....
        partitioningElements();

        // 11. Reducing Elements ....
        reducingElements();

        groupEmployeesByAge();

        groupTransactionsByCurrencyAndThenByTransactionType();
    }

    /**
     * 1. Collectors.toList()
     *
     * Purpose:
     * Collects elements into a List (e.g., ArrayList).
     *
     * Key Use Cases:
     * Dynamically create lists from filtered or transformed elements.
     * Use this when you need to retain all elements (duplicates included).
     *
     * Thought Process:
     * You want to preserve order and allow duplicates.
     * Use toList() as a simple way to gather results into an ordered, mutable list.
     */
    public static void toList(){
        System.out.println("**** toList() ****");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> output = numbers.stream()
                .map(a -> a * 5) // Multiply each item of the array by 5 ....
                .collect(Collectors.toList());

        System.out.println("toList Output: " + output);

        addDivider();
    }

    /**
     * 2. Collectors.toSet()
     *
     * Purpose:
     * Removes duplicates and collects elements into a Set.
     *
     * Key Use Cases:
     * Use when uniqueness among elements is required.
     * Suitable for filtering duplicate data in streams.
     */
    public static void toSet(){
        System.out.println("**** toSet() ****");
        List<Integer> numbers = List.of(1, 2, 1, 4, 4, 2);

        Set<Integer> output = numbers.stream()
                .map(a -> a * 5) // Multiply each item of the array by 5 ....
                .collect(Collectors.toSet());

        System.out.println("toSet Output: " + output);

        addDivider();
    }

    /**
     * 3. Collectors.joining()
     *
     * Purpose:
     * Concatenates stream elements into a String, with optional delimiter, prefix, and suffix.
     *
     * Key Use Cases:
     * Create readable strings from collections (e.g., joining names, CSV format).
     * Combine small strings into a single result.
     *
     * Thought Process:
     * You need a well-formatted output like CSV.
     * Simple strings need to be concatenated; this method avoids manual looping.
     */
    public static void joining(){
        System.out.println("**** joining() ****");
        List<String> words = List.of("apple", "banana", "cherry");

        String output = words.stream()
                .collect(Collectors.joining());
        System.out.println("Plain joining(): " + output);

        String output2 = words.stream()
                .collect(Collectors.joining(", ")); // Join words with a comma delimiter ....
        System.out.println("Comma Delimiter joining(): " + output2);

        addDivider();
    }

    /**
     * 4. Collectors.summingInt()
     *
     * Purpose:
     * Computes the sum of an int property extracted from the stream.
     *
     * Key Use Cases:
     * Aggregate numerical data like salaries, grades, or expenses.
     *
     * Thought Process:
     * You need a sum of a specific property (e.g., salary).
     * Use summingInt() when calculating running totals.
     */
    public static void summingIntAndDouble(){
        System.out.println("**** summingIntAndDouble() ****");
        List<EmployeeDTO> employees = createNewEmployeesList();

        double totalSalary = employees.stream()
                .collect(Collectors.summingDouble(e -> e.getSalary()));
        System.out.println("Total Salary: " + totalSalary);

        int totalAge = employees.stream()
                        .collect(Collectors.summingInt(e -> e.getAge()));
        System.out.println("Total Age: " + totalAge);

        addDivider();
    }

    /**
     * 5. Collectors.averagingInt()
     *
     * Purpose:
     * Computes the average for an int property.
     *
     * Key Use Cases:
     * Aggregate numerical data when averages are needed (e.g., average grade or salary).
     *
     * Thought Process:
     * You need an average value of a property (e.g., salary).
     * Use averagingInt() for a straightforward aggregation.
     */
    public static void averagingInt(){
        System.out.println("**** averagingInt() ****");
        List<EmployeeDTO> employees = createNewEmployeesList();

        double averageAge = employees.stream()
                .collect(Collectors.averagingInt(e -> e.getAge()));
        System.out.println("Average Age: " + averageAge);

        addDivider();

    }

    /**
     * 6. Collectors.counting()
     *
     * Purpose:
     * Counts the total number of elements in the stream.
     *
     * Key Use Cases:
     * Use it to count elements quickly, especially after filtering or transformations.
     *
     * Thought Process:
     * You want to know how many elements satisfy a condition.
     * Use this for quick cardinality calculations.
     */
    public static void counting(){
        System.out.println("**** counting() ****");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        long countOfEvenNumbers = numbers.stream()
                .filter(a -> a % 2 == 0)
                .collect(Collectors.counting());
        System.out.println("Count Of Even Numbers: " + countOfEvenNumbers);

        /**
         * Map<String, Long> wordCountMap = words.stream()
         *                 .collect(Collectors.groupingBy(
         *                         w -> w, // Key: the word itself ....
         *                         Collectors.counting() // Value: count occurrences ....
         *                 ));
         * How This Works:
         * groupingBy(w -> w)
         * Groups the stream elements (words) by their identity (the word itself).
         *
         * Creates a Map<String, List<String>> temporarily:
         * {
         *   "apple"     : ["apple"],
         *   "blueberry" : ["blueberry", "blueberry"],
         *   "apricot"   : ["apricot", "apricot", "apricot"],
         *   ...
         * }
         * Collectors.counting()
         *
         * Replaces each List<String> with its size (count of elements).
         * Internally, it uses:
         * Collectors.reducing(0L, e -> 1L, Long::sum)
         * Maps each element to 1L, then sums them up.
         *
         * Final Result
         * Converts the intermediate Map<String, List<String>> to Map<String, Long>
         */
        List<String> words = List.of("apple", "blueberry", "apricot", "banana", "apricot", "blueberry", "cherry", "apricot");

        Map<String, Long> wordCountMap = words.stream()
                .collect(Collectors.groupingBy(
                        w -> w, // Key: the word itself ....
                        Collectors.counting() // Value: count occurrences in the list created by above line ....
                ));
        System.out.println("Count of words in the given list: " + wordCountMap);

        Map<Character, Long> output = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0),
                        Collectors.counting()));
        System.out.println("Number Of Words By Starting Letter: " + output);

        addDivider();
    }


    /**
     * 7. Collectors.summarizingInt()
     *
     * Purpose:
     * Provides a statistical summary (total, count, min, max, average) of numerical properties.
     *
     * Key Use Cases:
     * Generate a full statistical summary of a collection.
     */
    public static void summarizingInt(){
        System.out.println("**** summarizingInt() ****");

        List<EmployeeDTO> employees = createNewEmployeesList();

        IntSummaryStatistics statistics = employees.stream()
                .collect(Collectors.summarizingInt(e -> e.getAge()));
        System.out.println("Statistics via summarizingInt for Employees Age: " + statistics);

        addDivider();
    }

    /**
     * 8. Collectors.mapping()
     * Purpose:
     * Applies a transformation (e.g., mapping) to elements before collecting them.
     *
     * Key Use Cases:
     * Transform elements during grouping or another collection process.
     */
    public static void mapping(){
        System.out.println("**** mapping() ****");

        List<String> names = List.of("Akash", "Sagar", "Mubarik", "Rahul", "Manoj");

        List<String> output = names.stream()
                        .collect(Collectors.mapping(String :: toUpperCase, Collectors.toList())); // Method Reference ....

        List<String> output2 = names.stream()
                .collect(Collectors.mapping(a -> a.toUpperCase(), Collectors.toList())); // Lambda Expression ....

        List<String> output3 = names.stream()
                        .map(a -> a.toUpperCase())
                                .collect(Collectors.toUnmodifiableList());

        System.out.println("Mapping function that transforms strings into Upper Case: " + output);

        addDivider();
    }

    /**
     * 9. Collectors.groupingBy
     *
     * Divide stream elements into groups based on a classifier function (e.g., by length, age group, etc.).
     *
     * Thought Process:
     * Use Collectors.groupingBy() to classify elements into buckets.
     * The classifier function determines the grouping criteria (here, word length).
     */
    public static void groupingElements(){
        System.out.println("**** groupingElements() ****");

        List<String> words = List.of("cat", "dog", "fish", "horse");

        Map<Integer, List<String>> output = words.stream()
                        .collect(Collectors.groupingBy(str -> str.length()));
        System.out.println("Grouped Elements in Map: " + output);

        addDivider();
    }

    /**
     * 10. Collectors.partitioningBy
     * Purpose:
     * Split elements into two groups (e.g., pass or fail) based on a predicate.
     *
     * Thought Process:
     * Use Collectors.partitioningBy() for a binary classification (e.g., true or false).
     * Useful for separating elements into two logical groups (e.g., even/odd, pass/fail).
     */
    public static void partitioningElements(){
        System.out.println("**** partitioningElements() ****");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        Map<Boolean,List<Integer>> output = numbers.stream()
                .collect(Collectors.partitioningBy(a -> a % 2 == 0));
        System.out.println("Partitioning Elements Output: " + output);

        List<String> fruits = List.of("apple", "banana", "cherry", "date");

        Map<Boolean,Map<String, Long>> output2 = fruits.stream()
                        .collect(Collectors.partitioningBy(
                                str -> str.length() % 2 == 0,
                                Collectors.groupingBy(
                                        str -> str,
                                        Collectors.counting()
                                )
                        ));
        System.out.println("output2: " + output2);

        addDivider();
    }

    /**
     * 11. Collectors.reducing
     *
     * What Does reducing Do?
     * reducing() aggregates stream elements into a single result using a reduction function (e.g., sum, concatenate, find maximum).
     * It's a more customizable form of reduce(), designed to work inside the collect() framework.
     */
    public static void reducingElements(){
        System.out.println("**** reducingElements() ****");

        // Sum of numbers ....
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .collect(Collectors.reducing(0, (a, b) -> a + b));

        int sum2 = numbers.stream()
                .collect(Collectors.reducing(0, (a, b) -> Integer.sum(a, b))); // Concatening the strings using Lambda Expression ....

        int sum3 = numbers.stream()
                .collect(Collectors.reducing(0, Integer::sum));
        System.out.println("Sum using reducing: " + sum + ", " + sum2 + ", " + sum3);

        // Concatenate Strings ....
        List<String> words = List.of("apple", "banana", "cherry");
        String output = words.stream()
                        .collect(Collectors.reducing("", (a, b) -> a + " " + b));
        String output2 = words.stream()
                .collect(Collectors.reducing("", String::concat)); // Concatening the strings using Method Reference....
        System.out.println("Concatenated String: " + output);

        addDivider();
    }


    public static void groupEmployeesByAge(){
        System.out.println("**** groupEmployeesByAge() ****");

        List<EmployeeDTO> employees = createNewEmployeesList();

        Map<Integer, Long> map = employees.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.getAge(),
                                Collectors.counting() // This counts the total no of elements present in the list created by above line ....
                        ));
        System.out.println("Age to Count mapping of Employees: " + map);

        // Since, we are required to take a total of a field value from a list of objects, it means that we have to COLLECT
        // the total.
        double totalSalary = employees.stream()
                        .collect(Collectors.summingDouble(e -> e.getSalary()));
        System.out.println("totalSalary: " + totalSalary);

        addDivider();
    }


    public static void groupTransactionsByCurrencyAndThenByTransactionType (){
        System.out.println("**** groupTransactionsByCurrencyAndThenByTransactionType() ****");

        List<Transaction> transactions = List.of(
                new Transaction("T1", "USD", "DEBIT", 100.0),
                new Transaction("T2", "USD", "CREDIT", 200.0),
                new Transaction("T3", "EUR", "DEBIT", 150.0)
        );

        /**
         * {
         *     "USD"={"DEBIT"=[T1], "CREDIT"=[T2]},
         *     "EUR"={"DEBIT"=[T3]}
         * }
         */

        Map<String, Map<String, List<String>>> output = transactions.stream()
                        .collect(Collectors.groupingBy(
                                t -> t.currency(),
                                Collectors.groupingBy(
                                        t -> t.type(),
                                        Collectors.mapping(t -> t.id(), Collectors.toList())
                                )
                        ));

        System.out.println("output:" + output);

        addDivider();
    }



    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    public static List<EmployeeDTO> createNewEmployeesList(){
        List<EmployeeDTO> employees = List.of(
                new EmployeeDTO(1, 25, "Alice", 50000),
                new EmployeeDTO(2, 48, "Bob", 60000),
                new EmployeeDTO(3, 25, "Charlie", 45000),
                new EmployeeDTO(4, 48, "Diana", 75000),
                new EmployeeDTO(4, 25, "Diana", 25000),
                new EmployeeDTO(4, 56, "Diana", 11000)
        );
        return employees;
    }
}
