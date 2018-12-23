package day22;

import org.junit.jupiter.api.Test;
import utils.Utils;
import utils.Utils.*;

import static day22.Day22.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    @Test
    public void testGeologicIndex() {

    }

    @Test
    void example_A() {
        String input = "depth: 510\n" +
                       "target: 10,10";
        Coordinate targetCoordinate = new Coordinate(10,10);
        int caveDepth = 510;
        assertEquals(114, solveA(input));
        assertEquals(0, getRisk(new Coordinate(0,0)));
        assertEquals(1, getRisk(new Coordinate(1,0)));
        assertEquals(2, getRisk(new Coordinate(1,1)));
    }
    @Test
    void example_B() {
        String input = "depth: 510\n" +
                       "target: 10,10";
        assertEquals(45, solveB(input));
    }
    @Test
    void example_web() {
        String input ="depth: 6969\n" +
                      "target: 9,796";
        assertEquals(1087, solveB(input));
    }
    @Test
    void puzzle() {
        String day = "22";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}