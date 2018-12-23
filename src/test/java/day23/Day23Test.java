package day23;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

import static day23.Day23.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {

    @Test
    void example_A() {
        String input = "pos=<0,0,0>, r=4\n" +
                       "pos=<1,0,0>, r=1\n" +
                       "pos=<4,0,0>, r=3\n" +
                       "pos=<0,2,0>, r=1\n" +
                       "pos=<0,5,0>, r=3\n" +
                       "pos=<0,0,3>, r=1\n" +
                       "pos=<1,1,1>, r=1\n" +
                       "pos=<1,1,2>, r=1\n" +
                       "pos=<1,3,1>, r=1";
        List<Day23.Nanobot> allBots = getAllNanobots(input);
        assertEquals(9, allBots.size());
        Nanobot strongestBot = getStrongestBot(allBots);
        assertEquals(4, strongestBot.getRadius());
        assertEquals(7, botsInRangeOf(strongestBot, allBots).size());

        assertEquals(7, solveA(input));
    }
    @Test
    void example_B() {
        String input = "pos=<10,12,12>, r=2\n" +
                "pos=<12,14,12>, r=2\n" +
                "pos=<16,12,12>, r=4\n" +
                "pos=<14,14,14>, r=6\n" +
                "pos=<50,50,50>, r=200\n" +
                "pos=<10,10,10>, r=5";
        assertEquals(36, solveB(input));
    }
    @Test
    void puzzle() {
        String day = "23";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}