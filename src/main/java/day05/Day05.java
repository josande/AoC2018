package day05;

class Day05 {


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
        int bestSoFar=10000;

        for (char letter ='A'; letter<='Z';letter++) { // A -> Z
            String toRemoveUpperCase = "" + letter;
            String toRemoveLowerCase = "" + (char)(letter+32);

            String tempString = input.replaceAll(toRemoveUpperCase,"").replaceAll(toRemoveLowerCase,"");
            bestSoFar = Math.min(bestSoFar, getRemainingUnitsLength(tempString));
        }
        return bestSoFar;
    }
}
