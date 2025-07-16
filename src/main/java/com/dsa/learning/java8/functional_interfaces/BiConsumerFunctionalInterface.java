package com.dsa.learning.java8.functional_interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * What Is the Use of BiConsumer Functional Interface?
 *
 * Problem:
 * Write a program using BiConsumer to merge two strings.
 *
 * BiConsumer Functional Interface:
 *
 * Functional Interface: BiConsumer is a functional interface, meaning it has a single abstract method (SAM) called accept.
 * Two Input Arguments: It accepts two arguments of types T and U, which can be any object types.
 * No Return Value: It performs an operation on these two arguments and does not return any value (void).
 * Purpose: BiConsumer is useful when you have a function that needs to process two input values and you don't need it to return any result.
 *          It's often used for operations that have side effects, such as modifying objects or printing to the console.
 */
public class BiConsumerFunctionalInterface {

    public static void main(String[] args) {

        /**
         * Define a BiConsumer to concatenate strings.
         *
         * BiConsumer<T, U>:
         * A functional interface for consuming two arguments (T and U).
         *
         * accept(T t, U u): Consumes both arguments and performs an action.
         */
        BiConsumer<String, String> consumer = (s1, s2) -> System.out.println("Concatenated string: " + s1 + " - " + s2);

        // Apply the BiConsumer ....
        consumer.accept("Rahul" , "Soni");

        populateHashMap();
    }

    public static void populateHashMap(){
        Map<String, Integer> map = new HashMap<>();

        // Using BiConsumer to add elements to the map ....
        BiConsumer<String, Integer> consumer = (str, num) -> map.put(str, num);

        consumer.accept("Rahul", 1);
        consumer.accept("Akash", 2);

        for(Map.Entry<String, Integer> m : map.entrySet()){
            System.out.println("Key: " + m.getKey() + " Value: " + m.getValue());
        }
    }

}
