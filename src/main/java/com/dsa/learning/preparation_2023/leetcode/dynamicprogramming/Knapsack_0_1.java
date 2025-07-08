package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

public class Knapsack_0_1 {

    public static void main(String[] args) {

        int[] itemsWeightArray = {2,3,4,5};
        int[] itemsPriceArray = {1,2,5,6};
        int sackWeight = 8;

        int[][] dp = new int[itemsWeightArray.length+1][sackWeight+1];


        for(int i=0; i<=itemsWeightArray.length; i++){
            for(int w=0;w<=sackWeight;w++){
                if(i == 0 || w == 0){
                    dp[i][w] = 0;
                } else if(itemsWeightArray[i-1] <= w) {
                    dp[i][w] = maxValue(dp[i-1][w] , ((dp[i-1][w - itemsWeightArray[i-1]]) + itemsPriceArray[i-1]));
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println("Final result: " + dp[itemsWeightArray.length][sackWeight]);
    }

    private static int maxValue(int value1, int value2){
        return (value1 > value2) ? value1 : value2;
    }


}
