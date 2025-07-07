package com.dsa.learning.maths.striver.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfectNumber {

    public static void main(String[] args) {
        int number = 6;
        int sum = 0;

        List<Integer> divisorsList = calculateDivisors(number);

        for(int i: divisorsList){
            sum = sum + i;
        }

        if(sum == number){
            System.out.println("PERFECT NUMBER!");
        } else {
            System.out.println("NOT a PERFECT NUMBER!");
        }
    }

    public static List<Integer> calculateDivisors(int num){
        List<Integer> divisorsList = new ArrayList<>();
        divisorsList.add(1);

        int squareRoot = (int)Math.sqrt(num);

        for(int i=2; i<= squareRoot; i++){
            if(num % i == 0){
                divisorsList.add(i);
                divisorsList.add(num/i);
            }
        }

        // Now to sort the results, we can SORT the list using Java's in-built function.
        Collections.sort(divisorsList);

        return divisorsList;
    }

}
