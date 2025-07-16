package com.dsa.learning.java8.functional_interfaces;

import java.util.function.Supplier;

/**
 * Chaining Supplier:
 * A Supplier generates or supplies a value (without input). Though Supplier doesn’t have built-in support for chaining,
 * you can wrap the output of one supplier and use it as input to another.
 */
public class CombineMultipleSuppliers {

    public static void main(String[] args) {

        combiningSupplier();
    }

    /**
     * Explanation:
     * supplier1: Generates the string "Hello".
     * supplier2: Uses the output of supplier1 ("Hello") and adds additional text (", World!").
     */
    public static void combiningSupplier(){
        System.out.println("**** combiningSupplier() ****");

        Supplier<String> supplier1 = () -> "Rahul";
        Supplier<String> supplier2 = () -> supplier1.get() + "Soni";

        // Apply the chained Function ....
        System.out.println("First Supplier1 will be outputted inside Supplier2's get() call, and then its own sout will get printed: " + supplier2.get());

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
