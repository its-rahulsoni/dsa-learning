package com.dsa.learning.java8.functional_interfaces;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicImplOfFIs {

    public static void main(String[] args) {

        predicateImpl();

        consumerImpl();

        functionImpl();

        supplierImpl();

        // Revision ....
        Predicate<String> pr = s -> {
            if(s.length() == 3)
                return true;
            else
                return false;
        };

        Function<Integer, Integer> f = a -> {
            return a * 5;
        };

        Consumer<String> c = s -> System.out.println("S: " + s);

        Supplier<Integer> s = () -> { return 100; };

        System.out.println("P: " + pr.test("string"));
        System.out.println("F: " + f.apply(3));
        System.out.println("C: " ); c.accept("asasa");
        System.out.println("S: " + s.get());
    }

    public static void predicateImpl(){
        System.out.println("**** predicateImpl() ****");

        Predicate<Integer> testingPredicate = n -> {
            if(n % 2 == 0)
                return true;
            else
                return false;
        };

        if(testingPredicate.test(3)){
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }

        addDivider();
    }

    public static void consumerImpl(){
        System.out.println("**** consumerImpl() ****");

        Consumer<String> testingConsumer = str -> System.out.println("Name passed is: " + str);

        testingConsumer.accept("Rahul Soni");

        addDivider();
    }

    public static void functionImpl(){
        System.out.println("**** functionImpl() ****");

        Function<Integer, String> functionTesting = n -> {
            if(n % 2 == 0)
                return "No is Even";
            else
                return "No is Odd";
        };

        System.out.println("Functional Interface Result: " + functionTesting.apply(3));

        addDivider();
    }

    /**
     * Explanation (Line by Line):
     * Supplier<T>:
     *
     * A functional interface that supplies values without taking any arguments.
     *
     * get(): Returns the generated value.
     *
     * Lambda Expression (() -> UUID.randomUUID().toString()):
     *
     * Generates a random UUID string.
     *
     * randomUUID.get():
     *
     * Gets the supplied value.
     */
    public static void supplierImpl(){
        System.out.println("**** supplierImpl() ****");

        Supplier<String> supplierTesting = () -> {return "New String";}; // return here is optional ....

        System.out.println("Supplier Functional Interface Result: " + supplierTesting.get());

        // Define a Supplier for random strings ....
        Supplier<String> randomString = () -> UUID.randomUUID().toString();

        // Get the supplied value ....
        String randomUUID = randomString.get();
        System.out.println("Random UUID generated is: " + randomUUID);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }



}
