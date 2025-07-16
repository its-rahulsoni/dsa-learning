package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * runAsync() Method
 * runAsync() is used to execute a non-blocking task asynchronously that does not return a result (a void return type).
 * Think of it as submitting a task for execution where the task doesn't have to return a value.
 */
public class CreateCFUsingRunAsync {

    public static void main(String[] args) {

        // Run an asynchronous task using runAsync ....
        CompletableFuture<Void> futureObject = CompletableFuture.runAsync( () -> {

            // Simulate a long-running task
            System.out.println("Task started using a thread of ForkJoinPool: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task completed!");
        });

        // Continue with the program while the task runs asynchronously ....
        System.out.println("Main Thread is free now: " + Thread.currentThread().getName());

        // Block and wait for the task to complete (optional, to demonstrate result) ....
        futureObject.join();

        System.out.println("Main thread stopped after async task.");

    }

    /**
     * Explanation:
     *
     * runAsync() executes the Runnable asynchronously in the ForkJoinPool.commonPool by default.
     * The Runnable does not return any value, so the CompletableFuture is of type Void.
     * join() ensures the main thread waits until the task is complete; useful for testing.
     */

}
