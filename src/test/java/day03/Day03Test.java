package day03;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day03.Day03.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    @Test
    void exampleOne() {
        String input = "#1 @ 1,3: 4x4\n" +
                       "#2 @ 3,1: 4x4\n" + //overlaps 4 (1&2 overlap)
                       "#3 @ 5,5: 2x2";
        assertEquals(4, solveA(input));
   }

    @Test
    void exampleTwo() {
        String input = "#1 @ 1,3: 4x4\n" +
                       "#3 @ 5,5: 2x2"; //overlaps zero
        assertEquals(0, solveA(input));
    }


    @Test
    void exampleThree() {
        String input = "#1 @ 1,3: 4x4\n" +
                       "#2 @ 3,1: 4x4\n" + //overlaps 4 (1&2 overlap)
                       "#3 @ 5,5: 2x2";
        assertEquals(3, solveB(input));
    }

    @Test
    void puzzle() {
        String day = "03";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
        System.out.println("Day"+ day +"(a): " + solveA(input));
        System.out.println("Day"+ day +"(b): " + solveB(input));
    }
}