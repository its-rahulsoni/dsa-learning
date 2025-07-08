package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

import java.util.Arrays;

public class FibonacciSequence {

    public static void main(String[] args) {

        int count = 7;
        int[] fib_recursion = new int[count];
        Arrays.fill(fib_recursion, -1);

        int[] fib_tabulation = new int[count];
        Arrays.fill(fib_tabulation, -1);

        fibonacciSequenceUsingRecursion(count - 1, fib_recursion);
        System.out.println("Fibonacci Sequence - Recursion: " + Arrays.toString(fib_recursion));


        fibonacciSequenceUsingTabulation(count - 1, fib_tabulation);
        System.out.println("Fibonacci Sequence - Tabulation: " + Arrays.toString(fib_tabulation));
    }

    public static int fibonacciSequenceUsingRecursion(int count, int[] fib){

        if(fib[count] != -1){
            return fib[count];
        }

        if(count == 0 || count == 1){
            return fib[count] = 1;
        }

        fib[count] = fibonacciSequenceUsingRecursion(count - 1, fib) + fibonacciSequenceUsingRecursion(count - 2, fib);

        return fib[count];
    }



    public static void fibonacciSequenceUsingTabulation(int count, int[] fib){

        fib[0] = fib[1] = 1;

        for(int i=2;i<=count;i++){
            fib[i] = fib[i - 1] + fib[i - 2];
        }
    }

}
