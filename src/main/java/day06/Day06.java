package day06;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Day06 {
    static class Coordinate {
        int x, y;
        int area=0;
        boolean isNextToWall =false;
        Coordinate(int x, int y) {
            this.x=x;
            this.y=y;
        }
        int getX() {return x;}
        int getY() {return y;}
        void addToArea(int i) {
            area+=i;
        }
        void setIsNextToWall(boolean state) {
            isNextToWall =state;
        }
        boolean isNextToWall() {return isNextToWall;}
        int getArea() {return area;}

        int getDistance(int x, int y) {
            return Math.abs(getX()-x) + Math.abs(getY()-y);
        }
    }

    static ArrayList<Coordinate> splitInput(String input) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        String[] rows = input.split("\n");
        for (String row : rows) {
            int x = Integer.valueOf(row.split(", ")[0]);
            int y = Integer.valueOf(row.split(", ")[1]);
            coordinates.add(new Coordinate(x,y));
        }
        return coordinates;
    }
    static int setUpRegions(int x, int y, List<Coordinate> coordinates) {
        int closestDistance = 999;
        int closestPoint = -1;

        for (int i=0; i<coordinates.size(); i++) {
            int distance = coordinates.get(i).getDistance(x,y);
            if (distance<closestDistance) {
                closestPoint=i;
                closestDistance=distance;

            } else if (distance == closestDistance){
                closestPoint=-1;
                closestDistance=distance;
            }
        }
        if (closestPoint>=0) {
            coordinates.get(closestPoint).addToArea(1);
            if (x == 0 || x == 399 || y == 0 || y == 399) {
                coordinates.get(closestPoint).setIsNextToWall(true);
            }
        }
        return closestPoint;

    }
    static int totalDistance(int x, int y, List<Coordinate> coordinates) {
        int distance=0;
        for (Coordinate c : coordinates) {
            distance += c.getDistance(x,y);
        }
        return distance;
    }

    static int largestFiniteArea(String input) {
        ArrayList<Coordinate> locations = splitInput(input);
        int size=400;
        for (int x=0;x<size; x++) {
            for (int y = 0; y < size; y++) {
                setUpRegions(x, y, locations);
            }
        }
        return locations.stream()
                .filter(c -> !c.isNextToWall())
                .mapToInt(Coordinate::getArea)
                .max()
                .orElseThrow(NoSuchElementException::new);

    }

    static int regionWithTotalDistanceLessThan(String input, int distanceLimit) {
        ArrayList<Coordinate> locations = splitInput(input);
        int regionSize=0;
        int size=400;

        for (int x=0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (totalDistance(x, y, locations) < distanceLimit) {
                    regionSize++;
                }
            }
        }
        return regionSize;
    }

}
