package day03;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Day03 {


    //This solves A
    static int solveA(String input) {

        //string into an array
        String[] inputRows = input.replaceAll(":","")
                                  .replaceAll("#", "")
                                  .split("\r?\n");

        //Create a 2D array with integers
        HashMap<Pair<Integer, Integer>, Integer> fabric = new HashMap<>();

        //check how many inches are covered by X
        return findOverlap(fabric, inputRows);
    }
    private static int findOverlap(HashMap<Pair<Integer, Integer>, Integer> fabric, String[] inputRows) {
        int overlap = 0;
        for(String row : inputRows) {
            String[] rowParts = row.split(" ");

            //split: starting position left, starting position top, total covered
            String[] startPositions = rowParts[2].split(",");
            int startLeft = Integer.valueOf(startPositions[0]);
            int startTop = Integer.valueOf(startPositions[1]);


            int width = Integer.valueOf(rowParts[3].split("x")[0]);
            int height = Integer.valueOf(rowParts[3].split("x")[1]);


            //SetFabric() set the id to covered area, if covered already, set -1
            for (int xCoordinate = startLeft; xCoordinate < startLeft + width; xCoordinate++) {
                for (int yCoordinate = startTop; yCoordinate < startTop + height; yCoordinate++) {
                    Pair<Integer, Integer> p = new Pair<>(xCoordinate, yCoordinate);
                    if (!fabric.containsKey(p)) {
                        fabric.put(p,1);
                    } else if (fabric.get(p) > 0) {
                        fabric.put(p,-1);
                        overlap++;
                    }
                }
            }
        }
        return overlap;
    }
//    /Part Two
static int solveB(String input) {
    //string into an array
    String[] inputRows = input.replaceAll(":","")
                              .replaceAll("#", "")
                              .split("\r?\n");

    //Create a 2D array with integers
    HashMap<Pair<Integer, Integer>, Integer> fabric = new HashMap<>();

    return findNonOverlappingPattern(fabric, inputRows);
}
    private static int findNonOverlappingPattern(HashMap<Pair<Integer, Integer>, Integer> fabric, String[] inputRows) {
        Set<Integer> nonOverlappingPatterns = new HashSet<>();
        for(String row : inputRows) {
            String[] rowParts = row.split(" ");

            //split: starting position left, starting position top, total covered
            String[] startPositions = rowParts[2].split(",");
            int startLeft = Integer.valueOf(startPositions[0]);
            int startTop = Integer.valueOf(startPositions[1]);


            int width = Integer.valueOf(rowParts[3].split("x")[0]);
            int height = Integer.valueOf(rowParts[3].split("x")[1]);
            int patternID = Integer.valueOf(rowParts[0]);

            nonOverlappingPatterns.add(patternID);

            for (int xCoordinate = startLeft; xCoordinate < startLeft + width; xCoordinate++) {
                for (int yCoordinate = startTop; yCoordinate < startTop + height; yCoordinate++) {
                    Pair<Integer, Integer> p = new Pair<>(xCoordinate, yCoordinate);
                    if (!fabric.containsKey(p)) {
                        fabric.put(p,patternID);
                    } else {
                        nonOverlappingPatterns.remove(fabric.get(p));
                        nonOverlappingPatterns.remove(patternID);
                        fabric.put(p,-1);
                    }
                }
            }
        }
        return (Integer) nonOverlappingPatterns.toArray()[0];
    }
}
