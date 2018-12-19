package day19;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day19.Day19.solveA;
import static day19.Day19.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {

    @Test
    void example_A() {
        String input = "#ip 0\n" +
                "seti 5 0 1\n" +
                "seti 6 0 2\n" +
                "addi 0 1 0\n" +
                "addr 1 2 3\n" +
                "setr 1 0 0\n" +
                "seti 8 0 4\n" +
                "seti 9 0 5";
        assertEquals(7, solveA(input));
    }

    @Test
    void puzzle() {
        String day = "19";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}