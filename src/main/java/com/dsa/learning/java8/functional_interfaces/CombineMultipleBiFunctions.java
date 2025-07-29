package com.dsa.learning.java8.functional_interfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Chaining BiFunction:
 * A BiFunction takes two arguments and returns a result. You can chain BiFunction instances by combining the output of one function and passing it to another.
 */
public class CombineMultipleBiFunctions {

    public static void main(String[] args) {

        combiningBiFunctions();

        chainingBiFunctionUsingAndThen();

        BiFunction<Integer,Integer,Integer> b1 = (a,b) -> a * b;
        BiFunction<Integer,Integer,Integer> b2 = (a,b) -> a + b;

        Function<Integer, Integer> f = a -> a * 5;
        System.out.println("Comb: " + b1.andThen(f).apply(3,5));
        System.out.println("Comb: " + b1.apply(b2.apply(4,5), 8)); // The 1st input of B1 is the output of second BiFunction B2 ....
    }

    /**
     * NOTE: The BiFunction accepts 2 inputs and returns 1 Output.
     */
    public static void combiningBiFunctions(){
        System.out.println("**** combiningBiFunctions() ****");

        int i = 5;
        int j = 7;
        int constantFactor = 2;

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        /**
         * APPROACH #01
         *
         * Step 1: Inner Function Call
         * The innermost function is the call to multiply: multiply.apply(i, j)
         * Arguments:
         * i = 5
         * j = 7
         *
         * Computation:
         * The multiply function multiplies the two inputs:
         * multiply.apply(5, 7) => 5 * 7 = 35
         * Result:
         * The result of multiply.apply(i, j) is 35.
         *
         * Step 2: Outer Function Call
         * Next, the result of multiply.apply(i, j) (which is 35) is passed as the first argument to the add function, along with the constantFactor as the second argument:
         *
         * add.apply(35, constantFactor)
         * Arguments:
         * a = 35 (result from multiply)
         * b = constantFactor = 2
         *
         * Computation:
         * The add function adds the two inputs: add.apply(35, 2) => 35 + 2 = 37
         * Result:
         * The result of add.apply(35, constantFactor) is 37.
         */
        int result = add.apply(multiply.apply(i, j), constantFactor); // 1st, inner function MULTIPLY() is called and then outer function ADD() is invoked ....
        System.out.println("***** Approach #01 *****");
        System.out.println("Result of first Multiplication & then Addition of this result with a constant numbers, Constant: " + constantFactor +
                ", i: " + i + ", j: " + j + ", result: " + result);

        /**
         * APPROACH #02
         *
         * Use an Intermediate Step
         * You can manually invoke one BiFunction, store its result, and pass that result into the second BiFunction.
         * This intermediate step effectively combines two BiFunction calls.
         */
        int intermediateResult = multiply.apply(i, j);
        int finalResult = add.apply(intermediateResult, constantFactor);
        System.out.println("\n***** Approach #02 *****");
        System.out.println("Result of first Multiplication & then Addition of this result with a constant numbers, Constant: " + constantFactor +
                ", i: " + i + ", j: " + j + ", result: " + finalResult);

        addDivider();
    }

    public static void chainingBiFunctionUsingAndThen(){
        System.out.println("**** chainingBiFunctionUsingAndThen() ****");

        int i = 5;
        int j = 7;
        int constantFactor = 2;

        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        Function<Integer, Integer> addConstant = x -> x + constantFactor;

        int finalResult = multiply.andThen(addConstant).apply(i, j);

        System.out.println("Result of first Multiplication & then Addition of this result with a constant numbers, Constant: " + constantFactor +
                ", i: " + i + ", j: " + j + ", result: " + finalResult);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
