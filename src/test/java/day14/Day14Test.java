package day14;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day14.Day14.solveA;
import static day14.Day14.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void example_A() {
        assertEquals("5158916779", solveA("9"));
        assertEquals("0124515891", solveA("5"));
        assertEquals("9251071085", solveA("18"));
        assertEquals("5941429882", solveA("2018"));


    }
    @Test
    void example_B() {
        assertEquals(9, solveB("51589"));
        assertEquals(5, solveB("01245"));
        assertEquals(18, solveB("92510"));
        assertEquals(2018, solveB("59414"));
    }

    @Test
    void puzzle() {
        String day = "14";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}