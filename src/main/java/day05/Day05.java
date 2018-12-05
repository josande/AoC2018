package day05;

public class Day05 {


    static int getRemainingUnitsLength(String input) {
        String currentUnits = input;
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<currentUnits.length(); i++) {
                if ( (i<currentUnits.length()-1) &&
                    ((currentUnits.charAt(i) == currentUnits.charAt(i+1)+32) ||
                     (currentUnits.charAt(i) == currentUnits.charAt(i+1)-32)) ) {
                    i++;
                } else {
                    sb.append(currentUnits.charAt(i));
                }
            }
            if (sb.length() == currentUnits.length()) {
                return sb.length();
            } else {
                currentUnits=sb.toString();
            }
        }
    }


    static int getBestReducedPolymerLength(String input) {
        int lengthBefore = input.length();
        int bestSoFar=10000;

        for (int i= 65; i<=90;i++) { // A -> Z
            String toRemoveUpperCase = "" + (char) i;
            String toRemoveLowerCase = "" + (char) (i+32); 

            String tempString = input.replaceAll(toRemoveUpperCase,"").replaceAll(toRemoveLowerCase,"");
            if (lengthBefore!=tempString.length()) {
                int currentLength = getRemainingUnitsLength(tempString);
                bestSoFar = Math.min(bestSoFar, currentLength);
            }
        }
        return bestSoFar;
    }
}
