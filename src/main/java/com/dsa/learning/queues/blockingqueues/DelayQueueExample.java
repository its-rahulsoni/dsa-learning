package com.dsa.learning.queues.blockingqueues;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 * Type: Unbounded queue that holds elements until a specified delay has expired.
 * Blocks consumers until the delay of the head element elapses.
 *
 * Characteristics:
 * Requires elements to implement the interface Delayed to define the delay.
 * Useful in scheduling tasks or rate-limiting scenarios where elements must become available after a delay.
 */
public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        // Add elements with specified delays
        queue.put(new DelayedTask("Task1", 5)); // Delay: 5 seconds
        queue.put(new DelayedTask("Task2", 2)); // Delay: 2 seconds
        queue.put(new DelayedTask("Task3", 10)); // Delay: 10 seconds

        // Consumer retrieves tasks in delay order
        while (!queue.isEmpty()) {
            DelayedTask task = queue.take(); // Blocks until the delay elapses
            System.out.println("Processing: " + task.getName());
        }
    }
}

// A custom task that implements Delayed interface.
class DelayedTask implements Delayed {
    private String name;
    private long delayTime; // Delay time in nanoseconds
    private long expiryTime;

    public DelayedTask(String name, long delaySeconds) {
        this.name = name;
        this.delayTime = TimeUnit.SECONDS.toNanos(delaySeconds);
        this.expiryTime = System.nanoTime() + delayTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expiryTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), o.getDelay(TimeUnit.NANOSECONDS));
    }
}