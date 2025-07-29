package com.dsa.learning.java8.streams.terminal.max;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxPractise {

    public static void main(String[] args) {

        maxAgeOfEmployee();
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

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    static class Employee {
        String name;
        int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
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
