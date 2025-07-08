package com.dsa.learning.preparation_2023.leetcode.dynamicprogramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String s1 = "ABCAB";
        String s2 = "AECB";

        int commonsSequenceLengthUsingRecursion = recursionApproach(s1,s2);
        System.out.println("commonsSequenceLength using Recursion: " + commonsSequenceLengthUsingRecursion);


        int commonsSequenceLengthUsingTabulation = tabulationApproach(s1,s2);
        System.out.println("commonsSequenceLength using Tabulation: " + commonsSequenceLengthUsingTabulation);

    }

    public static int recursionApproach(String s1, String s2){

        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int commonsSequenceLength = commonSubsequenceUsingRecursion(s1.length(), s2.length(), s1, s2, dp);

        return commonsSequenceLength;
    }

    public static int commonSubsequenceUsingRecursion(int indexString1, int indexString2, String s1, String s2, int[][] dp){

        if(indexString1 == 0 || indexString2 == 0){
            return dp[indexString1][indexString2] = 0;
        }

        if(dp[indexString1][indexString2] != -1){
            return dp[indexString1][indexString2];
        }

        if(s1.charAt(indexString1-1) == s2.charAt(indexString2-1)){
            dp[indexString1][indexString2] = 1 + commonSubsequenceUsingRecursion(indexString1-1, indexString2-1,
                    s1,s2,dp);
        } else {
            dp[indexString1][indexString2] = Math.max(commonSubsequenceUsingRecursion(indexString1-1, indexString2,
                    s1,s2,dp),
                    commonSubsequenceUsingRecursion(indexString1, indexString2-1,
                            s1,s2,dp));
        }

        return dp[indexString1][indexString2];
    }


    public static int tabulationApproach(String s1, String s2){

        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int commonsSequenceLength = commonSubsequenceUsingTabulation( s1, s2, dp);

        return commonsSequenceLength;

    }

    public static int commonSubsequenceUsingTabulation(String s1, String s2, int[][] dp){

        for(int indexString1=0;indexString1<=s1.length();indexString1++){
            for(int indexString2=0;indexString2<=s2.length();indexString2++){

                if(indexString1 == 0 || indexString2 == 0){
                    dp[indexString1][indexString2] = 0;
                } else if(s1.charAt(indexString1-1) == s2.charAt(indexString2-1)){
                    // Meaning char at this index from s1 matches char at s2 at same index.
                    // So we add 1 to max subsequence length of characters (prior to this char) of s1 that were compared to this char of s2.
                    dp[indexString1][indexString2] = 1 + dp[indexString1 - 1][indexString2 - 1];
                } else {
                    dp[indexString1][indexString2] = Math.max(dp[indexString1 - 1][indexString2], dp[indexString1][indexString2-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

}
