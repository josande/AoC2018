package day01;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;

import static day01.Day01.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @Test
    void exampleOne() {
        String input = "+1, -2, +3, +1";
        assertEquals(3, solveA(input, ", "));
    }

    @Test
    void exampleTwo() {
        String input = "+1, +1, +1";
        assertEquals(3, solveA(input, ", "));
    }

    @Test
    void exampleThree() {
        String input = "+1, +1, -2";
        assertEquals(0, solveA(input, ", "));
    }

    @Test
    void exampleFour() {
        String input = "-1, -2, -3";
        assertEquals(-6, solveA(input, ", "));
    }

    @Test
    void testGetFrequenciesFromString() {
        String input = "+1, -2, +3, +1";
        ArrayList<Integer> frequencyList = getFrequenciesFromString(input, ", ");
        assertEquals(4, frequencyList.size());
        assertEquals((Integer) 1, frequencyList.get(0));
        int expected = -2;
        assertEquals((Integer) expected, frequencyList.get(1));
        assertEquals((Integer) 3, frequencyList.get(2));
    }

    @Test
    void exampleOneGetFirstRepeatedFrequency() {
        String input = "+1, -2, +3, +1";
        assertEquals(2, solveB(input, ", "));
    }

    @Test
    void exampleTwoGetFirstRepeatedFrequency() {
        String input = "+1, -1";
        assertEquals(0, solveB(input, ", "));
    }

    @Test
    void exampleThreeGetFirstRepeatedFrequency() {
        String input = "+3, +3, +4, -2, -4";
        assertEquals(10, solveB(input, ", "));
    }

    @Test
    void exampleFourGetFirstRepeatedFrequency() {
        String input = "-6, +3, +8, +5, -6";
        assertEquals(5, solveB(input, ", "));
    }

    @Test
    void exampleFiveGetFirstRepeatedFrequency() {
        String input = "+7, +7, -2, -7, -4";
        assertEquals(14, solveB(input, ", "));
    }

    @Test
    void exampleTestGetFirstRepeatedFrequency() {
        String input = "+7\n+7\n-2\n-7\n-4";
        assertEquals(14, solveB(input, "\n?\n"));
    }
    @Test
    void puzzle() {
        String day = "01";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
        System.out.println("Day"+ day +"(a): " + solveA(input,"\r?\n"));
        System.out.println("Day"+ day +"(b): " + solveB(input,"\r?\n"));
    }

}