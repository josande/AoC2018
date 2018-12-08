package day25;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day25.Day25.solveA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    @Test
    void example_A() {
        String input = "";
        assertEquals("", solveA(input));
    }

    @Test
    void puzzle() {
        String day = "25";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
    }
}