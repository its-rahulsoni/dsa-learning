package com.dsa.learning.string.dial_epam.easy;

public class CountVowelsAndConsonantsInString {

    public static void main(String[] args) {
        String input = "apple";

        //countVowelsAndConsonantsInAString(input);

        System.out.println("Vowels count: " + countVowelsAndConsonantsInAString(input)[0] +
                " Consonants count: " + countVowelsAndConsonantsInAString(input)[1]);
    }

    public static int[]  countVowelsAndConsonantsInAString(String input){
        int vowelCount = 0;
        int consonantsCount = 0;

        for(char ch : input.toCharArray()){
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                vowelCount++;
            } else {
                consonantsCount++;
            }
        }

        //System.out.println("vowelCount: " + vowelCount + " , consonantsCount: " + consonantsCount);
        return new int[]{vowelCount,consonantsCount};
    }

}
