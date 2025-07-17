package com.dsa.learning.java8.completable_future;

import java.util.concurrent.CompletableFuture;

public class ChainingCompletableFutures {

    public static void main(String[] args) {

        // Step 1: Create the first CompletableFuture ....
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing first task...");
            return 10;
        });

        // Step 2: Create the second CompletableFuture that depends on the result of future1 ....
        // thenApply(): It transforms the result of a CompletableFuture. It’s used when you want to perform further computation on the result synchronously.
        CompletableFuture<Integer> completableFuture2 = completableFuture1.thenApply(resultFromFirstCF -> {
            System.out.println("Executing second task...");
            return resultFromFirstCF * 4;
        });

        // Step 3: Create the third CompletableFuture for consuming the transformed result ....
        // thenAccept(): It is used for tasks that handle the result without returning a value.
        // If you want to consume the result but don’t need to pass anything further down the chain, use thenAccept().
        CompletableFuture<Void> completableFuture3 = completableFuture2.thenAccept(resultFromSecondCF -> {
            System.out.println("Executing third task with final output: " + resultFromSecondCF);
        });

        // Step 4: Block and wait until everything is complete ....
        completableFuture3.join();

        System.out.println("All tasks completed!");
    }

}
