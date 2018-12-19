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
    void f00ale() {
        String input="#ip 3\n" +
                "addi 3 16 3\n" +
                "seti 1 5 1\n" +
                "seti 1 4 4\n" +
                "mulr 1 4 5\n" +
                "eqrr 5 2 5\n" +
                "addr 5 3 3\n" +
                "addi 3 1 3\n" +
                "addr 1 0 0\n" +
                "addi 4 1 4\n" +
                "gtrr 4 2 5\n" +
                "addr 3 5 3\n" +
                "seti 2 6 3\n" +
                "addi 1 1 1\n" +
                "gtrr 1 2 5\n" +
                "addr 5 3 3\n" +
                "seti 1 1 3\n" +
                "mulr 3 3 3\n" +
                "addi 2 2 2\n" +
                "mulr 2 2 2\n" +
                "mulr 3 2 2\n" +
                "muli 2 11 2\n" +
                "addi 5 3 5\n" +
                "mulr 5 3 5\n" +
                "addi 5 3 5\n" +
                "addr 2 5 2\n" +
                "addr 3 0 3\n" +
                "seti 0 6 3\n" +
                "setr 3 8 5\n" +
                "mulr 5 3 5\n" +
                "addr 3 5 5\n" +
                "mulr 3 5 5\n" +
                "muli 5 14 5\n" +
                "mulr 5 3 5\n" +
                "addr 2 5 2\n" +
                "seti 0 2 0\n" +
                "seti 0 2 3\n";
        assertEquals(1092, solveA(input));
        assertEquals(13406472, solveB(input));


    }

    @Test
    void puzzle() {
        String day = "19";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}