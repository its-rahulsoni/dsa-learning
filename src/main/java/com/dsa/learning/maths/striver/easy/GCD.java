package com.dsa.learning.maths.striver.easy;

/**
 * THOUGHT PROCESS:
 *
 * Approach #01: The max no that will divide the 2 input numbers, will be always the smaller amongst the 2. So we start from this no till it remains >= 1.
 *
 * Approach #02: We divide the greater no with the smaller no. We replace the greater no with the remainder as this is basically reducing the numbers to 0.
 *               Once one of the number turns to 0, the remaining no will the GCD.
 */
public class GCD {

    public static void main(String[] args) {
        int a = 20;
        int b = 95;
        System.out.println("a: " + a + " & b: " + b + ", GCD: " + findGCDApproach1(a,b));
        System.out.println("a: " + a + " & b: " + b + ", GCD: " + findGCDApproach2(a,b));
    }

    public static int findGCDApproach1(int n1, int n2){
        int min = Math.min(n1, n2);
        int gcdNo = 1;

        for(int i=min; i>1; i--){
            if(n1 % i == 0 && n2 % i == 0){
               gcdNo = i;
               break;
            }
        }

        return gcdNo;
    }


    public static int findGCDApproach2(int n1, int n2){

        while(n1 > 0 || n2 > 0){
            if(n1 > n2){
                n1 = n1 % n2;
            } else {
                n2 = n2 % n1;
            }

            if(n1 == 0){
                return n2;
            } else if(n2 == 0){
                return n1;
            }
        }
        return 1;
    }

}
