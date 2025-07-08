package com.dsa.learning.preparation_2023.codingpatterns.subset;

import java.util.ArrayList;
import java.util.List;

public class DistinctSubsetsRecursive {

    public static void main(String[] args) {
        int[] numbersArray = {1,5,3};

        List<List<Integer>> subsetsList = findSubsets(numbersArray);

        System.out.println("Subsets: " + subsetsList.toString());
    }

    public static List<List<Integer>> findSubsets(int[] numbersArray){
        List<List<Integer>> finalSubsetsList = new ArrayList<>();
        generateSubsets(0, numbersArray, new ArrayList<>(), finalSubsetsList);
        return finalSubsetsList;
    }

    public static void generateSubsets(int index, int[] numbersArray, List<Integer> currentSubsetArray, List<List<Integer>> finalSubsetsList ){
        finalSubsetsList.add(new ArrayList(currentSubsetArray));

        for(int i= index; i < numbersArray.length; i++){
            currentSubsetArray.add(numbersArray[i]);
            generateSubsets(i+1, numbersArray, currentSubsetArray, finalSubsetsList);
            currentSubsetArray.remove(currentSubsetArray.size() - 1);
        }
    }
}
