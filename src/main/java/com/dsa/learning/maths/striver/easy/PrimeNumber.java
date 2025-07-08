package com.dsa.learning.maths.striver.easy;

public class PrimeNumber {

    public static void main(String[] args) {

        int number = 1;

        boolean flag = checkForPrime(number);
        if(flag){
            System.out.println("Prime Number");
        } else{
            System.out.println("NOT a Prime Number");
        }

    }

    public static boolean checkForPrime(int number){
        boolean flag = true;

        if(number == 1){
            return false;
        }
        int sqRoot = (int)Math.sqrt(number);
        for(int i=2; i<=sqRoot; i++){
            if(number % i == 0){
                flag = false;
                break;
            }
        }

        return flag;
    }

}
