package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

class Day06 {
    static class Coordinate {       // Seems we will be doing various things with these points
        private int x, y;
        private int area=0;
        private boolean isNextToWall =false;

        Coordinate(int x, int y) {  // We only need the coordinate initially
            this.x=x;               // this. indicate that we store the x in the x-variable in this class
            this.y=y;
        }

        int getX() {return x;}
        int getY() {return y;}
        void addToArea(int i) { area+=i; }
        int getArea() {return area;}
        void setIsNextToWall(boolean state) {
            isNextToWall =state;
        }
        boolean isNextToWall() {return isNextToWall;}

        int getDistance(int x, int y) {
            return Math.abs(getX()-x) + Math.abs(getY()-y);
        }
    }

    static ArrayList<Coordinate> splitInput(String input) {
        return ( ArrayList<Coordinate> ) Arrays.stream(input.split("\n"))
                                                .map(c -> new Coordinate(Integer.valueOf(c.split(", ")[0]),Integer.valueOf(c.split(", ")[1])))
                                                .collect(Collectors.toList());
       /* ArrayList<Coordinate> coordinates = new ArrayList<>();
        String[] rows = input.split("\n");
        for (String row : rows) {
            int x = Integer.valueOf(row.split(", ")[0]);
            int y = Integer.valueOf(row.split(", ")[1]);
            coordinates.add(new Coordinate(x,y));
        }
        return coordinates; */
    }
    static void setAreaForCoordiante(int x, int y, List<Coordinate> coordinates, int areaSize) {
        int closestDistance = 999;      // This ought to be high enough...
        int closestPoint = -1;          // Negative since 0 and above is indexes used by our points.

        for (int i=0; i<coordinates.size(); i++) {
            int distance = coordinates.get(i).getDistance(x,y);
            if (distance < closestDistance) {        //Strictly closest to one point (so far)
                closestPoint = i;
                closestDistance = distance;
            } else if (distance == closestDistance){ //Equal distance to two or more points
                closestPoint = -1;
                closestDistance = distance;
            }
        }
        if (closestPoint >= 0) {                           // A single point ended up closest
            coordinates.get(closestPoint).addToArea(1);  // so we add one to that points covered area

            if (x == 0 || x == areaSize-1 || y == 0 || y == areaSize-1) { // If it is at the edge, it will keep growing in that direction.
                coordinates.get(closestPoint).setIsNextToWall(true); // So we make a note that this points area is at the edge
            }
        }
    }
    static int totalDistance(int x, int y, List<Coordinate> coordinates) {
        return coordinates.stream()                           // For each element in the list
                          .mapToInt(c -> c.getDistance(x,y))  // find the distance to this coordinate
                          .sum();                             // and return the sum of all the distances.
//    for (Coordinate c : coordinates) {
    //      distance += c.getDistance(x,y);
    // }
//      return distance;
    }

    static int largestFiniteArea(String input) {
        ArrayList<Coordinate> locations = splitInput(input);
        int areaSize=400;                               // Arbitrarily chosen grid size that seem to cover the relevant area.
        for (int x=0; x < areaSize; x++) {
            for (int y = 0; y < areaSize; y++) {
                setAreaForCoordiante(x, y, locations, areaSize);
            }
        }
        return locations.stream()
                .filter(c -> !c.isNextToWall())     // Remove any area touching a wall
                .mapToInt(Coordinate::getArea)      // Ge the size of the area
                .max()                              // Ignore all that isn't the biggest one
                .orElseThrow(NoSuchElementException::new);  //This won't happen, unless we only have infinite areas.

    }

    static int regionWithTotalDistanceLessThan(String input, int distanceLimit) {
        ArrayList<Coordinate> locations = splitInput(input);
        int regionSize=0;
        int areaSize=400;

        for (int x=0; x < areaSize; x++) {
            for (int y = 0; y < areaSize; y++) {
                if (totalDistance(x, y, locations) < distanceLimit) {
                    regionSize++;
                }
            }
        }

        return regionSize;
    }

}
