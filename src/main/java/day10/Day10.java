package day10;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

class Day10 {
    static String solveA(String input) {
        String[] rows = splitInput(input);

        Pair<List<Pair<Integer, Integer>>,List<Pair<Integer, Integer>>> starPositionMovement = getStarsPositionAndMovement(rows);

        int relevantTime= findMin(starPositionMovement.getKey(), starPositionMovement.getValue());
        print(starPositionMovement.getKey(), starPositionMovement.getValue(), relevantTime);
        return "";
    }

    private static int findMin(List<Pair<Integer, Integer>> coordinates, List<Pair<Integer, Integer>>  movements) {
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

    private static void print(List<Pair<Integer, Integer>> coordinates, List<Pair<Integer, Integer>>  movements, int time) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        for (int star = 0; star < coordinates.size(); star++) {
            minX = Math.min(minX, coordinates.get(star).getKey() + movements.get(star).getKey() * time);
            minY = Math.min(minY, coordinates.get(star).getValue() + movements.get(star).getValue() * time);
            maxX = Math.max(maxX, coordinates.get(star).getKey() + movements.get(star).getKey() * time);
            maxY = Math.max(maxY, coordinates.get(star).getValue() + movements.get(star).getValue() * time);
        }
        Character[][] plot = new Character[maxX-minX+2][maxY-minY+2];

        for (int x = 0; x<=maxX-minX+1; x++) {
            for (int y = 0; y <= maxY-minY+1; y++) {
                plot[x][y] = ' ';
            }
        }
        for (int star = 0; star < coordinates.size(); star++) {
            int x = coordinates.get(star).getKey() + movements.get(star).getKey() * time;
            int y = coordinates.get(star).getValue() + movements.get(star).getValue() * time;
            plot[x-minX][y-minY] = '#';
        }
            for (int y = 0; y <= maxY-minY; y++) {
                for (int x = 0; x<=maxX-minX; x++) {
                    System.out.print(plot[x][y]);
                }

            System.out.print("\n");
        }
    }

     static int solveB(String input) {
         String[] rows = splitInput(input);
         Pair<List<Pair<Integer, Integer>>,List<Pair<Integer, Integer>>> starPositionMovement = getStarsPositionAndMovement(rows);
         return findMin(starPositionMovement.getKey(), starPositionMovement.getValue());
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
