package com.dsa.learning.java8.streams.terminal.any_all_none_Match;

import java.util.List;

public class MatchApisUsages {

    public static void main(String[] args) {

        // 01. Check if Any Number is Prime ....
        checkIfAnyNumberIsPrime();

        // 02. Tasks for Different Teams ....
        tasksForDifferentTeam();

        // 03. Nested Numbers Filtering ....
        nestedNumberFiltering();
    }

    /**
     * 01. Check if Any Number is Prime
     *
     */
    public static void checkIfAnyNumberIsPrime(){
        System.out.println("**** checkIfAnyNumberIsPrime() ****");

        List<Integer> numbers = List.of(10, 15, 18, 23, 27);

        boolean isPrime = numbers.stream()
                        .anyMatch(n -> MatchApisUsages.isPrime(n));
        System.out.println("isPrime: " + isPrime);

        addDivider();
    }


    /**
     * 02. Tasks for Different Teams
     *
     * Problem:
     * You have a List<List<Task>>, where each team has a list of tasks. A Task has a priority level and a status.
     *
     * Retain only the teams where all tasks are completed and at least one task is high priority.
     * Output a flat list with the names of all high-priority tasks for these teams.
     */
    public static void tasksForDifferentTeam(){
        System.out.println("**** tasksForDifferentTeam() ****");

        List<List<Task>> tasksByTeam = List.of(
                List.of(new Task("Task1", 5, false), new Task("Task2", 3, true)),
                List.of(new Task("Task3", 4, false), new Task("Task4", 5, true)), // Not all completed
                List.of(new Task("Task5", 1, true), new Task("Task6", 5, false)) // All completed
        );

        List<Task> output = tasksByTeam.stream()
                .flatMap(subList -> subList.stream())
                .filter(task -> {
                    if(task.isCompleted() && task.priority == 5)
                        return true;
                    else
                        return false;
                })
                .toList();


        /**
         * Here, allMatch condition matches all tasks in the sublist #03 that are completed and have a priority of 5.
         * The other sublists are filtered out entirely from further processing.
         *
         * That's why only the last sub-list is returned.
         */
        List<Task> output2 = tasksByTeam.stream()
                        .filter(subList -> subList.stream().allMatch(task -> task.isCompleted() && task.getPriority() == 5)) // Matches only those sub-lists whose all tasks are completed and has high priority ....
                        .flatMap(sublist -> sublist.stream())
                        .toList();

        /**
         * Here nothing will be returned.
         * Bcoz: the first aub-stream in the filter, only sends that stream further that has all its tasks with status = Completed.
         */
        List<Task> output3 = tasksByTeam.stream()
                .filter(subList -> subList.stream().allMatch(task -> task.isCompleted())  &&
                                    subList.stream().anyMatch(task -> task.getPriority() == 5))
                .flatMap(sublist -> sublist.stream())
                .toList();

        System.out.println("Names of all high-priority tasks with status as completed: " + output);
        System.out.println("Names of all high-priority tasks with status as completed2: " + output3);

        addDivider();
    }

    /**
     * 03. Nested Numbers Filtering
     *
     * Problem:
     * You have a List<List<Integer>> where each sublist contains a group of numbers. You need to:
     *
     * Retain only the sublists where at least one number is divisible by 3 and no number is negative.
     * Flatten the sublists and output a list of the squared values of the retained numbers.
     *
     * Explanation:
     *
     * Sublists Filter:
     * anyMatch(num -> num % 3 == 0) ensures at least one number in a sublist is divisible by 3.
     * noneMatch(num -> num < 0) removes sublists containing negative numbers.
     *
     * Flatten and Transform:
     * flatMap(List::stream) flattens valid sublists.
     * map(num -> num * num) calculates squared values.
     */
    public static void nestedNumberFiltering() {
        System.out.println("**** tasksForDifferentTeam() ****");

        List<List<Integer>> nestedLists = List.of(
                List.of(3, 6, 9),   // Keep (all non-negative, at least one divisible by 3)
                List.of(-4, 10, 15), // Remove (contains negative -4)
                List.of(8, 12, 20),  // Remove (none divisible by 3)
                List.of(21, 18, 6)   // Keep (all non-negative, at least one divisible by 3)
        );

        List<Integer> output = nestedLists.stream()
                        .filter(subList -> subList.stream().anyMatch(n -> n % 3 == 0) && // 1st we check for divisible by 3 condition ....
                                subList.stream().allMatch(n -> n > 0)) // 2nd we check for positive number ....
                                .flatMap(str -> str.stream())
                                        .toList();

        System.out.println("Sublisist with atleast 1 no divisible by 3 and no number is negative: " + output);

        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static class Task {
        String name;
        int priority; // 1 (low) to 5 (high)
        boolean isCompleted;

        Task(String name, int priority, boolean isCompleted) {
            this.name = name;
            this.priority = priority;
            this.isCompleted = isCompleted;
        }

        @Override
        public String toString(){
            return "name: " + name + ", priority: " + priority + ", isCompleted: " + isCompleted + ")";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }
    }
}
