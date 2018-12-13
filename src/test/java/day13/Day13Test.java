package day13;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day13.Day13.solveA;
import static day13.Day13.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {


    @Test
    void example_A() {
        String input = "/->-\\        \n" +
                       "|   |  /----\\\n" +
                       "| /-+--+-\\  |\n" +
                       "| | |  | v  |\n" +
                       "\\-+-/  \\-+--/\n" +
                       "  \\------/   ";
        assertEquals("7,3", solveA(input));
    }
    @Test
    void example_B() {
        String input = "";
        assertEquals("", solveB(input));
    }
    @Test
    void puzzle() {
        String day = "13";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}