package day17;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day17.Day17.makeMap;
import static day17.Day17.solveA;
import static day17.Day17.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    @Test
    void example_A() {
        String input = "x=495, y=2..7\n" +
                "y=7, x=495..501\n" +
                "x=501, y=3..7\n" +
                "x=498, y=2..4\n" +
                "x=506, y=1..2\n" +
                "x=498, y=10..13\n" +
                "x=504, y=10..13\n" +
                "y=13, x=498..504";
        assertEquals(57, solveA(input));
    }
    @Test
    void example_B() {
        String input = "x=495, y=2..7\n" +
                "y=7, x=495..501\n" +
                "x=501, y=3..7\n" +
                "x=498, y=2..4\n" +
                "x=506, y=1..2\n" +
                "x=498, y=10..13\n" +
                "x=504, y=10..13\n" +
                "y=13, x=498..504";
        assertEquals(29, solveB(input));
    }
    @Test
    void puzzle() {
        String day = "17";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }

}