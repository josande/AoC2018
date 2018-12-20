package day20;

import com.mifmif.common.regex.Generex;
import javafx.util.Pair;
import utils.Utils.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Day20 {

    static List<String> getAllPaths(String input) {
        input=input.replaceAll("\\^", ""); ///Odd workaround
        input=input.replaceAll("\\$", ""); ///Odd workaround

        input=input.replaceAll("\\|\\)", "|X)"); ///Odd workaround
        Generex generex = new Generex(input);

        // Generate all String that matches the given Regex.
        List<String> matchedStrs = generex.getAllMatchedStrings();
        List<String> results = new ArrayList<>();
        for (String str: matchedStrs) {
            results.add(str.replaceAll("X", ""));
        }

        return results;
    }

    static void addRooms(HashMap<Coordinate, Room> rooms, String path) {
        Coordinate currentLocation = new Coordinate(0,0);
        for (int i=0;i<path.length(); i++) {
            currentLocation = nextRoom(currentLocation, path.charAt(i), rooms);
        }
    }
    static Coordinate getNextLocation(Coordinate currentLocation, char c) {
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

    static Pair<StringBuilder, Coordinate> addRooms(StringBuilder inputLeft, Coordinate location, HashMap<Coordinate, Room> rooms) {
        char c = inputLeft.charAt(0);
        while (c != '|' && c != '(' && c != ')') {
            inputLeft.deleteCharAt(0);
            location=nextRoom(location, c, rooms);
        }
        if (c != '|') {
            inputLeft = findEndOfParenthesis(inputLeft);
        }
        if (c == '(') {

        }
        return new Pair<>(inputLeft, location);
    }
    static Coordinate nextRoom(Coordinate currentLocation, char c, HashMap<Coordinate, Room> rooms) {
        Room currentRoom = rooms.getOrDefault(currentLocation, new Room());
        Coordinate nextLocation = getNextLocation(currentLocation, c);
        Room nextRoom = rooms.getOrDefault(nextLocation, new Room());
        currentRoom.addDoorTo(nextLocation);
        rooms.put(currentLocation,currentRoom);
        rooms.put(nextLocation,nextRoom);
        return nextLocation;
    }


    static HashMap<Coordinate, Room> getAllRooms(List<String> paths) {
        HashMap<Coordinate, Room> rooms = new HashMap<>();
        for (String path : paths) {
            addRooms(rooms, path);
        }
        return rooms;
    }

    static int findLongestDistance(HashMap<Coordinate, Room> rooms) {
        int longestSoFar = 0;
        Coordinate furthestAway=null;

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
                        furthestAway=current;
                        System.out.print("Found new end! "+furthestAway+" "+current);
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
        List<String> paths = getAllPaths(input);
        HashMap<Coordinate, Room> rooms = getAllRooms(paths);

        return findLongestDistance(rooms);
    }

    static String solveB(String input) {
        return "";
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
