package com.dsa.learning.queues.blockingqueues;

import com.dsa.learning.models.EmployeeDTO;

import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The PriorityBlockingQueue is a thread-safe priority queue provided in the java.util.concurrent package in Java.
 * It is designed for use in concurrent scenarios and is especially useful when multiple threads need to access
 * and process elements based on priority.
 *
 * Blocking Nature:
 * Unlike PriorityQueue, it does not block waiting for elements when the queue is empty.
 * In PriorityBlockingQueue, if the queue is empty, poll() will simply return null instead of blocking,
 * and if it's full (though it's unbounded), producers won’t be blocked because it grows dynamically.
 * Instead, consumers must handle the null return for operations like poll().
 */
public class PriorityBlockingQueueWithComparator {

    public static void main(String[] args) {

        Comparator<EmployeeDTO> comparator = Comparator.comparingDouble(EmployeeDTO::getSalary);

        Queue<EmployeeDTO> employeeQueue = new PriorityBlockingQueue<>(
                10,
                comparator);

        employeeQueue.add(new EmployeeDTO(1, 25, "Alice", 50000));
        employeeQueue.add(new EmployeeDTO(2, 48, "Bob", 60000));
        employeeQueue.add(new EmployeeDTO(3, 35, "Charlie", 50000));
        employeeQueue.add(new EmployeeDTO(4, 20, "Diana", 75000));
        employeeQueue.add(new EmployeeDTO(5, 20, "Bob", 60000));
        employeeQueue.add(new EmployeeDTO(6, 58, "Diana", 75000));

        while (!employeeQueue.isEmpty()) {
            System.out.println(employeeQueue.poll()); // Retrieves and removes smallest salary each time
        }
    }

}
