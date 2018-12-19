package day05;

class Day05 {


    static int getRemainingUnitsLength(String input) {
        char[] currentUnits = input.toCharArray();
        StringBuilder sb;
        char c,n;
        int currentLength;
        for (;;) {
            sb = new StringBuilder();
            currentLength=currentUnits.length;
            for (int i = 0; i<currentLength; i++) {
                c=currentUnits[i];
                if (i<currentLength-1) {
                    n=currentUnits[i+1];
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
                currentUnits= sb.toString().toCharArray();
            }
        }
    }
    static int getBestReducedPolymerLength(String input) {
        int bestSoFar=Integer.MAX_VALUE;
        StringBuilder sb;
        for (char upperCase='A'; upperCase<='Z'; upperCase++) { // A -> Z
            char lowerCase=(char)(upperCase+32);
            sb = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (c != upperCase && c!= lowerCase)
                    sb.append(c);
            }
            bestSoFar = Math.min(bestSoFar, getRemainingUnitsLength(sb.toString()));
        }
        return bestSoFar;
    }
}
