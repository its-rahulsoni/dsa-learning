package com.dsa.learning.preparation_2023.interviews.streams;

import com.coding.interviews.streams.pojo.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctMethodOfStreamClass {

    public static void main(String[] args) {

        System.out.println("Example 1 - Finding all distinct salaries among all employees.");

        /**
         * distinct()
         *
         * It returns a stream consisting of distinct elements in a stream.
         * distinct() is the method of Stream interface. This method uses hashCode() and equals() methods to get distinct elements.
         * In case of ordered streams, the selection of distinct elements is stable. But, in case of unordered streams,
         * the selection of distinct elements is not necessarily stable and can change. distinct() performs stateful
         * intermediate operation i.e, it maintains some state internally to accomplish the operation.
         */
        List<Employee> employeesList = Arrays.asList(
                new Employee(1, "Alex", 100),
                new Employee(2, "Brian", 100),
                new Employee(3, "Charles", 200),
                new Employee(4, "David", 200),
                new Employee(5, "Edward", 300),
                new Employee(6, "Frank", 300)
        );

        List<Double> listOfDistinctEmployeeSalaries =
                employeesList.stream()
                        .map(e -> e.getSalary())
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("listOfDistinctEmployeeSalaries: " + listOfDistinctEmployeeSalaries);

        System.out.println("--------------------------------------------------------");

        System.out.println("Example 2 - Implementation of Stream.distinct() to get the distinct elements in the List.");

        // Creating a list of strings
        List<String> listOfStrings = Arrays.asList("Geeks", "for", "for", "Geeks",
                "GeeksQuiz", "for", "GeeksforGeeks");

        listOfStrings.stream()
                .distinct()
                .forEach(System.out::println);

        // Storing the count of distinct elements
        // in the list using Stream.distinct() method
        long Count = listOfStrings.stream()
                .distinct()
                .count();

        // Displaying the count of distinct elements
        System.out.println("The count of distinct elements is : " + Count);

    }
}
