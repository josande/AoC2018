package day05;

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

    static int getBestReducedPolymerLength(String input) {
        int bestSoFar=Integer.MAX_VALUE;

        for (char letter ='A'; letter<='Z'; letter++) { // A -> Z
            String toRemoveUpperCase = "" + letter;
            String toRemoveLowerCase = "" + (char)(letter+32);

            String tempString = input.replaceAll(toRemoveUpperCase,"")
                                     .replaceAll(toRemoveLowerCase,"");
            bestSoFar = Math.min(bestSoFar, getRemainingUnitsLength(tempString));
        }
        return bestSoFar;
    }
}
