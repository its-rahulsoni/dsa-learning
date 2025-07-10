package com.dsa.learning.java8.streams.max;

import java.util.*;

public class FindMaximumCustomObject {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", 50000),
                new Employee("Bob", 60000),
                new Employee("Charlie", 55000)
        );

        /*List<Employee> employees2 = new ArrayList<>(Arrays.asList(new Employee("Alice", 50000),
                new Employee("Bob", 60000),
                new Employee("Charlie", 55000)));*/

        /**
         * Thought Process:
         * Stream Elements: Objects (Employee).
         *
         * Comparison Logic: Compare Employee.salary using Comparator.comparingDouble().
         *
         * Empty Stream: Handle the absence of employees using Optional.ifPresent().
         */
        Optional<Employee> maxSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(e -> e.getSalary()));
        System.out.println("maxSalaryEmployee: " + maxSalaryEmployee.get());

    }

    static class Employee {
        String name;
        double salary;
        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        @Override
        public String toString() {
            return name + " ($" + salary + ")";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}
