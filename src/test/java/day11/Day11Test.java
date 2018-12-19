package day11;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day11.Day11.getPowerLevel;
import static day11.Day11.solveA;
import static day11.Day11.solveB;
import static org.junit.jupiter.api.Assertions.*;

class Day11Test {


    @Test
    void example_A() {
        assertEquals(4, getPowerLevel(3,5,8));
        assertEquals(-5,getPowerLevel(122,79,57));
        assertEquals(0, getPowerLevel(217,196,39));
        assertEquals(4, getPowerLevel(101,153,71));

        assertEquals("21,61",solveA("42"));
        assertEquals("33,45",solveA("18"));

    }
    @Test
    void example_B() {
        assertEquals("90,269,16", solveB("18"));
        assertEquals("232,251,12", solveB("42"));
    }
    @Test
    void puzzle() {
        String day = "11";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }


}