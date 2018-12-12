package day12;

import javafx.util.Pair;

import java.util.ArrayList;

class Day12 {
    static int solveA(String input) {
       return solver(input, 20L);

    }
    private static int solver(String input, long iterations) {
        String[] inputRows = input.split(("\r?\n"));
        ArrayList<Pair<String, Character>> instructions = new ArrayList<>();
        String currentRow=inputRows[0].split(": ")[1];
        for (int i=2; i<inputRows.length;i++) {
            String s = inputRows[i].split(" => ")[0];
            Character c = inputRows[i].split(" => ")[1].toCharArray()[0];
            instructions.add(new Pair<>(s,c));
        }

        for (long i=0; i<iterations; i++) {
            currentRow="...."+currentRow+"....";
            char[] newChars = currentRow.toCharArray();
            for (int r=2; r<currentRow.length()-2; r++) {
                boolean isChanged=false;
                for (Pair<String, Character> p : instructions) {
                    if (p.getKey().equals(currentRow.substring(r-2, r+3))) {
                        newChars[r] = p.getValue();
                        isChanged=true;
                    }
                }
                if (!isChanged) {
                    newChars[r] = '.';
                }
            }
            currentRow = String.valueOf(newChars);
        }

        int plantValue=0;
        for (int i=0; i<currentRow.length();i++) {
            if (currentRow.charAt(i) == '#') {
                plantValue += (i - 4*iterations);
            }
        }
        return plantValue;
    }

    static Long solveB(String input) {
        int currentValue=0;
        int lastValue=0;
        int lastLastValue;
        for (int i=100; ; i+=10) {
            lastLastValue = lastValue;
            lastValue = currentValue;
            currentValue = solver(input, i);
            if (lastValue - lastLastValue  == currentValue - lastValue) {
                int stepLength=(currentValue-lastValue)/10;
                return currentValue+(50000000000L-i)*stepLength;
            }
        }
    }
}
