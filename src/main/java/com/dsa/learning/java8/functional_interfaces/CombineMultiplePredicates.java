package com.dsa.learning.java8.functional_interfaces;

import java.util.function.Predicate;

/**
 * How to Combine Multiple Predicates Using and, or, and negate?
 *
 * Problem:
 * Validate numbers by combining multiple conditions:
 * A number must be even and greater than 10.
 *
 * Explanation (Line by Line):
 *
 * Predicate Chaining (and):
 * Combines two predicates using logical AND.
 * Both conditions (isEven and isGreaterThan10) must be true for the combined result to be true.
 *
 * Lambda Expressions:
 * isEven: Checks divisibility by 2.
 * isGreaterThan10: Checks if the number is greater than 10.
 *
 * and().test(number):
 * Applies both predicates on the input 12.
 */
public class CombineMultiplePredicates {

    public static void main(String[] args) {

        combiningMultiplePredicates();
    }

    public static void combiningMultiplePredicates(){
        System.out.println("**** combiningMultiplePredicates() ****");

        int number = 11;

        // Create predicates for even and greater than 10 ....
        Predicate<Integer> evenPredicate = no -> no % 2 == 0;
        Predicate<Integer> isGreaterThan10 = no -> no % 10 == 0;

        // AND - both the predicates should return TRUE ....
        boolean andResult = evenPredicate.and(isGreaterThan10).test(number);
        System.out.println("Is number " + number + ", even AND divisble by 10: " + andResult);

        // OR - any predicate should return TRUE ....
        boolean orResult = evenPredicate.or(isGreaterThan10).test(number);
        System.out.println("Is number " + number + ", even OR divisble by 10: " + orResult);

        // NEGATE - Negates a predicate (logical NOT). The negate() method simply reverses the output of the predicate (i.e., true becomes false and vice versa) ....
        boolean negateResult = evenPredicate.negate().test(number);
        System.out.println("Is number " + number + ", even after NEGATION: " + negateResult);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
