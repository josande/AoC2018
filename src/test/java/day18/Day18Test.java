package day18;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day18.Day18.solveA;
import static day18.Day18.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void example_A() {
        String input = "";
        assertEquals("", solveA(input));
    }
    @Test
    void example_B() {
        String input = "";
        assertEquals("", solveB(input));
    }
    @Test
    void puzzle() {
        String day = "18";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}
