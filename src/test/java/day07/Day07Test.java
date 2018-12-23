package day07;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day07.Day07.solveA;
import static day07.Day07.solveB;
import static org.junit.jupiter.api.Assertions.*;

class Day07Test {
    @Test
    void exampleOne() {
        String input="Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        assertEquals("CABDFE", solveA(input));
    }
    @Test
    void exampleTwo() {
        String input="Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        assertEquals(15, solveB(input, 0, 2));
    }
    @Test
    void puzzle() {
        String day = "07";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): "+ solveA(input));
        System.out.println("Day" + day + "(b): "+ solveB(input, 60,5));
    }
}