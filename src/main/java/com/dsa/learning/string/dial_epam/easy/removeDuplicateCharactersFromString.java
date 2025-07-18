package com.dsa.learning.string.dial_epam.easy;

import java.util.*;

public class removeDuplicateCharactersFromString {

    public static void main(String[] args) {

        approach1();

        approach2();

        approach3();

        approach4();
    }

    /**
     * THOUGHT PROCESS:
     * The idea is to iterate over string and store the count of each character in a LInkedHashMap (to maintain the character occurences).
     * Then we iterate over this map and append each character to the StringBuilder.
     * The main idea here is that the map's key will be unique with value storing frequency for each character, so we can create a unique char string
     * just by iterating over keys of this map and ignoring the count.
     */
    public static void approach1(){
        System.out.println("**** approach1() - Using a LinkedHashMap ****");
        String input = "programming";
        String output = "";

        Map<Character, Integer> map = new LinkedHashMap<>(); // LinkedHashMap: To preserve order of characters ....

        for(char ch : input.toCharArray()){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> m : map.entrySet()){
            sb.append(m.getKey());
        }
        output = sb.toString();

        System.out.println("String with unique characters: " + output);

        addDivider();
    }

    /**
     * THOUGHT PROCESS:
     * The Set stores unique element only, so we can add characters to a Set and the duplicate characters will be ignored automatically.
     * LinkedHashSet can be used to preserve order of the characters.
     */
    public static void approach2(){
        System.out.println("**** approach2() - Using a LinkedHashSet ****");
        String input = "programming";

        Set<Character> set = new LinkedHashSet<>();

        for(char ch : input.toCharArray()){
            set.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for(char ch : set){
            sb.append(ch);
        }

        String output = sb.toString();
        System.out.println("String with unique characters: " + output);

        addDivider();
    }


    /**
     * THOUGHT PROCESS:
     * We're using only a StringBuilder here.
     * The idea is to check the index of each character in the output of StringBuilder.
     * If any char is present at any index in SB, then it means that its a duplicate character and we can skip it. And otherwise, add this character to the SB.
     */
    public static void approach3(){
        System.out.println("**** approach3() - Using only StringBuilder ****");
        String input = "programming";

        StringBuilder sb = new StringBuilder();
        for(char ch : input.toCharArray()){
            // Add character only if it hasn't been added before ....
            if(sb.indexOf(String.valueOf(ch)) == -1 ){
                sb.append(ch);
            }
        }

        String output = sb.toString();
        System.out.println("String with unique characters: " + output);

        addDivider();
    }

    /**
     * THOUGHT PROCESS:
     * We're using a boolean array of size 256.
     * The idea is to update index of array with status true for each character encountered only if the index value is false.
     * Note: We can use character to refer to the Index of an array. That's the main idea behind this approach.
     */
    public static void approach4(){
        System.out.println("**** approach4() - Using an boolean array of 256 size where each index maps to a character ****");

        String input = "programming";

        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder();

        for(char ch : input.toCharArray()){
            if(seen[ch] != true){
                sb.append(ch);
                seen[ch] = true;
            }
        }

        String output = sb.toString();
        System.out.println("String with unique characters: " + output);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
