package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Using a Custom Executor
 * Both runAsync() and supplyAsync() allow you to pass a custom executor for running the task instead
 * of the default ForkJoinPool.commonPool.
 */
public class CreateCFUsingCustomExecutor {

    /**
     * Explanation:
     *
     * Tasks are executed in the ThreadPool defined by the custom executor.
     * runAsync() and supplyAsync() both make use of the executor.
     * Always shutdown the executor after usage to avoid resource leaks.
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> futureRunObject = CompletableFuture.runAsync(() -> {
            // Simulate a long-running task
            System.out.println("RunAsync Task started using a thread of Executor Service: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("RunAsync Task completed!");
        }, executorService);

        CompletableFuture<String> futureSupplyObject = CompletableFuture.supplyAsync(() -> {

            // Simulate a long-running task
            System.out.println("SupplyAsync Task started using a thread of Executor Service: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("SupplyAsync Task completed!");
            return "Hello from supplyAsync task!";
        },executorService);

        // Continue with the program while the task runs asynchronously ....
        System.out.println("Main Thread is free now: " + Thread.currentThread().getName());

        futureRunObject.join();
        String supplyOutput = futureSupplyObject.join();

        System.out.println("Main thread stopped after async task and output received is: " + supplyOutput);

        // Shutdown the custom executor ....
        executorService.shutdown();
    }

}
