package day12;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day12.Day12.solveA;
import static day12.Day12.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void example_A() {
        String input = "initial state: #..#.#..##......###...###\n" +
                "\n" +
                "...## => #\n" +
                "..#.. => #\n" +
                ".#... => #\n" +
                ".#.#. => #\n" +
                ".#.## => #\n" +
                ".##.. => #\n" +
                ".#### => #\n" +
                "#.#.# => #\n" +
                "#.### => #\n" +
                "##.#. => #\n" +
                "##.## => #\n" +
                "###.. => #\n" +
                "###.# => #\n" +
                "####. => #";
        assertEquals(325, solveA(input));
    }
    @Test
    void testNoMove() {
        String input = "initial state: #.\n" +
                "\n" +
                "..#.. => #";
        assertEquals(0, solveA(input));
    }

    @Test
    void puzzle() {
        String day = "12";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }

}