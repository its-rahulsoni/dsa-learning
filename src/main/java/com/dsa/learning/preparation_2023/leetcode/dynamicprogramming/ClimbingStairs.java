package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

import java.util.Arrays;

public class ClimbingStairs {

    public static void main(String[] args) {

        int noOfStairs = 5;
        int[] dp = new int[noOfStairs + 1];

        Arrays.fill(dp, -1);

        int totalNoOfPaths = calculatePaths(0, noOfStairs, dp);

        System.out.println("totalNoOfPaths: " + totalNoOfPaths);
    }

    public static int calculatePaths(int currentStair, int totalStairs, int[] dp){

        if(currentStair > totalStairs){
            return 0;
        }

        if(currentStair == totalStairs){
            return 1;
        }

        if(dp[currentStair] != -1){
            return  dp[currentStair];
        }

        dp[currentStair] = calculatePaths(currentStair + 1, totalStairs, dp) + calculatePaths(currentStair + 2, totalStairs, dp);

        return dp[currentStair];
    }

}
