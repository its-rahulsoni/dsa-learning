package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

public class MaximumSubarraySum {

    public static void main(String[] args) {

        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        int current_sum = 0;
        int max_sum = 0;
        int start = -1;
        int end = -1;

        for(int i=0;i<array.length;i++){

            if(current_sum == 0){
                start = i;
            }

            current_sum += array[i];

            if(current_sum < 0){
                current_sum = 0;
            } else {
                if(current_sum > max_sum){
                    max_sum = current_sum;
                    end = i;
                }
            }
        }

        System.out.println("start: " + start + " end: " + end + " max_sum: " + max_sum);

    }

}
