package com.dsa.learning.string.dial_epam.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s of lowercase English letters, the task is to find the first non-repeating character. If there is no such character, return '$'.
 *
 * Examples:
 *
 * Input: s = "geeksforgeeks"
 * Output: 'f'
 * Explanation: 'f' is the first character in the string which does not repeat.
 *
 * Input: s = "racecar"
 * Output: 'e'
 * Explanation: 'e' is the only character in the string which does not repeat.
 *
 * Input: "aabbccc"
 * Output: '$'
 * Explanation: All the characters in the given string are repeating.
 */
public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        approach1UsingHashMapAnd2Iterations();
    }

    /**
     * THOUGHT PROCESS:
     * We iterate the string for each character and store the frequency of each character in the map.
     * In next iteration of the string (char array), we check for the first character whose value is 1 in map. This is our output.
     */
    public static void approach1UsingHashMapAnd2Iterations(){
        Map<Character, Integer> map = new HashMap<>();

        String str = "geeksforgeeks";
        char output = '$';

        for(char ch : str.toCharArray()){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for(char ch : str.toCharArray()){
            if(map.get(ch) == 1){
                output = ch;
                break;
            }
        }

        System.out.println("First non-repeating character is: " + output);
    }

}
