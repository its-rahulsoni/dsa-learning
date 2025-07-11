package com.dsa.learning.java8.streams.intermediate.distinct;

import com.dsa.learning.java8.streams.terminal.max.FindMaximumCustomObject;
import com.dsa.learning.models.EmployeeDTO;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        List<Integer> output = numbers.stream()
                .distinct()
                .toList();
        System.out.println("output using distinct: " + output);
    }
}
