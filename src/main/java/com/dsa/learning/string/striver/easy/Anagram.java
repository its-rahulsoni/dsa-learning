package com.dsa.learning.string.striver.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * THOUGHT PROCESS:
 *
 * Approach #01: We use HashMaps as we need to store the count of each character in the strings. The idea of Anagram is that
 *               the characters remain the same along with their Count. Its just that they are re-arranged.
 *
 * Approach #02: We convert the Strings into Character Arrays and use Sort function to sort them.
 *               We then create a string out of this Sorted array and check whether they are equal or not.
 */
public class Anagram {
    public static void main(String[] args) {
        String s1 = "anagran";
        String s2 = "nagaran";

        //checkForAnagramApproach1(s1,s2);
        checkForAnagramApproach2(s1,s2);
    }

    public static void checkForAnagramApproach1(String s1, String s2){

        if(s1.length() != s2.length()){
            System.out.println("NOT ANAGRAM as lengths of strings are different.");
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for(char ch: s1.toCharArray()){
            if(map1.containsKey(ch)){
                map1.put(ch, map1.get(ch) + 1);
            } else {
                map1.put(ch, 1);
            }
        }

        for(char ch: s2.toCharArray()){
            if(map2.containsKey(ch)){
                map2.put(ch, map2.get(ch) + 1);
            } else {
                map2.put(ch, 1);
            }
        }

        for(Map.Entry<Character, Integer> k : map1.entrySet()){
            if(k.getValue() != map2.get(k.getKey())){
                System.out.println("NOT ANAGRAM!, for character: " + k.getKey());
                return;
            }
        }
        System.out.println("Congrats, ANAGRAM!");
    }

    public static void checkForAnagramApproach2(String s1, String s2){
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        System.out.println("Sorted string, s1: " + Arrays.toString(ch1) + " ch2: " + Arrays.toString(ch2));
        if(String.valueOf(ch1).equals(String.valueOf(ch2))){
            System.out.println("Congrats, ANAGRAM!");
        } else {
            System.out.println("NOT ANAGRAM!");
        }
    }
}
