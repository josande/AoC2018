package day02;


public class Day02 {

    static int getChecksum(String input) {
        String[] boxIDs = input.split("\n");
        int numberOfPairs = 0;
        int numberOfTriples = 0;
        for(String boxID : boxIDs) {
            if(containsCombination(boxID, 2)) {
                numberOfPairs++;
            }
            if(containsCombination(boxID, 3)) {
                numberOfTriples++;
            }
        }



        return numberOfPairs * numberOfTriples;

    }

    static boolean containsCombination(String input, int timesItShouldExist) {
        // single boxID comes in
        // check letter by letter - so we need to split it up
        // count the letters & check if it is only occurring twice

        String remainingString = input;
        while(remainingString.length() > 0) {
            char firstLetter = remainingString.charAt(0);
            int lengthBefore = remainingString.length();

            remainingString = remainingString.replaceAll(""+firstLetter, "");

            int lengthAfter = remainingString.length();

            if (lengthBefore-lengthAfter == timesItShouldExist) {
                return true;
            }
        }
        return false;
    }

}
