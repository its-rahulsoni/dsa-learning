package com.dsa.learning.string.dial_epam.easy;

/**
 * THOUGHT PROCESS:
 *
 * We can create an Array of size 256:
 * You create int[] freq = new int[256]; or boolean[] seen = new boolean[256];
 * Each index represents a character (like 'a' = 97, 'A' = 65, ' ' = 32)
 *
 * So you can directly do:
 * freq[someChar]++;
 *
 * Mapping Characters to Array Indices:
 * Each character in the ASCII table has a unique integer value (ASCII code).
 * For example:
 * 'A' → ASCII value 65
 * 'a' → ASCII value 97
 * '0' → ASCII value 48
 * ' ' (space) → ASCII value 32
 * These ASCII values can be directly used as indices in the integer array -
 * (e.g., ch['A'] corresponds to the index 65).
 */
public class CheckIfStringIsUnique {

    public static void main(String[] args) {
        String input = "rahul";

        System.out.println("Result: " + isUnique(input));
    }

    public static boolean isUnique(String str) {
        if (str.length() > 256) return false; // Pigeonhole principle

        boolean[] seen = new boolean[256];

        for(char ch : str.toCharArray()){
            if(seen[ch] == true){
                return false;
            } else {
                seen[ch] = true;
            }
        }
        return true;
    }

}
