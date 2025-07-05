package com.dsa.learning.queues.priorityqueue;

import com.dsa.learning.models.EmployeeDTO;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueWithCustomComparator {

    public static void main(String[] args) {

        System.out.println("COMPARATOR #01: Polling order in Ascending Order");

        /**
         * What it does: This comparator orders employee objects by their salary in ascending order.
         * Why it works: Comparator.comparingDouble() is a concise way to create comparators for fields of numeric types (like salary).
         */
        Comparator<EmployeeDTO> salaryComparator = Comparator.comparingDouble(EmployeeDTO::getSalary);

        /**
         * We pass the salaryComparator to the constructor of PriorityQueue so that the queue uses priority based on salary.
         */
        Queue<EmployeeDTO> employeeQueue = new PriorityQueue<>(salaryComparator);

        /**
         * Employees are added in arbitrary order, but when retrieved from the queue, they will follow the
         * priority order defined by the comparator.
         */
        employeeQueue.add(new EmployeeDTO(1, 25, "Alice", 50000));
        employeeQueue.add(new EmployeeDTO(2, 48, "Bob", 60000));
        employeeQueue.add(new EmployeeDTO(3, 35, "Charlie", 45000));
        employeeQueue.add(new EmployeeDTO(4, 20, "Diana", 75000));

        System.out.println("Peek element: " + employeeQueue.peek() + "\n"); // Retrieves but doesn't remove the smallest salary

        /**
         * Poll: Removes and retrieves elements based on priority (lowest salary first).
         */
        while (!employeeQueue.isEmpty()) {
            System.out.println(employeeQueue.poll()); // Retrieves and removes smallest salary each time
        }

        System.out.println("\n****************************************");
        System.out.println("****************************************\n");

        System.out.println("COMPARATOR #02: Polling order in Descending Order");

        Comparator<EmployeeDTO> descendingSalaryComparator = Comparator.comparingDouble(EmployeeDTO::getSalary).reversed();

        /**
         * We pass the descendingSalaryComparator to the constructor of PriorityQueue so that the queue uses priority based on salary.
         */
        Queue<EmployeeDTO> employeeQueue2 = new PriorityQueue<>(descendingSalaryComparator);

        /**
         * Employees are added in arbitrary order, but when retrieved from the queue, they will follow the
         * priority order defined by the comparator.
         */
        employeeQueue2.add(new EmployeeDTO(1, 25, "Alice", 50000));
        employeeQueue2.add(new EmployeeDTO(2, 48, "Bob", 60000));
        employeeQueue2.add(new EmployeeDTO(3, 35, "Charlie", 45000));
        employeeQueue2.add(new EmployeeDTO(4, 20, "Diana", 75000));

        System.out.println("Peek element: " + employeeQueue2.peek() + "\n"); // Retrieves but doesn't remove the smallest salary

        /**
         * Poll: Removes and retrieves elements based on priority (lowest salary first).
         */
        while (!employeeQueue2.isEmpty()) {
            System.out.println(employeeQueue2.poll()); // Retrieves and removes smallest salary each time
        }

        System.out.println("\n****************************************");
        System.out.println("****************************************\n");

        System.out.println("COMPARATOR #03: Polling in Descending Order of Salary and if same salary then use Name(ascending) and " +
                "if same salary and name then use Age(descending).");

        /**
         * 1) Comparator.comparingDouble(EmployeeDTO::getSalary).reversed():
         * This part ensures employees are sorted by salary in descending order.
         * Reversing the natural order ensures higher salaries have higher priority in the queue.
         *
         * 2) .thenComparing(EmployeeDTO::getName):
         * If two employees have the same salary, this part sorts employees based on their name in ascending alphabetical order.
         *
         * 3) .thenComparingInt(EmployeeDTO::getAge)
         * If two employees have the same salary and name, this part sorts employees based on their age in descending order.
         */
        Comparator<EmployeeDTO> comparatorWithTwoComparisions = Comparator
                .comparingDouble(EmployeeDTO::getSalary).reversed() // First sort: Descending salary
                .thenComparing(EmployeeDTO::getName)               // Second sort: Ascending name
                .thenComparing(EmployeeDTO::getAge).reversed();

        Queue<EmployeeDTO> employeeQueue3 = new PriorityQueue<>(comparatorWithTwoComparisions);
        employeeQueue3.add(new EmployeeDTO(1, 25, "Alice", 50000));
        employeeQueue3.add(new EmployeeDTO(2, 48, "Bob", 60000));
        employeeQueue3.add(new EmployeeDTO(3, 35, "Charlie", 50000));
        employeeQueue3.add(new EmployeeDTO(4, 20, "Diana", 75000));
        employeeQueue3.add(new EmployeeDTO(5, 20, "Bob", 60000));
        employeeQueue3.add(new EmployeeDTO(6, 58, "Diana", 75000));

        while (!employeeQueue3.isEmpty()) {
            System.out.println(employeeQueue3.poll()); // Retrieves and removes smallest salary each time
        }

    }

}
