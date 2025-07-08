package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

import java.util.Arrays;

public class GridUniquePaths {

    public static void main(String[] args) {

    int i = 0 ,j = 0;
    int m = 2;
    int n = 3;
    int[][] dp = new int[m][n];

    for(int[] row : dp){
        Arrays.fill(row, -1);
    }

    uniquePathsUsingRecursion(i, j, m, n, dp);

    System.out.println("Total unique paths using recursion: " + dp[0][0]); // The method recursively calculates the total paths from origin ....

    int result = uniquePathsUsingTabulation(m, n);
    System.out.println("Total unique paths using tabulation: " + result);
    }



    public static int uniquePathsUsingRecursion(int i, int j, int m, int n, int[][] dp){

        if(i == m-1 && j == n-1){
            return 1;
        } else if(i >= m || j >= n){
            return 0;
        } else if(dp[i][j] != -1){
            return dp[i][j];
        } else {
            dp[i][j] = uniquePathsUsingRecursion(i+1, j, m, n, dp) + uniquePathsUsingRecursion(i, j+1, m, n, dp);
        }

        return dp[i][j];
    }


    public static int uniquePathsUsingTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        // We initialize the first row and the first column with 1 because there is only one way to reach each cell in the first row and the first column (by moving only right or down).
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;

        for (int j = 0; j < n; j++)
            dp[0][j] = 1;

        // Build the dynamic programming table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

}
