package day01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Day01 {

    static int solveA(String input, String splitOn) {
        int currentFrequency = 0;
        ArrayList<Integer> changingFrequencies = getFrequenciesFromString(input, splitOn);
        for(int frequencyInt : changingFrequencies) {
            currentFrequency = currentFrequency + frequencyInt;
        }
        return currentFrequency;
    }

    static int solveB(String input, String splitOn) {
        int currentFrequency = 0;
        Set<Integer> visitedFrequencies = new HashSet<>();
        visitedFrequencies.add(currentFrequency);
        ArrayList<Integer> changingFrequencies = getFrequenciesFromString(input, splitOn);

        while(true) {
            for(int frequencyInt : changingFrequencies) {
                currentFrequency += frequencyInt;
                // check if the value is in list already
                if (visitedFrequencies.contains(currentFrequency)) {
                    // stop & return the currentFrequency
                    return currentFrequency;
                } else {
                    visitedFrequencies.add(currentFrequency);
                }
            }
        }
    }

    static ArrayList<Integer> getFrequenciesFromString(String input, String splitOn) {
        String[] frequencyArray = input.split(splitOn);
        ArrayList<Integer> frequencies =  new ArrayList<>();
        for (String frequencyString : frequencyArray) {
            int frequencyInt = Integer.valueOf(frequencyString);
            frequencies.add(frequencyInt);
        }
        return frequencies;
    }

}
