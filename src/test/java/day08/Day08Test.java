package day08;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day08.Day08.solveA;
import static day08.Day08.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {
    @Test
    void example_A() {
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        assertEquals(138, solveA(input));
    }
    @Test
    void example_B() {
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        assertEquals(66, solveB(input));
    }
    @Test
    void puzzle() {
        String day = "08";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }

}