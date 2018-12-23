package day10;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

class Day10 {
    static String solveA(String input) {
        String[] rows = splitInput(input);

        Pair<List<Pair<Integer, Integer>>,List<Pair<Integer, Integer>>> starPositionMovement = getStarsPositionAndMovement(rows);
        int time = findTime(starPositionMovement.getKey(), starPositionMovement.getValue());
        return print(starPositionMovement.getKey(), starPositionMovement.getValue(), time);
    }

    private static int findTime(List<Pair<Integer, Integer>> coordinates, List<Pair<Integer, Integer>>  movements) {
        int size=-1;

        for (int time=0; ; time++) {
            int maxY = 0;
            int minY = Integer.MAX_VALUE;

            for (int star = 0; star < coordinates.size(); star++) {
                maxY = Math.max(maxY, coordinates.get(star).getValue() + movements.get(star).getValue() * time);
                minY = Math.min(minY, coordinates.get(star).getValue() + movements.get(star).getValue() * time);
            }

            if ( size < 0 || Math.abs(maxY-minY) < size ) {
                size=Math.abs(maxY-minY);
            } else {
                return time-1;
            }
        }
    }

    private static String print(List<Pair<Integer, Integer>> coordinates, List<Pair<Integer, Integer>>  movements, int time) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        ArrayList<Pair<Integer, Integer>> finalPositions = new ArrayList<>();
        for (int star = 0; star < coordinates.size(); star++) {
            int x= coordinates.get(star).getKey() + movements.get(star).getKey() * time;
            int y=coordinates.get(star).getValue() + movements.get(star).getValue() * time;
            finalPositions.add(new Pair<>(x,y));
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            sb.append("\n");
            for (int x = minX; x<=maxX; x++) {
                sb.append(finalPositions.contains(new Pair<>(x,y))?"#":" ");
            }
        }
        return sb.toString();
    }

     static int solveB(String input) {
         String[] rows = splitInput(input);
         Pair<List<Pair<Integer, Integer>>,List<Pair<Integer, Integer>>> starPositionMovement = getStarsPositionAndMovement(rows);
         return findTime(starPositionMovement.getKey(), starPositionMovement.getValue());
    }
    private static Pair<List<Pair<Integer, Integer>>,List<Pair<Integer, Integer>>> getStarsPositionAndMovement(String[] dataRows) {
        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();

        for (String row: dataRows) {
            String[] values = row.split(" ");
            coordinates.add(new Pair<>(Integer.valueOf(values[0]), Integer.valueOf(values[1])));
            movements.add(new Pair<>(Integer.valueOf(values[2]), Integer.valueOf(values[3])));
        }
        return new Pair<>(coordinates, movements);
    }
    private static String[] splitInput(String input) {
        return input
                .replaceAll("position=< ","")
                .replaceAll("position=<","")
                .replaceAll("velocity=<","")
                .replaceAll(">","")
                .replaceAll(",","")
                .replaceAll(" {2,}"," ")
                .split("\r?\n");
    }
}
