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
        CompletableFuture<Integer> completableFuture2 = completableFuture1.thenApply(resultFromFirstCF -> {
            System.out.println("Executing second task...");
            return resultFromFirstCF * 4;
        });

        // Step 3: Create the third CompletableFuture for consuming the transformed result ....
        CompletableFuture<Void> completableFuture3 = completableFuture2.thenAccept(resultFromSecondCF -> {
            System.out.println("Executing third task with final output: " + resultFromSecondCF);
        });

        // Step 4: Block and wait until everything is complete ....
        completableFuture3.join();

        System.out.println("All tasks completed!");
    }

}
