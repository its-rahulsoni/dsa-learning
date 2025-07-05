package com.dsa.learning.queues.blockingqueues;

import com.dsa.learning.models.EmployeeDTO;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue
 * Type: Unbounded (or optionally bounded) blocking queue backed by a linked list.
 * Blocks the producer if the queue reaches its capacity (for bounded queues) and blocks the consumer if the queue is empty.
 */
public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
        Comparator<EmployeeDTO> comparator = Comparator.comparingDouble(EmployeeDTO::getSalary);

        LinkedBlockingQueue<EmployeeDTO> employeeQueue = new LinkedBlockingQueue<>(2);

        /**
         * Producer Thread (add to Queue):
         *
         * Adds elements to the PriorityBlockingQueue one at a time, simulating incremental production.
         * Uses put() to add elements, blocking if the queue is full (though the queue is unbounded, so this won't happen here).
         */
        Thread producer = new Thread(() -> {
            try {
                employeeQueue.add(new EmployeeDTO(1, 25, "Alice", 50000)); // Blocks if queue is full
                System.out.println("Added Alice");
                Thread.sleep(1000);

                employeeQueue.add(new EmployeeDTO(2, 48, "Bob", 60000)); // Blocks if queue is full
                System.out.println("Added Bob");
                Thread.sleep(1000);

                employeeQueue.add(new EmployeeDTO(3, 35, "Charlie", 70000)); // Blocks if queue is full
                System.out.println("Added Charlie");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        /**
         * Consumer Thread (take from Queue):
         *
         * Repeatedly retrieves and processes employees using take().
         * Blocking Behavior: If the queue is empty, take() blocks until an element becomes available.
         */
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000); // Simulate processing time
                    EmployeeDTO employee = employeeQueue.take(); // Blocks if the queue is empty
                    System.out.println("Processing: " + employee);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}