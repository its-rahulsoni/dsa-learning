package com.dsa.learning.string.dial_epam.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Find All Substrings of a Given String
 * A substring of a string is any sequence of characters within the string that occurs contiguously. For example:
 *
 * String: "abc"
 * Substrings: "a", "b", "c", "ab", "bc", "abc".
 * The problem is to generate all substrings of a given string programmatically.
 *
 * Time Complexity:
 * If the task explicitly requires you to generate every possible substring, it's mathematically impossible to do
 * this in less than O(n^2), because:
 * The number of substrings for a string of length n is: n * (n + 1) / 2 , which is quadratic.
 */
public class SubStringsFromnAGivenString {

    public static void main(String[] args) {

        String input = "rahul";
        List<String> list = new ArrayList<>();

        for(int i=0; i< input.length(); i++){
            for(int j=i+1; j<input.length()+1; j++){
                list.add(input.substring(i,j));
            }
        }
        System.out.println("List: " + list);
    }

}
