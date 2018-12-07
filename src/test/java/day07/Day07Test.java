package day07;

import org.junit.jupiter.api.Test;

import static day07.Day07.findBuildOrder;
import static day07.Day07.findShortestTime;
import static org.junit.jupiter.api.Assertions.*;

class Day07Test {
    @Test
    public void exampleOne() {
        String input="Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        assertEquals("CABDFE", findBuildOrder(input));
    }
    @Test
    public void exampleTwo() {
        String input="Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        assertEquals(15, findShortestTime(input, 0, 2));
    }
    @Test
    void puzzle() {
        String input="Step G must be finished before step S can begin.\n" +
                "Step T must be finished before step Q can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step H must be finished before step X can begin.\n" +
                "Step V must be finished before step O can begin.\n" +
                "Step Z must be finished before step P can begin.\n" +
                "Step R must be finished before step J can begin.\n" +
                "Step L must be finished before step Y can begin.\n" +
                "Step Y must be finished before step E can begin.\n" +
                "Step W must be finished before step X can begin.\n" +
                "Step X must be finished before step B can begin.\n" +
                "Step K must be finished before step E can begin.\n" +
                "Step Q must be finished before step P can begin.\n" +
                "Step U must be finished before step B can begin.\n" +
                "Step M must be finished before step O can begin.\n" +
                "Step P must be finished before step N can begin.\n" +
                "Step I must be finished before step J can begin.\n" +
                "Step B must be finished before step C can begin.\n" +
                "Step C must be finished before step O can begin.\n" +
                "Step J must be finished before step F can begin.\n" +
                "Step F must be finished before step O can begin.\n" +
                "Step E must be finished before step D can begin.\n" +
                "Step D must be finished before step N can begin.\n" +
                "Step N must be finished before step S can begin.\n" +
                "Step S must be finished before step O can begin.\n" +
                "Step W must be finished before step O can begin.\n" +
                "Step L must be finished before step P can begin.\n" +
                "Step N must be finished before step O can begin.\n" +
                "Step T must be finished before step D can begin.\n" +
                "Step G must be finished before step I can begin.\n" +
                "Step V must be finished before step X can begin.\n" +
                "Step B must be finished before step N can begin.\n" +
                "Step R must be finished before step N can begin.\n" +
                "Step H must be finished before step J can begin.\n" +
                "Step B must be finished before step S can begin.\n" +
                "Step P must be finished before step I can begin.\n" +
                "Step A must be finished before step J can begin.\n" +
                "Step A must be finished before step U can begin.\n" +
                "Step B must be finished before step D can begin.\n" +
                "Step T must be finished before step A can begin.\n" +
                "Step U must be finished before step D can begin.\n" +
                "Step T must be finished before step L can begin.\n" +
                "Step I must be finished before step E can begin.\n" +
                "Step R must be finished before step U can begin.\n" +
                "Step H must be finished before step S can begin.\n" +
                "Step P must be finished before step F can begin.\n" +
                "Step Q must be finished before step C can begin.\n" +
                "Step A must be finished before step P can begin.\n" +
                "Step X must be finished before step E can begin.\n" +
                "Step Q must be finished before step N can begin.\n" +
                "Step E must be finished before step N can begin.\n" +
                "Step Q must be finished before step O can begin.\n" +
                "Step J must be finished before step S can begin.\n" +
                "Step X must be finished before step P can begin.\n" +
                "Step K must be finished before step U can begin.\n" +
                "Step F must be finished before step E can begin.\n" +
                "Step C must be finished before step E can begin.\n" +
                "Step H must be finished before step K can begin.\n" +
                "Step W must be finished before step B can begin.\n" +
                "Step G must be finished before step O can begin.\n" +
                "Step F must be finished before step N can begin.\n" +
                "Step I must be finished before step D can begin.\n" +
                "Step G must be finished before step V can begin.\n" +
                "Step E must be finished before step S can begin.\n" +
                "Step Y must be finished before step P can begin.\n" +
                "Step G must be finished before step E can begin.\n" +
                "Step P must be finished before step J can begin.\n" +
                "Step U must be finished before step N can begin.\n" +
                "Step U must be finished before step F can begin.\n" +
                "Step X must be finished before step U can begin.\n" +
                "Step X must be finished before step C can begin.\n" +
                "Step R must be finished before step Q can begin.\n" +
                "Step Q must be finished before step E can begin.\n" +
                "Step Z must be finished before step E can begin.\n" +
                "Step X must be finished before step F can begin.\n" +
                "Step J must be finished before step D can begin.\n" +
                "Step X must be finished before step M can begin.\n" +
                "Step Y must be finished before step D can begin.\n" +
                "Step K must be finished before step J can begin.\n" +
                "Step Z must be finished before step J can begin.\n" +
                "Step M must be finished before step P can begin.\n" +
                "Step T must be finished before step M can begin.\n" +
                "Step F must be finished before step S can begin.\n" +
                "Step P must be finished before step S can begin.\n" +
                "Step X must be finished before step I can begin.\n" +
                "Step U must be finished before step J can begin.\n" +
                "Step M must be finished before step B can begin.\n" +
                "Step Q must be finished before step D can begin.\n" +
                "Step Z must be finished before step I can begin.\n" +
                "Step D must be finished before step S can begin.\n" +
                "Step J must be finished before step N can begin.\n" +
                "Step D must be finished before step O can begin.\n" +
                "Step T must be finished before step H can begin.\n" +
                "Step P must be finished before step D can begin.\n" +
                "Step M must be finished before step F can begin.\n" +
                "Step Y must be finished before step S can begin.\n" +
                "Step H must be finished before step I can begin.\n" +
                "Step Y must be finished before step W can begin.\n" +
                "Step X must be finished before step J can begin.\n" +
                "Step L must be finished before step W can begin.\n" +
                "Step G must be finished before step N can begin.";
        System.out.println("Day07(a): "+ findBuildOrder(input));
        System.out.println("Day07(b): "+ findShortestTime(input, 60,5)); //1072, to low
    }
}