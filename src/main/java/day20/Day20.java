package day20;

import javafx.util.Pair;
import utils.Utils.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

class Day20 {

    private static Coordinate getNextLocation(Coordinate currentLocation, char c) {
        if (c == 'N')
            return currentLocation.getUp();
        if (c == 'S')
            return currentLocation.getDown();
        if (c == 'W')
            return currentLocation.getLeft();
        if (c == 'E')
            return currentLocation.getRight();
        return null;
    }

    static HashMap<Coordinate, Room> depthFirst(String input) {
        HashMap<Coordinate, Room> rooms = new HashMap<>();
        Coordinate startLocation = new Coordinate(0,0);

        Stack<Pair<String, Coordinate>> queue = new Stack<>();
        HashSet<Pair<String, Coordinate>> visited = new HashSet<>();

        queue.add(new Pair<>(input, startLocation));

        while(!queue.isEmpty()){
            Pair<String, Coordinate> current = queue.pop();

            String inputLeft = current.getKey();
            boolean isDone = visited.contains(current);

            Coordinate location = current.getValue();

            char c = inputLeft.charAt(0);
            if (isDone) {
             //   System.out.println("Been here, skipping on!");
            } else if (c == '$') {
              //  System.out.println("Reached the end! Queue:" + queue.size() + " Rooms found:" + rooms.size());
            } else if (c == '^') {
                visited.add(new Pair<>(inputLeft, location));
                inputLeft = inputLeft.substring(1);
                current = new Pair<>(inputLeft, location);
                queue.add(current);
            } else if (c != '|' && c != '(' && c != ')') {
                visited.add(new Pair<>(inputLeft, location));
                location = nextRoom(location, c, rooms);
                inputLeft = inputLeft.substring(1);
                current = new Pair<>(inputLeft, location);
                queue.add(current);
            } else if (c == ')') {
                visited.add(new Pair<>(inputLeft, location));
                inputLeft = inputLeft.substring(1);
                current = new Pair<>(inputLeft, location);
                queue.add(current);
            } else if (c == '(') {
                visited.add(new Pair<>(inputLeft, location));
                inputLeft = inputLeft.substring(1);
                current = new Pair<>(inputLeft, location);
                queue.add(current);
                int level = 1;
                while (level > 0) {
                    c = inputLeft.charAt(0);
                    inputLeft = inputLeft.substring(1);
                    if (c == '(') {
                        level++;
                    } else if (c == ')') {
                        level--;
                    }
                    if (level == 1 && c == '|') {
                        queue.add(new Pair<>(inputLeft, location));
                    }
                }
            } else if (c == '|') {
                visited.add(new Pair<>(inputLeft, location));
                int level = 1;
                while (level > 0) {
                    c = inputLeft.charAt(0);
                    inputLeft = inputLeft.substring(1);
                    if (c == '(') {
                        level++;
                    } else if (c == ')') {
                        level--;
                    }
                }
                queue.add(new Pair<>(inputLeft, location));
            }
        }
        return rooms;
    }

    private static Coordinate nextRoom(Coordinate currentLocation, char c, HashMap<Coordinate, Room> rooms) {
        Room currentRoom = rooms.getOrDefault(currentLocation, new Room());
        Coordinate nextLocation = getNextLocation(currentLocation, c);
        Room nextRoom = rooms.getOrDefault(nextLocation, new Room());
        currentRoom.addDoorTo(nextLocation);
        rooms.put(currentLocation,currentRoom);
        rooms.put(nextLocation,nextRoom);
        return nextLocation;
    }

    private static int findLongestDistance(HashMap<Coordinate, Room> rooms) {
        int longestSoFar = 0;

        Coordinate startLocation = new Coordinate(0,0);


        HashSet<Coordinate> queue = new HashSet<>();
        HashSet<Coordinate> scanned = new HashSet<>();

        queue.add(startLocation);
        for (int steps=0; ; steps++) {
            HashSet<Coordinate> newToAdd  = new HashSet<>();

            for (Coordinate current : queue) {
                if (scanned.contains(current))
                    continue;
                if (rooms.get(current).getLeadsTo().isEmpty() || scanned.containsAll(rooms.get(current).getLeadsTo())) {
                    if (steps>longestSoFar) {
                        longestSoFar=steps;
                    }
                }
                scanned.add(current);
                newToAdd.addAll(rooms.get(current).getLeadsTo());
            }
            queue.addAll(newToAdd);
            queue.removeAll(scanned);

            if (queue.isEmpty())
                return longestSoFar;
        }
    }

    static int solveA(String input) {
        HashMap<Coordinate, Room> rooms = depthFirst(input);
        return findLongestDistance(rooms);
    }

    static int solveB(String input) {
        HashMap<Coordinate, Room> rooms = depthFirst(input);
        return rooms.size()-roomsWithinDoors(rooms, 1000);
    }

    static int roomsWithinDoors(HashMap<Coordinate, Room> rooms, int limit) {
        Coordinate startLocation = new Coordinate(0,0);

        HashSet<Coordinate> queue = new HashSet<>();
        HashSet<Coordinate> scanned = new HashSet<>();

        HashSet<Coordinate> visited = new HashSet<>();

        queue.add(startLocation);
        for (int steps=0; steps<limit; steps++) {
            HashSet<Coordinate> newToAdd  = new HashSet<>();

            for (Coordinate current : queue) {
                visited.add(current);
                if (scanned.contains(current))
                    continue;
                scanned.add(current);
                newToAdd.addAll(rooms.get(current).getLeadsTo());
            }
            queue.addAll(newToAdd);
            queue.removeAll(scanned);
        }

        return visited.size();
    }

    static class Room {
        HashSet<Coordinate> leadsTo = new HashSet<>();
        void addDoorTo(Coordinate other) {
            leadsTo.add(other);
        }
        HashSet<Coordinate> getLeadsTo() {
            return leadsTo;
        }

    }

}
