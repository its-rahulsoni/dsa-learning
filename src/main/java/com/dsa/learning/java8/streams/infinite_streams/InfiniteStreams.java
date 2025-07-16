package com.dsa.learning.java8.streams.infinite_streams;

import java.util.Random;
import java.util.stream.Stream;

public class InfiniteStreams {

    public static void main(String[] args) {

        // 01. Infinite Stream of Odd Numbers ....
        oddNumbersStream();

        // 02.Infinite Stream of Even Numbers ....
        evenNumbersStream();

        // 03.Infinite Stream of Random Numbers ....
        randomNumbersStream();

        // 04. Infinite Stream of Normal Numbers from 1 to N ....
        normalNumbersStream();

    }

    /**
     * 01.Infinite Stream of Odd Numbers
     * An odd number is simply a number that is not divisible by 2 (e.g., 1, 3, 5, 7, ...).
     *
     * We can use Stream.iterate to increment numbers starting from 1.
     *
     * Explanation:
     * Stream.iterate(1, n -> n + 2): Starts with 1 and increments by 2, generating odd numbers.
     * .limit(10): Ensures we process only the first 10 odd numbers.
     */
    public static void oddNumbersStream(){
        System.out.println("**** oddNumbersStream() ****");

        Stream.iterate(1 , n -> n +2) // Start at 1 and keep adding 2
                        .limit(10)  // Limit to the first 10 numbers (to avoid infinite output)
                                .forEach(System.out::println); // Print numbers

        addDivider();
    }

    /**
     * 02.Infinite Stream of Even Numbers.
     * An even number is divisible by 2 (e.g., 2, 4, 6, 8, ...).
     *
     * Similar to the odd number stream, we can use Stream.iterate, starting from 2.
     *
     * Explanation:
     * Stream.iterate(2, n -> n + 2): Starts with 2 and increments by 2, generating even numbers.
     */
    public static void evenNumbersStream(){
        System.out.println("**** evenNumbersStream() ****");

        Stream.iterate(2, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        addDivider();
    }

    /**
     * 03.Infinite Stream of Random Numbers.
     * Random numbers are different because they don't follow a pattern. For this, we can use Stream.generate and a Random instance.
     */
    public static void randomNumbersStream(){
        System.out.println("**** randomNumbersStream() ****");

        Random random = new Random();

        Stream.generate(() -> random.nextInt(50))
                .limit(10)
                .forEach(System.out::println);
        addDivider();
    }

    /**
     * 04.Infinite Stream of Normal Numbers from 1 to N.
     *
     * You can generate a stream of consecutive numbers starting from 1 using Stream.iterate. If you want numbers from 1 to n (finite), you can use .limit().
     */
    public static void normalNumbersStream(){
        System.out.println("**** normalNumbersStream() ****");

        Stream.iterate(1 , n -> n + 1)
                        .limit(5)
                                .forEach(System.out::println);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }
}
