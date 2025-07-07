package com.dsa.learning.maths.striver.easy;


/**
 * Concept: https://www.cuemath.com/numbers/lcm-least-common-multiple/
 *
 * THOUGHT PROCESS:
 * We pick up the minimum no amongst the 2. Then we multiply this min no from 1 to ......
 * And check the multiple result to be divisible by both the numbers. If this multiple is divisible by both the numbers,
 * then this is our LCM.
 */
public class LCM {

    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        System.out.println("a: " + a + " & b: " + b + ", GCD: " + findLCMApproach1(a,b));
    }

    public static int findLCMApproach1(int n1, int n2){
        int min = Math.min(n1, n2);
        int i = 1;
        int multiple = 1;

        do {
           multiple = min * i;

           if(multiple % n1 == 0 && multiple % n2 == 0){
               return multiple;
           }
           i++;
        } while (true);

    }

}
