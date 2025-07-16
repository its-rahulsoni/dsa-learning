package com.dsa.learning.java8.streams.intermediate.filter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterUsages {

    public static void main(String[] args) {

        // 01. Filter Strings by Predicate (Starts With a Letter) ....
        filterStringsByPredicate();

        // 02. Filter and Count ....
        filterAndCountAllIntegersGreaterThan5();

        // 03. Filter Distinct Elements ....
        filterDistinctNumbersThatAreEven();

        // 04. Multiple Conditions in Filters ....
        filterNumbersThatAreGreaterThan5AndDivisibleBy3();

        // 05. Filter Objects Based on Field ....
        filterEmployeesWithSalaryLessThan50000();

        // 06. Filter Nested Lists.
        filterInnerListForNumbersLessThan5();

        // 07. Filter Employees Based on Multiple Conditions ....
        filterEmployeesBasedOnMultipleConditions();

        // 08. Filter Words with Nested Conditions ....
        filterWordsWithNestedConditions();

        // 09. Filter and Sort Large Numbers ....
        filterAndSortLargeNumbers();

        // 10. Complex Filtering with Optional Data ....
        complexFilteringWithOptionalData();
    }

    /**
     * 01. Filter Strings by Predicate (Starts With a Letter)
     * Filter out all strings that don’t start with the letter 'A'.
     */
    public static void filterStringsByPredicate(){
        System.out.println("**** filterStringsByPredicate() ****");

        List<String> names = List.of("Alice", "Bob", "Alex", "Charlie", "Anna");

        List<String> output = names.stream()
                        .filter(str -> !str.startsWith("A"))
                                .collect(Collectors.toUnmodifiableList());

        System.out.println("Strings that don’t start with the letter 'A': " + output);

        addDivider();
    }

    /**
     * 02. Filter and Count.
     * Count all integers greater than 5 in a list.
     */
    public static void filterAndCountAllIntegersGreaterThan5(){
        System.out.println("**** filterAndCountAllIntegersGreaterThan5() ****");

        List<Integer> numbers = List.of(4, 6, 9, 7, 2, 3, 8);

        long output = numbers.stream()
                .filter(num -> num > 5)  // Keep numbers greater than 5 ....
                .count(); // Count the remaining elements ....

        System.out.println("Count of integers greater than 5: " + output);

        addDivider();
    }


    /**
     * 03. Filter Distinct Elements.
     * Filter distinct numbers that are even.
     */
    public static void filterDistinctNumbersThatAreEven(){
        System.out.println("**** filterDistinctNumbersThatAreEven() ****");

        List<Integer> numbers = List.of(2, 4, 4, 6, 8, 10, 8, 6);

        long output = numbers.stream()
                .filter(num -> num % 2 == 0)  // Keep only Even numbers ....
                .distinct()
                .count(); // Count the distinct Even numbers ....
        System.out.println("Count of distinct Even numbers: " + output);

        addDivider();
    }

    /**
     * 04. Multiple Conditions in Filters.
     * Filter numbers that are greater than 5 and divisible by 3.
     *
     * Explanation:
     * Predicate with &&:
     *  Combines two conditions:
     *      num > 5: Number must be greater than 5.
     *      num % 3 == 0: Number must be divisible by 3.
     *
     * toList():
     * Converts the filtered stream into a list.
     */
    public static void filterNumbersThatAreGreaterThan5AndDivisibleBy3(){
        System.out.println("**** filterNumbersThatAreGreaterThan5AndDivisibleBy3() ****");

        List<Integer> numbers = List.of(3, 6, 9, 12, 14, 15, 17);

        List<Integer> output = numbers.stream()
                .filter(num -> num > 5 && num % 3 == 0)  // Keep only Even numbers ....
                .distinct()
                .collect(Collectors.toUnmodifiableList()); // Count the distinct Even numbers ....
        System.out.println("Numbers That Are Greater Than 5 And Divisible By 3: " + output);

        addDivider();
    }

    /**
     * 05. Filter Objects Based on Field.
     * Filter out employees whose salary is less than 50,000.
     *
     * Explanation:
     * Lambda Expression (emp -> emp.salary >= 50000):
     * Filters employees based on their salary.
     *
     * Integration:
     * Combine filters with terminal operations (like collect()) to directly produce a new list.
     */
    public static void filterEmployeesWithSalaryLessThan50000(){
        System.out.println("**** filterEmployeesWithSalaryLessThan50000() ****");

        List<Employee> employees = List.of(
                new Employee("Alice", 60000),
                new Employee("Bob", 40000),
                new Employee("Charlie", 55000)
        );

        List<Employee> output = employees.stream()
                .filter(emp -> emp.getSalary() < 50000)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("Employees With Salary Less Than 50000: " + output);

        addDivider();
    }


    /**
     * 06. Filter Nested Lists.
     * You have a List<List<Integer>> where each inner list represents a set of numbers. Filter out inner lists if they contain any number less than 5,
     * and then flatten the filtered lists into a single list.
     *
     * Explanation:
     * filter(innerList -> innerList.stream().allMatch(num -> num >= 5)):
     *
     * Examines each inner list and keeps it if all numbers satisfy the condition (num >= 5).
     * Uses allMatch() to verify that all elements pass the predicate.
     * flatMap(List::stream):
     *
     * Flattens the filtered nested lists into a single-level stream.
     * Result: The inner lists [5, 7, 9] and [8, 12, 6] are kept, flattened to [5, 7, 9, 8, 12, 6].
     *
     * NOTE: The below approach FAILS (using flatMap first, as we usually do with List of Lists):
     * List<Integer> output = nestedLists.stream()
     *                         .flatMap(subList -> subList.stream()
     *                                 .allMatch(num -> num >= 5))
     *                                 .collect(Collectors.toUnmodifiableList());
     *
     * REASON: The issue with your approach is that you're applying allMatch directly inside the flatMap() step, which is incorrect in this context.
     * The flatMap method requires you to map elements to a stream, whereas allMatch produces a boolean value.
     *
     * Error Explained:
     * flatMap() Requirement:
     * flatMap() takes a mapping function that transforms each element into a stream (e.g., Stream<R>). These sub-streams are then flattened into a single stream.
     *
     * allMatch Issue:
     * .allMatch(num -> num >= 5) returns a boolean for each sublist, indicating whether all elements in the sublist satisfy the condition (num >= 5).
     * A boolean is not a stream—flatMap cannot work with non-stream values.
     *
     * What You Need Instead:
     * You first need to filter entire sublists based on the condition using filter() on the top-level nestedLists stream. After filtering the sublists, you can use flatMap() to flatten them into a single stream.
     */
    public static void filterInnerListForNumbersLessThan5(){
        System.out.println("**** filterInnerListForNumbersLessThan5() ****");

        List<List<Integer>> nestedLists = List.of(
                List.of(5, 7, 9),   // Keep (all numbers >= 5)
                List.of(4, 10, 15), // Remove (contains 4)
                List.of(8, 12, 6),  // Keep
                List.of(3, 2)       // Remove (all are < 5)
        );

        List<Integer> output = nestedLists.stream()
                        .filter(subList -> subList.stream().allMatch(num -> num >= 5)) // Retain lists with all numbers >= 5
                                .flatMap(subList -> subList.stream()) // Flatten the lists into a single stream
                                        .collect(Collectors.toUnmodifiableList());

        System.out.println("Lists flattened with elements greater than equal to 5: " + output);

        addDivider();
    }


    /**
     * 07. Filter Employees Based on Multiple Conditions.
     *
     * Problem:
     * From a list of Employee objects, filter employees who:
     *
     * Earn more than $50,000.
     * Are younger than 40 years old.
     * Have a name starting with 'A' OR have "Manager" in their role.
     *
     * Explanation:
     * filter(emp -> emp.salary > 50000):
     *
     * Retains employees earning more than $50,000.
     * filter(emp -> emp.age < 40):
     *
     * Chains another condition to filter only employees younger than 40 years old.
     * filter(emp -> emp.name.startsWith("A") || emp.role.contains("Manager")):
     *
     * Adds a condition that either their name starts with 'A' or their role contains the word "Manager".
     * Integration with toList():
     *
     * Collects the result into a list.
     */
    public static void filterEmployeesBasedOnMultipleConditions(){
        System.out.println("**** filterEmployeesBasedOnMultipleConditions() ****");

        List<Employee2> employees = List.of(
                new Employee2("Alice", 30, 60000, "Manager"),
                new Employee2("Bob", 45, 55000, "Developer"),
                new Employee2("Alex", 28, 48000, "Manager"),
                new Employee2("Charlie", 35, 52000, "Analyst"),
                new Employee2("Anna", 38, 58000, "Manager")
        );

        List<Employee2> output = employees.stream()
                        .filter(e -> (e.getSalary() > 50000 && e.getAge() < 40 && (e.getName().startsWith("A") || e.getRole().equals("Manager"))))
                                .collect(Collectors.toUnmodifiableList());

        // NOTE: We can use one method multiple times ....
        List<Employee2> output2 = employees.stream()
                .filter(e -> (e.getSalary() > 50000))
                .filter(e -> e.getAge() < 40)
                .filter(e -> (e.getName().startsWith("A") || e.getRole().equals("Manager")))
                .collect(Collectors.toUnmodifiableList());

        System.out.println("Filtered Employees: " + output2);


        addDivider();
    }

    /**
     * 08. Filter Words with Nested Conditions.
     * Problem:
     * Filter words that:
     *
     * Contain more than 5 characters.
     * Do not contain the letter 'e'.
     * Start with a vowel (A, E, I, O, U).
     *
     * NOTE: We can perform multiple line of operations inside filter() lambda adn return the output boolean ....
     */
    public static void filterWordsWithNestedConditions(){
        System.out.println("**** filterWordsWithNestedConditions() ****");

        List<String> words = List.of("Apple", "Orange", "Banana", "Umbrella", "Pineapple", "Avocado", "Egg");

        List<String> output = words.stream()
                        .filter(s -> s.length() > 5)
                        .filter(e -> !e.contains("e"))
                        .filter(s -> {
                            char ch = s.toLowerCase().charAt(0);
                            return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch =='u';
                        })
                        .toList();

        System.out.println("Filtered Words With Nested Conditions: " + output);

        addDivider();
    }


    /**
     * 09. Filter and Sort Large Numbers
     * Problem:
     * From a list of numbers, filter out:
     *
     * Numbers divisible by 3.
     * Retain only those greater than 50.
     * Sort in descending order.
     *
     */
    public static void filterAndSortLargeNumbers(){
        System.out.println("**** filterAndSortLargeNumbers() ****");

        List<Integer> numbers = List.of(15, 33, 45, 12, 60, 78, 53, 89, 102);

        List<Integer> output = numbers.stream()
                        .filter(n -> n % 3 == 0)
                        .filter(n -> n > 50)
                        .sorted(Comparator.reverseOrder())
                        .toList();

        System.out.println("Filtered And Sorted Large Numbers: " + output);
        addDivider();
    }


    /**
     * 10. Complex Filtering with Optional Data
     * Problem:
     * From a list of optional strings, filter and collect only non-empty optional values that:
     *
     * Contain the substring 'Java'.
     * Have a length greater than 6.
     */
    public static void complexFilteringWithOptionalData(){
        System.out.println("**** filterAndSortLargeNumbers() ****");

        List<Optional<String>> optionalWords = List.of(
                Optional.of("JavaStreams"),
                Optional.empty(),
                Optional.of("Programming"),
                Optional.of("AdvancedJava"),
                Optional.empty()
        );

        /**
         * What This Does:
         * flatMap(Optional::stream):
         *
         * Converts a stream of Optional<String> into a stream of non-empty plain strings.
         * Empty Optional objects are removed, because Optional::stream returns an empty stream for Optional.empty().
         * Example:
         *
         * Input: [Optional.of("Java is cool"), Optional.empty(), Optional.of("AdvancedJava"), Optional.empty()]
         * After flatMap(Optional::stream)
         */
        List<String> output = optionalWords.stream()
                        .flatMap(subList -> subList.stream())
                        .filter(op -> op.contains("Java") && op.length() > 6)
                        .toList();

        /**
         * Advantages of filter() with isPresent():
         * Ensures explicit control over filtering logic.
         * Avoids unchecked access to Optional.get().
         * Useful when you want to retain Optional objects instead of plain strings (e.g., for further processing).
         */
        List<Optional<String>> output2 = optionalWords.stream()
                        .filter(op -> {
                            if(op.isPresent())
                                return true;
                            else
                                return false;
                        })
                .filter(op -> op.get().contains("Java") && op.get().length() > 6)
                .toList();

        // Using Map to convert Optional to String ....
        List<String> output3 = optionalWords.stream()
                .filter(op -> {
                    if(op.isPresent())
                        return true;
                    else
                        return false;
                })
                .filter(op -> op.get().contains("Java") && op.get().length() > 6)
                .map(op -> op.get())
                .toList();

        System.out.println("Filtered And Sorted Large Numbers: " + output2);
        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
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

    static class Employee2 {
        String name;
        int age;
        double salary;
        String role;

        Employee2(String name, int age, double salary, String role) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.role = role;
        }

        @Override
        public String toString() {
            return name + " (" + age + " years, $" + salary + ", Role: " + role + ")";
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

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

}
