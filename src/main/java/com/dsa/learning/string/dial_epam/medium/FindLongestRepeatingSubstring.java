package com.dsa.learning.string.dial_epam.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem: Find the Longest Repeating Substring
 * The task is to find the longest substring that occurs more than once in a given string. This substring must appear multiple
 * times in the original string and be as long as possible.
 *
 * Examples:
 *
 * Input: "banana"
 * Output: "ana" (both "ana" repeat).
 * Input: "abcdxyzabcd"
 * Output: "abcd" (repeats twice).
 * Input: "abcdef"
 * Output: "" (no repeating substrings).
 */
public class FindLongestRepeatingSubstring {

    public static void main(String[] args) {
        approach1();
    }

    /**
     * THOUGHT PROCESS:
     * There are 3 things that the question mentions:
     * 01. Sub-Strings: So we need to find out all the sub-strings of the given string.
     * 02. Repeating: The sub-string must be Repeating, i.e. its frequency must be > 1.
     * 03. Longest: The length of this sub-string must be the highest amongst all the sub-strings formed.
     *
     * So, we first figure out all the sub-strings that could be formed of this string and also store them
     * in a Map along with their respective frequency of occurrences.
     *
     * Next we iterate over this Map and Only Consider those sub-strings whose frequency is greater than 1.
     * Then, filter out sub-string (with frequency > 1) whose length is highest amongst all those sub-strings
     * with frequency > 1.
     *
     * Time Complexity: O(n^2)
     */
    public static void approach1(){
        String str = "abcdxyzabcd";

        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String subString;

        for(int i=0;i <str.length(); i++){
            for(int j=i+1; j<=str.length(); j++){
                subString = str.substring(i, j);
                list.add(subString);
                map.put(subString, map.getOrDefault(subString, 0) + 1);
            }
        }
        //System.out.println("sub-strings list: " + list);
        System.out.println("sub-strings map: " + map);

        int maxLength = 0;
        String maxSubString = "";

        for(Map.Entry<String, Integer> m : map.entrySet()){
            /**
             * m.getValue():        Represents the frequency of occurrence of this sub-string.
             * m.getKey().length:   Represents the sub-string's length. Since we want longest sub-string, so we're
             *                      only considering the longest sub-string here.
             */
            if(m.getValue() > 1 && m.getKey().length() > maxLength){
                maxLength = m.getKey().length();
                maxSubString = m.getKey();
            }
        }
        System.out.println("maxLength: " + maxLength + " - maxSubString: " + maxSubString);

    }
}
