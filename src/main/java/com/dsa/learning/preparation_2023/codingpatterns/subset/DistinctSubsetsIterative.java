package com.dsa.learning.preparation_2023.codingpatterns.subset;

import java.util.ArrayList;
import java.util.List;

public class DistinctSubsetsIterative {

    public static void main(String[] args) {
        int[] numbersArray = {1,5,3};

        List<List<Integer>> subsetsList = findSubsets(numbersArray);

        System.out.println("Subsets: " + subsetsList.toString());
    }

    public static List<List<Integer>> findSubsets(int[] numbersArray){
        List<List<Integer>> finalSubsetsList = new ArrayList<>();
        finalSubsetsList.add(new ArrayList<>());

        for(int number : numbersArray){
            int totalNumberOfSubsetsInFinalList = finalSubsetsList.size();

            for(int j=0; j<totalNumberOfSubsetsInFinalList; j++){
                List<Integer> newSubsetsList = new ArrayList<>(finalSubsetsList.get(j)); // Fetching one of the previous subset into a new subset list ....
                newSubsetsList.add(number); // Adding this number to the previously created subset ....
                finalSubsetsList.add(newSubsetsList); // Adding this new subset (previous subset fetched + added new number) to the final subset list ....
            }
        }

        return finalSubsetsList;
    }
}
