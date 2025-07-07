package com.dsa.learning.maths.striver.easy;

public class CountOfPrimeNumbersTillN {

    public static void main(String[] args) {
        int num = 10;

        for(int i=2; i<num; i++){
            if(PrimeNumber.checkForPrime(i)){
                System.out.println("Prime Number: " + i);
            }
        }
    }
}
