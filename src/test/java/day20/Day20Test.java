package day20;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day20.Day20.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    @Test
    void example_A() {
        String input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
        assertEquals(18, solveA(input));
    }
    @Test
    void example_ReturnTOSameBranch() {
        String input = "^NN(N|N)SS$";
        assertEquals(3, solveA(input));
    }

    @Test
    void example_B() {
        String input = "^NN(EW|WE)SSSS$";
        assertEquals(2, roomsWithinDoors(depthFirst(input),1));
        assertEquals(5, roomsWithinDoors(depthFirst(input),4));
    }
    @Test
    void puzzle() {
        String day = "20";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}