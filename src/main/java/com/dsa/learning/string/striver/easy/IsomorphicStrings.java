package com.dsa.learning.string.striver.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ISOMORPHIC: Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * THOUGHT PROCESS:
 *
 * Approach #01: Whenever we have to think about COUNT of characters in a string, we must use HASHMAP to store that count.
 *               Here, we store count of each character of both strings in 2 separate HashMaps. Then, we compare for the values of each character from first map in second map.
 *               The idea is that the count pattern of each character in first map must match the count pattern in second map.
 *               Ex: If map1 has 3 elements with count of 1,2,2 then in map2 we must also have 3 characters with their count pattern similar like 2,1,2 (order doesn't matter).
 *               We use an ArrayList to store the already mapped characters from Map2 while checking the count mapping.
 *               NOTE: This solution is O(n^2) complexity, so its not ideal.
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        String s1 = "papee";
        String s2 = "title";

       // isIsomorphicApproach1(s1,s2);
        isIsomorphicApproach2(s1,s2);
    }

    // This actually is NOT correct ISOMORPHIC STRING LOGIC. Refer to isomorphic.md file for details.
    public static void isIsomorphicApproach1(String s1, String s2){
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        for(char ch : arr1){
            if(map1.containsKey(ch)){
                map1.put(ch, map1.get(ch) + 1);
            } else {
                map1.put(ch, 1);
            }
        }

        for(char ch : arr2){
            if(map2.containsKey(ch)){
                map2.put(ch, map2.get(ch) + 1);
            } else {
                map2.put(ch, 1);
            }
        }

        List<Character> visitedChar = new ArrayList<>();
        boolean flag = false;
        for(Map.Entry<Character, Integer> k1 : map1.entrySet()){
            flag = false;
            for(Map.Entry<Character, Integer> k2 : map2.entrySet()){
                if((k2.getValue() == k1.getValue()) && !visitedChar.contains(k2.getKey())){
                    visitedChar.add((char)k2.getKey());
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println("NOT ISOMORPHIC!");
                break;
            }
        }

        if(flag){
            System.out.println("ISOMORPHIC");
        }
    }

    public static void isIsomorphicApproach2(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        if(s1.length() != s2.length()){
            System.out.println("NOT ISOMORPHIC, different string lengths!");
            return;
        }

        for(int i=0;i<s1.length();i++){
            if((map1.containsKey(c1[i]) && !map2.containsKey(c2[i])) ||
                    (!map1.containsKey(c1[i]) && map2.containsKey(c2[i]))){
                System.out.println("NOT ISOMORPHIC! Previous Occurrence of character for c1: " + c1[i] +
                        " & c2: " + c2[i] + " is present in one string while absent in another.");
                return;
            }
            if(map1.containsKey(c1[i]) && map2.containsKey(c2[i])){
                if(map1.get(c1[i]) != map2.get(c2[i])){
                    System.out.println("NOT ISOMORPHIC! Occurrence is at different index.");
                    return;
                } else {
                    map1.put(c1[i], i);
                    map2.put(c2[i], i);
                }
            }
            if(!map1.containsKey(c1[i])){
                map1.put(c1[i], i);
            }

            if(!map2.containsKey(c2[i])){
                map2.put(c2[i], i);
            }
        }

        System.out.println("CONGRATS! ISOMORPHIC Strings :-)");
    }

}
