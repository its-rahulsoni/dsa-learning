package com.dsa.learning.java8.functional_interfaces;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Chaining Consumer:
 * A Consumer performs an operation on a given input without returning anything (void). You can chain multiple Consumer instances using andThen().
 */
public class CombineMultipleConsumers {

    public static void main(String[] args) {

        usingAndThen();
    }

    /**
     * Explanation:
     * printUpperCase: Prints the string in uppercase.
     * printLowerCase: Prints the string in lowercase.
     * combinedConsumer: Executes both printUpperCase and printLowerCase sequentially.
     */
    public static void usingAndThen(){
        System.out.println("**** usingAndThen() ****");

        String inputString = "Rahul Soni";

        Consumer<String> printUpperCase = str -> System.out.println("Upper case string: " + str.toUpperCase());
        Consumer<String> printLowerCase = str -> System.out.println("Lower case string: " + str.toUpperCase());

        // Chain Consumers Using andThen()
        Consumer<String> combinedConsumer = printUpperCase.andThen(printLowerCase);
        combinedConsumer.accept(inputString);

        // This one will also work ....
        System.out.println("\nCombining both the Consumers ....");
        printUpperCase.andThen(printLowerCase).accept(inputString);

       // NOTE: The below sout won't work bcoz a Consumer DOESN'T return anything, so we can't club it with any message using '+' inside sout ....
       // System.out.println("Final Output of first conversion to Upper Case and then to Lower Case: " + combinedConsumer.accept(inputString));

        addDivider();
    }


    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
