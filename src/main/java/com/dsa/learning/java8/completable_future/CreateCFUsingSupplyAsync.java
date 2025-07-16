package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * supplyAsync() Method
 * supplyAsync() is used to execute a task asynchronously that returns a result. This is similar to runAsync(),
 * but it is used for tasks that must compute and return a value.
 */
public class CreateCFUsingSupplyAsync {

    public static void main(String[] args) {

        // Run an asynchronous task using supplyAsync ....
        CompletableFuture<String> futureObject = CompletableFuture.supplyAsync(() -> {

            // Simulate a long-running task
            System.out.println("Task started using a thread of ForkJoinPool: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task completed!");
            return "Hello from supplyAsync task!";
        });

        // Continue with the program while the task runs asynchronously ....
        System.out.println("Main Thread is free now: " + Thread.currentThread().getName());

        // Get the result of the asynchronous task (blocking, for demonstration) ....
        String output = futureObject.join();

        System.out.println("Main thread stopped after async task and output received is: " + output);

    }

    /**
     * Explanation:
     *
     * supplyAsync() runs the Supplier asynchronously in the ForkJoinPool.commonPool by default.
     * The Supplier computes a result (String in this case).
     * future.join() blocks the main thread until the result is available, and then prints it.
     */

}
