package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture provides several methods to handle exceptions, allowing for both recovery and propagation of errors.
 */
public class ExceptionHandlinginCF {

    public static void main(String[] args) {

        exceptionallyMethod();

        handleMethod();

        whenCompleteMethod();

        exceptionChaining();
    }

    /**
     * exceptionally():
     * Used to handle exceptions when a computation or callback in CompletableFuture fails.
     * Allows you to specify fallback logic or return an alternative result when an exception occurs.
     */
    public static void exceptionallyMethod(){
        System.out.println("***** exceptionallyMethod() *****");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task started...");
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return "Hello World!";
        }).exceptionally(ex -> {
            System.out.println("Error occurred: " + ex.getMessage());
           // ex.printStackTrace();
            return "Exception occurred";
        });

        System.out.println("Result from Completable Future supply(): " + completableFuture.join());

        addDivider();
    }

    /**
     * handle():
     * Combines both result processing and exception handling in a single step.
     * Useful when you want to handle success and failure cases in the same block.
     *
     * The handle() method provides:
     * Result of the computation if it was successful (or null in case of failure).
     * Exception if one occurred (or null if no exception occurred).
     */
    public static void handleMethod(){
        System.out.println("***** handleMethod() *****");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task started...");
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return "Hello World!";
        }).handle((result, ex) -> {
            if(ex != null){
                System.out.println("Exception occurred: " + ex.getMessage());
                //ex.printStackTrace();
                return "Exception occurred";
            }
            return result;
        });

        System.out.println("Result from Completable Future supply(): " + completableFuture.join());

        addDivider();
    }


    /**
     * whenComplete():
     * Allows observation of the result or exception when the computation is completed but does not modify the value.
     * It accepts both the result (if successful) or the exception (if failed), and it doesn’t change the outcome of the CompletableFuture.
     */
    public static void whenCompleteMethod(){
        System.out.println("***** whenCompleteMethod() *****");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task started...");
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return "Hello World!";
        });

        completableFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error occurred: " + ex.getMessage());
            } else {
                System.out.println("Computation succeeded with result: " + result);
            }
        });

        try {
            completableFuture.join();
        } catch (Exception e) {
            System.out.println("Caught exception in main: " + e.getMessage());
        }

        addDivider();
    }


    /**
     * Multiple Exception Handling in a Chain.
     * Sometimes you'll need to handle different exceptions at different stages of the chain.
     */
    public static void exceptionChaining(){
        System.out.println("***** exceptionChaining() *****");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task started...");
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return 1;
        }).exceptionally(ex -> {
            System.out.println("Step 1 recovered: " + ex.getMessage());
            return 2;
        }).thenApply(result -> {
            System.out.println("Processing result: " + result);
            if (true){
                throw new IllegalArgumentException("Step 2 invalid input!");
            }
            return result * 2;
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Error during step 2: " + ex.getMessage());
                return null; // Recover by returning null
            }
            return result;
        });

        System.out.println("Final Result: " + completableFuture.join());
        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
