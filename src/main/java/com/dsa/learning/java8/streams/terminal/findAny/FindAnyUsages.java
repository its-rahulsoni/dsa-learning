package com.dsa.learning.java8.streams.terminal.findAny;

import com.dsa.learning.models.EmployeeDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Its returns Optional().
 */
public class FindAnyUsages {

    public static void main(String[] args) {

        // 01. Find Any Even Number in a List ....
        findAnyEvenNumberInTheList();
    }

    /**
     * 01. Find Any Even Number in a List.
     *
     * Problem:
     * Retrieve any even number from a list using findAny().
     *
     * Explanation:
     * Filter: Retains only even numbers (10, 20).
     * findAny(): Retrieves any one of them, e.g., 10.
     */
    public static void findAnyEvenNumberInTheList(){
        System.out.println("**** findAnyEvenNumberInTheList() ****");

        List<Integer> numbers = List.of(3, 6, 7, 10, 15, 20);

        Optional<Integer> even = numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .findAny();

        System.out.println("Any even no: " + even.get());

        numbers.stream()
                .filter(n -> n % 2 == 0)
                .findAny()
                .ifPresentOrElse(no -> System.out.println("Even no: " + no),
                        () -> System.out.println("No even no found")); // Note: The () -> sout() pattern. It accepts param (1 or none)

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
