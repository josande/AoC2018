package day21;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day21.Day21.solveA;
import static day21.Day21.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

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
        String day = "21";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}