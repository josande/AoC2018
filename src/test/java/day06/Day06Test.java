package day06;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day06.Day06.solveA;
import static day06.Day06.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void exampleOne() {
        String input = "1, 1\n" +
                        "1, 6\n" +
                        "8, 3\n" +
                        "3, 4\n" +
                        "5, 5\n" +
                        "8, 9";

        assertEquals(17, solveA(input));
    }
    @Test
    void exampleTwo() {
        String input = "1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9";

        assertEquals(16, solveB(input,32));
    }
    @Test
    void puzzle() {
        String day = "06";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
        System.out.println("Day"+ day +"(a): " + solveA(input));
        System.out.println("Day"+ day +"(b): " + solveB(input,10000));
    }
}