package com.dsa.learning.java8.streams.terminal.reduce;

import com.dsa.learning.models.EmployeeDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 🧠 What is reduce() in Layman Terms?
 * Imagine you have a bag of items (like coins or cards), and you want to combine them into one final result — like summing coins, finding the max, or merging strings.
 * That’s what .reduce() does: it reduces a stream of elements into a single result.
 *
 * ✨ Think of it like folding a stream into one value.
 * ✅ Basic Syntax:
 * Optional<T> result = stream.reduce((a, b) -> combine a and b);
 * Or:
 * T result = stream.reduce(identity, (a, b) -> combine a and b);
 * identity: a default value to start with (like 0 for sum, or "" for strings)
 *
 * (a, b) -> ...: how to combine two elements at a time
 *
 * Optional<T>: because result might be empty if the stream is empty
 */
public class ReducePractise {

    public static void main(String[] args) {

        concatenateWords();

        maxAgeOfEmployee();
    }

    public static void concatenateWords(){
        System.out.println("**** concatenateWords() ****");

        List<String> words = List.of("Java", "is", "awesome");

        String output = words.stream()
                        .collect(Collectors.joining(" "));
        System.out.println("output: " + output);

        String output2 = words.stream()
                        .reduce("", (a, b) -> a + " " + b);
        System.out.println("output2: " + output2);

        Optional<String> output3 = words.stream()
                .collect(Collectors.reducing((a, b) -> a + b));
        System.out.println("output3: " + output3.get());

        addDivider();
    }


    public static void maxAgeOfEmployee(){
        System.out.println("**** maxAgeOfEmployee() ****");

        List<Employee> employees = List.of(
                new Employee("Rahul", 30),
                new Employee("Amit", 45),
                new Employee("Sneha", 28)
        );

        Optional<Employee> output = employees.stream()
                        .max(Comparator.comparingInt(Employee::getAge));
        System.out.println("output: " + output.get());

        Optional<Employee> output2 = employees.stream()
                        .max(Comparator.comparing(e -> e.getAge())); //  .max(Comparator.comparingInt(e -> e.getAge()));
        System.out.println("output2: " + output2.get());

        Optional<Employee> output3 = employees.stream()
                .max((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        System.out.println("output3: " + output3.get());

        Integer output4 = employees.stream()
                        .map(e -> e.getAge())
                                .reduce(Integer.MIN_VALUE, (a, b) -> Math.max(a, b));
        System.out.println("Max age: " + output4);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    static class Employee {
        String name;
        int age;
        Employee(String name, int age) {
            this.name = name; this.age = age;
        }

        @Override
        public String toString(){
            return "name: " + name + ", age: " + age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
