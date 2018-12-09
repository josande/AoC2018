package day09;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day09.Day09.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    @Test
    void example_A0() {
        String input = "9 players; last marble is worth 25 points";
        assertEquals(32, solveA(input));

    }
    @Test
    void example_A1() {
        String input = "10 players; last marble is worth 1618 points";
        assertEquals(8317, solveA(input));
    }

    @Test
    void example_A2() {
        String input = "13 players; last marble is worth 7999 points:";
        assertEquals(146373, solveA(input));
    }
    @Test
    void example_A3() {
        String input = "17 players; last marble is worth 1104 points";
        assertEquals(2764, solveA(input));
    }
    @Test
    void example_A4() {
        String input ="21 players; last marble is worth 6111 points";
        assertEquals(54718, solveA(input));
    }
    @Test
    void example_A5() {
        String input = "30 players; last marble is worth 5807 points";
        assertEquals(37305, solveA(input));
    }

    @Test
    void puzzle() {
        String day= "09";
        String input=new Utils().readFile("Day"+day+"Input.txt");
        System.out.println("Day"+day+"(a): " + solveA(input));
        System.out.println("Day"+day+"(b): " + solveB(input));
    }
}