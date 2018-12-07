package day07;

import javafx.util.Pair;

import java.util.*;

public class Day07 {
    static private HashMap<Character, String> makeWorkOrder(String input) {
        String[] instructions=input.split("\n");
        HashMap<Character, String> toDoList=new HashMap<>();
        for (String instruction : instructions) {
            Character dependsOn = instruction.charAt(5);
            Character task = instruction.charAt(36);

            String dependencies = toDoList.getOrDefault(task, "");
            dependencies+=dependsOn;
            toDoList.put(task, dependencies);

            if (!toDoList.containsKey(dependsOn)) {
                toDoList.put(dependsOn, "");
            }
        }
        return toDoList;
    }
    static String  findBuildOrder(String input) {

        HashMap<Character, String> toDoList = makeWorkOrder(input);
        String result = "";
        while(!toDoList.isEmpty()) {
            for (Map.Entry<Character, String> entry : toDoList.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    result += entry.getKey();
                    removeDependencies(toDoList, entry.getKey());
                    toDoList.remove(entry.getKey());
                    break;
                }
            }
        }
        return result;

    }


    static int findShortestTime(String input, int baseTimEToSolve, int numberOfWorkers) {
        HashMap<Character, String> toDoList = makeWorkOrder(input);

        Pair<Character, Integer>[] elves = new Pair[numberOfWorkers];
        for (int e = 0; e < elves.length; e++) {
            elves[e] = new Pair(' ',0);
        }


        for (int time =0; ; time++) {
            for (int e = 0; e < elves.length; e++) {
                if (elves[e].getValue() == time) {
                    toDoList = removeDependencies(toDoList, elves[e].getKey());
                    toDoList.remove(elves[e].getKey());
                    elves[e] = new Pair(' ', 0);
                }
            }
            handOutNewJobs(toDoList, elves, time, baseTimEToSolve);

            if (toDoList.isEmpty()) { return time; }
        }
    }
    static void handOutNewJobs(HashMap<Character, String> toDoList, Pair<Character, Integer>[] elves, int currentTime, int baseTimEToSolve) {
        List<Character> unlockedTasks = new ArrayList<>();
        // Jobs without dependencies
        for (Map.Entry<Character, String> entry : toDoList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                unlockedTasks.add(entry.getKey());
            }
        }
        // Remove jobs worked on
        for (Pair elf : elves) {
            unlockedTasks.remove(elf.getKey());
        }
        // If there is job to be done and elves with free hands, put them to work
        if (!unlockedTasks.isEmpty()) {
            int tasksAssigned = 0;
            for (int e = 0; e < elves.length; e++) {
                if (elves[e].getValue() == 0 && unlockedTasks.size() > tasksAssigned) {
                    elves[e] = new Pair(unlockedTasks.get(tasksAssigned), currentTime + baseTimEToSolve + unlockedTasks.get(tasksAssigned++) - 64);
                }
            }
        }
    }
    static HashMap<Character, String> removeDependencies(HashMap<Character, String> toDoList, Character c) {
        toDoList.forEach((key, value) -> {
            for (Map.Entry<Character, String> current : toDoList.entrySet()) {
                current.setValue((current.getValue()).replaceAll(("" + c), ""));
            }
        });
        return toDoList;
    }
}
