package day25;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day25.Day25.solveA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    @Test
    void example_A() {
        String input = " 0,0,0,0\n" +
                " 3,0,0,0\n" +
                " 0,3,0,0\n" +
                " 0,0,3,0\n" +
                " 0,0,0,3\n" +
                " 0,0,0,6\n" +
                " 9,0,0,0\n" +
                "12,0,0,0";
        assertEquals(2, solveA(input));
    }
    @Test
    void example_2() {
        String input="1,-1,-1,-2\n" +
                "-2,-2,0,1\n" +
                "0,2,1,3\n" +
                "-2,3,-2,1\n" +
                "0,2,3,-2\n" +
                "-1,-1,1,-2\n" +
                "0,-2,-1,0\n" +
                "-2,2,3,-1\n" +
                "1,2,2,0\n" +
                "-1,-2,0,-2";
        assertEquals(8, solveA(input));
    }

    @Test
    void puzzle() {
        String day = "25";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
    }
}