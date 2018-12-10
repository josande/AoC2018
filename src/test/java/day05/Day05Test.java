package day05;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day05.Day05.getBestReducedPolymerLength;
import static day05.Day05.getRemainingUnitsLength;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void exampleOne() {
        String input ="dabAcCaCBAcCcaDA";
        assertEquals(10, getRemainingUnitsLength(input));

    }
    @Test
    void exampleTwo() {
        String input="dabAcCaCBAcCcaDA";
        assertEquals(4, getBestReducedPolymerLength(input));

    }
    @Test
    void puzzle() {
        String day = "05";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
		System.out.println("Day"+ day +"(a): "+getRemainingUnitsLength(input));
        System.out.println("Day"+ day +"(b): "+getBestReducedPolymerLength(input));
    }


}