package com.dsa.learning.java8.functional_interfaces;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Chaining Supplier:
 * A Supplier generates or supplies a value (without input). Though Supplier doesn’t have built-in support for chaining,
 * you can wrap the output of one supplier and use it as input to another.
 */
public class CombineMultipleSuppliers {

    public static void main(String[] args) {

        combiningSupplier();

        combingSupplierWithFunction();
    }

    /**
     * Explanation:
     * supplier1: Generates the string "Hello".
     * supplier2: Uses the output of supplier1 ("Hello") and adds additional text (", World!").
     *
     * We cannot directly combine two Supplier instances using andThen(), compose(), or similar methods because:
     *
     * 1. Supplier is a Producer (No Input)
     * Supplier<T> has no input parameter (only T get()).
     *
     * Methods like andThen() (from Function) require:
     * An input to chain operations.
     * A return value to pass to the next function.
     */
    public static void combiningSupplier(){
        System.out.println("**** combiningSupplier() ****");

        Supplier<String> supplier1 = () -> "Rahul";
        Supplier<String> supplier2 = () -> supplier1.get() + "Soni";

        // Apply the chained Function ....
        System.out.println("First Supplier1 will be outputted inside Supplier2's get() call, and then its own sout will get printed: " + supplier2.get());

        addDivider();
    }

    public static void combingSupplierWithFunction(){
        System.out.println("**** combingSupplierWithFunction() ****");

        Function<Integer, Integer> f1 = a -> a * a;
        Supplier<Integer> s1 = () -> 5;

        System.out.println("Using Supplier to pass input to Function FI: " + f1.apply(s1.get()));


        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
