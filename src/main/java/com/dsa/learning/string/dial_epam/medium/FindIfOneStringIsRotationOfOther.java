package com.dsa.learning.string.dial_epam.medium;

import java.util.ArrayList;
import java.util.List;

public class FindIfOneStringIsRotationOfOther {

    public static void main(String[] args) {
        approach1();

        approach2();

        approach3();

        approachUsingKMPAlgorithm();
    }

    /**
     * THOUGHT PROCESS:
     * The idea is to generate all possible rotations of the first string and check if any of these rotations match the second string.
     * If any rotation matches, the two strings are rotations of each other, otherwise they are not.
     *
     * Time Complexity:
     * Total Time Complexity: O(n) * O(n) -> O(n^2)
     */
    public static void approach1(){
        System.out.println("**** approach1() ****");

        String str1 = "abcd";
        String str2 = "cdab";
        int length = str1.length();

        List<String> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for(int i=1; i< str1.length(); i++){
            sb.append(str1.substring(i, length));
            sb.append(str1.substring(0, i));
            list.add(sb.toString());
            sb.setLength(0); // To clear content inside String Builder ....
        }
        System.out.println("List: " + list);

        if(list.contains(str2)){
            System.out.println("The string: " + str2 + ", is a rotation of string: " + str1);
        } else{
            System.out.println("The string: " + str2 + ", is NOT a rotation of string: " + str1);
        }

        addDivider();
    }

    /**
     * THOUGHT PROCESS:
     * The idea is to generate all possible rotations of the first string and check if any of these rotations match the second string.
     * If any rotation matches, the two strings are rotations of each other, otherwise they are not.
     *
     * Time Complexity:
     * 1. The loop runs n - 1 times and performs: (str1.substring(i, length) + str1.substring(0, i))
     * Each of the iteration creates two substrings of size ~n and concatenates them: O(n)
     * So, each iteration = O(n)
     * Total time for this loop = (n - 1) × O(n) = O(n²)
     *
     * 2. list.contains(str2)
     * This is a linear search over the list with up to n - 1 elements.
     * Each equals() call compares strings of length n → O(n)
     *
     * Internally, ArrayList uses the indexOf(object) method to check if the object is in the list.
     * The indexOf(object) method iterates the entire array and compares each element with the equals(object) method.
     *
     * The ArrayList.contains() method requires O(n) time. So the time we spend to find a specific object here
     * depends on the number of items we have in the array.
     */
    public static void approach2(){
        System.out.println("**** approach2() ****");

        String str1 = "abcd";
        String str2 = "cdab";
        int length = str1.length();

        List<String> list = new ArrayList<>();

       String temp = "";

        for(int i=1; i< str1.length(); i++){
           temp = str1.substring(i, length) + str1.substring(0, i);
            list.add(temp);
        }
        System.out.println("List: " + list);

        if(list.contains(str2)){
            System.out.println("The string: " + str2 + ", is a rotation of string: " + str1);
        } else{
            System.out.println("The string: " + str2 + ", is NOT a rotation of string: " + str1);
        }

        addDivider();
    }

    /**
     * THOUGHT PROCESS:
     * If s1 and s2 are rotations of each other, then s2 must be a substring of s1 + s1.
     */
    public static void approach3(){
        System.out.println("**** approach3() ****");

        String str1 = "abcd";
        String str2 = "cdab";

        String concatedString = str1 + str1;

        if(concatedString.contains(str2)){
            System.out.println("The string: " + str2 + ", is a rotation of string: " + str1);
        } else {
            System.out.println("The string: " + str2 + ", is NOT a rotation of string: " + str1);
        }

        addDivider();
    }


    /**
     * Need to spend time practising it.
     * The details of this Algorithm are present in a separate KMPAlgoForPatternMatch.md file.
     */
    public static void approachUsingKMPAlgorithm(){
        System.out.println("**** approachUsingKMPAlgorithm() ****");

        String text = "abcxabcdabcdabcy";
        String pattern = "abcdabcy";

        int[] lps = prepareLPSArray(pattern);

        int n = text.length();
        int m = pattern.length();

        boolean patternFound = false;

        // Step 2: Perform KMP search
        int i = 0, j = 0; // i = index in text, j = index in pattern
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                // Pattern found
                patternFound = true;
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if(patternFound)
            System.out.println("The string: " + text + ", is a rotation of string: " + pattern);
        else
            System.out.println("The string: " + text + ", is NOT a rotation of string: " + pattern);

        addDivider();
    }

    public static int[] prepareLPSArray(String pattern){
        int m = pattern.length();
        int i = 1;
        int len = 0; // Length of the previous longest prefix suffix

        int[] lps = new int[m];

        // lps[0] is always 0
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Move to previous prefix match
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }


}
