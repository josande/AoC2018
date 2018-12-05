package day05;

public class Day05 {


    static int getRemainingUnitsLength(String input) {
        String currentUnits = input;
        while (true) {
            int length = currentUnits.length();
            String newString = currentUnits;
            for (int i = length-1; i > 0; i--) {
                if ( (currentUnits.charAt(i) == currentUnits.charAt(i-1)+32) ||
                     (currentUnits.charAt(i) == currentUnits.charAt(i-1)-32) ) {
                    newString=newString.substring(0,i-1)+newString.substring(i+1,newString.length());
                    i --;
                }
            }
            currentUnits=newString;
            if (newString.length() == length) {
                return newString.length();
            }

        }
    }


    static int getBestReducedPolymerLength(String input) {
    int bestSoFar=10000;
        for (int i= 65; i<=90;i++) {
            String toRemoveUpperCase = "" + (char) i;
            String toRemoveLowerCase = "" + (char) (i+32);

            String tempString = input.replaceAll(toRemoveUpperCase,"").replaceAll(toRemoveLowerCase,"");
            int currentLength=getRemainingUnitsLength(tempString);
            bestSoFar=Math.min(bestSoFar, currentLength);
        }
        return bestSoFar;
    }


}
