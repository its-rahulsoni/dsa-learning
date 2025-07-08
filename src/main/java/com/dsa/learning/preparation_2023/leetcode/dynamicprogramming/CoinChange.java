package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=-NTaXJ7BBXs
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] array = {1,2,5};
        int sum = 9;
        int[] dp = new int[sum+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        coinsCombination(sum, array, dp);
        System.out.println("minNoOfCoins: " + dp[sum]);
    }

    public static int coinsCombination(int sum, int[] coinsArray, int[] dp){

        if(sum == 0){
            return 0;
        }

        if(dp[sum] != Integer.MAX_VALUE) {
            return dp[sum];
        } else {
            for(int i=0;i<coinsArray.length;i++){
                int remainingSum = sum - coinsArray[i];
                if(remainingSum >= 0){
                    int minCoins = 1 + coinsCombination(sum - coinsArray[i], coinsArray, dp);

                    if(minCoins < dp[sum]){
                        dp[sum] = minCoins;
                    }
                }
            }
        }
        return dp[sum];
    }
}
