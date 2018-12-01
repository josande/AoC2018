package day01;

import java.util.ArrayList;

public class Day01 {

    static int getFrequency(String input) {
        int startFrequency = 0;
        int currentFrequency = startFrequency;
        ArrayList<Integer> changingFrequencies = getFrequenciesFromString(input);
        for(int frequencyInt : changingFrequencies) {
            currentFrequency = currentFrequency + frequencyInt;
        }
        return currentFrequency;
    }

    static ArrayList<Integer> getFrequenciesFromString(String input) {
         String[] frequencyArray = input.split(", ");
        ArrayList<Integer> frequencies =  new ArrayList<>();
        for (String frequencyString : frequencyArray) {
            int frequencyInt = Integer.valueOf(frequencyString);
            frequencies.add(frequencyInt);
        }
        return frequencies;
    }

}
