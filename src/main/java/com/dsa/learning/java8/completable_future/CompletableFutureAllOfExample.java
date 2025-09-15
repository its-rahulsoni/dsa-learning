package com.dsa.learning.java8.completable_future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture.allOf()
 * The CompletableFuture.allOf() method is a static method provided in the CompletableFuture API that allows you to combine multiple CompletableFuture tasks.
 * It is used when you have a collection of tasks (or multiple parallel computations) and want to wait for all of them to complete before proceeding with any further actions.
 *
 * Task Creation:
 *
 * Three asynchronous tasks (future1, future2, future3) are created using CompletableFuture.runAsync(). Each task simulates a different delay.
 * Combining Futures:
 *
 * CompletableFuture.allOf(future1, future2, future3) combines all the tasks.
 * It returns a CompletableFuture<Void> that completes when ALL three individual futures complete.
 * Waiting for Completion:
 *
 * combinedFuture.join() blocks the main thread until all the tasks are complete.
 * Final Action:
 *
 * Once all tasks are finished, "All tasks completed!" is printed.
 */
public class CompletableFutureAllOfExample {

    public static void main(String[] args) {

        usingAllOfMethod();

        usingAllOfWithException();

        
    }

    public static void usingAllOfMethod(){

        System.out.println("**** usingAllOfMethod() ****");
        // Create three independent CompletableFuture tasks
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 started...");
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1 completed.");
            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2 started...");
            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2 completed.");
            return 20;
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3 started...");
            try {
                Thread.sleep(3000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 3 completed.");
            return 30;
        });

        // Combine all futures with CompletableFuture.allOf ....
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        // Block and wait for all futures to complete
        combinedFuture.join();

        List<Integer> resultsFromCF = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println("Results: " + resultsFromCF);

        System.out.println("All tasks completed!");

        addDivider();
    }

    /**
     * What Happens if One Task Fails?
     * If any CompletableFuture in the array passed to allOf fails (throws an exception), the result of the CompletableFuture.allOf()
     * will also fail and complete exceptionally.
     */
    public static void usingAllOfWithException(){
        System.out.println("**** usingAllOfWithException() ****");
        // Create three independent CompletableFuture tasks
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 1 started...");
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1 completed.");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 2 is running...");
            throw new RuntimeException("Task 2 failed!");
        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 3 started...");
            try {
                Thread.sleep(3000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 3 completed.");
        });

        // Combine all futures with CompletableFuture.allOf ....
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        // Block and wait for all futures to complete
        try {
            combinedFuture.join(); // This throws an exception because future2 failed
        } catch (Exception e) {
            System.out.println("Combined task failed: " + e.getMessage());
        }

        System.out.println("All tasks completed!");

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }



}
