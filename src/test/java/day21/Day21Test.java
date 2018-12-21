package day21;

import org.junit.jupiter.api.Test;

import static day21.Day21.solveA;
import static day21.Day21.solveB;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    @Test
    void example_A() {
        String input = "#ip 4\n" +
                "seti 123 0 5\n" +
                "bani 5 456 5\n" +
                "eqri 5 72 5\n" +
                "addr 5 4 4\n" +
                "seti 0 0 4\n" +
                "seti 0 9 5\n" +
                "bori 5 65536 3\n" +
                "seti 10828530 0 5\n" +
                "bani 3 255 2\n" +
                "addr 5 2 5\n" +
                "bani 5 16777215 5\n" +
                "muli 5 65899 5\n" +
                "bani 5 16777215 5\n" +
                "gtir 256 3 2\n" +
                "addr 2 4 4\n" +
                "addi 4 1 4\n" +
                "seti 27 4 4\n" +
                "seti 0 4 2\n" +
                "addi 2 1 1\n" +
                "muli 1 256 1\n" +
                "gtrr 1 3 1\n" +
                "addr 1 4 4\n" +
                "addi 4 1 4\n" +
                "seti 25 9 4\n" +
                "addi 2 1 2\n" +
                "seti 17 9 4\n" +
                "setr 2 8 3\n" +
                "seti 7 9 4\n" +
                "eqrr 5 0 2\n" +
                "addr 2 4 4\n" +
                "seti 5 5 4";
        solveA(input);
    }
    @Test
    void example_B() {
        String input = "";
        assertEquals("", solveB(input));
    }
   /* @Test
    void puzzle() {
        String day = "21";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));  // 202209
        System.out.println("Day" + day + "(b): " + solveB(input));  // 11777564
    }*/
}