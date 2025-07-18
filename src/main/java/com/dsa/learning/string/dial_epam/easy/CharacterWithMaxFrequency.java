package com.dsa.learning.string.dial_epam.easy;

import java.util.HashMap;
import java.util.Map;

public class CharacterWithMaxFrequency {

    public static void main(String[] args) {

        approach1();

        approach2();
    }

    /**
     * THOUGHT PROCESS:
     * The idea is to store frequency of each character in a map and then iterate the map to find max count of a char.
     * We use 2 var, one for storing max count and another for max character.
     */
    public static void approach1(){
        System.out.println("**** approach1() - Using HashMap to store frequency of each character ****");

        String input = "geeksforgeeks";

        Map<Character, Integer> map = new HashMap<>();

        for(char ch : input.toCharArray()){
           map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int maxCount = 0;
        char maxChar = ' '; // Placeholder (assuming no spaces in input, otherwise use a better sentinel)

        for(Map.Entry<Character, Integer> m : map.entrySet()){
            if(m.getValue() > maxCount){
                maxCount = m.getValue();
                maxChar = m.getKey();
            }
        }

        System.out.println("Max frequency char is: " + maxChar + ", with a count of: " + maxCount);

        addDivider();
    }


    /**
     * THOUGHT PROCESS:
     * The idea is to store frequency of each character in an Array of 256 size (one for each ASCII char) and then iterate the array to find max count of a char.
     * We use 2 var, one for storing max count and another for max character.
     */
    public static void approach2(){
        System.out.println("**** approach2() - Using array of 256 size to store frequency of each character at respective index ****");

        String input = "geeksforgeeks";
        int[] arr = new int[256];

        for(char ch : input.toCharArray()){
           arr[ch] = arr[ch] + 1;
        }

        int maxCount = 0;
        char maxChar = ' '; // Placeholder (assuming no spaces in input, otherwise use a better sentinel)

        for(int i=0; i<arr.length;i++){
            if(arr[i] > maxCount){
                maxCount = arr[i];
                maxChar = (char)i;
            }
        }

        System.out.println("Max frequency char is: " + maxChar + ", with a count of: " + maxCount);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

}
