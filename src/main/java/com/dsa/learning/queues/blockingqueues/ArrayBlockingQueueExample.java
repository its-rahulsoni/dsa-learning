package com.dsa.learning.queues.blockingqueues;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue
 * Type: Bounded, fixed-size blocking queue.
 * Blocks a producer if the queue is full and blocks a consumer if the queue is empty.
 *
 * Characteristics:
 * Uses a predefined capacity specified when initializing the queue.
 * FIFO (First-In-First-Out) ordering.
 * Useful for bounded producer-consumer scenarios to prevent excessive memory usage.
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2); // Capacity of 2

        // Producer thread
        new Thread(() -> {
            try {
                queue.put(1); // Blocks if queue is full
                System.out.println("Added: 1");
                queue.put(2); // Blocks if queue is full
                System.out.println("Added: 2");

                // Will block here until consumer removes an element. In the output, we can see that flow blocks temporarily.
                queue.put(3);
                System.out.println("Added: 3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Simulate processing time
                System.out.println("Removed: " + queue.take()); // Blocks if queue is empty
                System.out.println("Removed: " + queue.take()); // Blocks if queue is empty
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}