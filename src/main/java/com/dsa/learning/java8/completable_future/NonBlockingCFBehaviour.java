package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;

public class NonBlockingCFBehaviour {

    public static void main(String[] args) {
        // CompletableFuture to simulate a task taking 3 seconds
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task is running...");
            try {
                Thread.sleep(3000); // Simulate long computation (3 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

        System.out.println("Main thread is doing some work...");

        // Attach a non-blocking callback to process the result once it's ready
        completableFuture.thenAccept(result -> System.out.println("Result: " + result));

        System.out.println("Main thread continues working without waiting...");
        // The main thread is not blocked while the task runs asynchronously

        /**
         * .join():
         *
         * CompletableFuture is non-blocking, meaning the main thread isn't responsible for waiting until the
         * asynchronous computation finishes unless explicitly told to.
         *
         * If you want to see the final output (e.g., the callback executing and processing the result of the task)
         * before the program exits, you can ensure that the main thread waits for the asynchronous task to complete.
         *
         * Main method exists if join() is NOT used.
         */
        completableFuture.join();

        System.out.println("Process finished.");
    }

}
