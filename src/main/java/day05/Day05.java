package day05;

import java.util.ArrayList;

class Day05 {


    static int getRemainingUnitsLength(String input) {
        String currentUnits = input;
        StringBuilder sb;
        char c,n;
        int currentLength;
        while (true) {
            sb = new StringBuilder();
            currentLength=currentUnits.length();
            for (int i = 0; i<currentLength; i++) {
                c=currentUnits.charAt(i);
                if (i<currentLength-1) {
                    n=currentUnits.charAt(i+1);
                    if (c==n+32 || c==n-32) {
                        i++;
                    } else {
                        sb.append(c);
                    }
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() == currentLength) {
                return sb.length();
            } else {
                currentUnits=sb.toString();
            }
        }
    }

    private static ArrayList<String> getAllCombination() {
        ArrayList<String> allCominations = new ArrayList<>();
        for (Character c1='A';c1<='Z'; c1++) {
            char c2 = (char)(c1+32);
            allCominations.add(c1+""+c2);
            allCominations.add(c2+""+c1);
        }
        return allCominations;
    }

    static int getBestReducedPolymerLength(String input) {
        int bestSoFar=Integer.MAX_VALUE;

        for (char letter ='A'; letter<='Z'; letter++) { // A -> Z
            String toRemoveUpperCase = "" + letter;
            String toRemoveLowerCase = "" + (char)(letter+32);

            String tempString = input.replaceAll(toRemoveUpperCase,"").replaceAll(toRemoveLowerCase,"");
            bestSoFar = Math.min(bestSoFar, getRemainingUnitsLength(tempString));
        }
        return bestSoFar;
    }
}
