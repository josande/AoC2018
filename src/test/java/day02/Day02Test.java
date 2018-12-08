package day02;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static day02.Day02.*;
import static org.junit.jupiter.api.Assertions.*;


class Day02Test {

    @Test
    void testExampleOne() {
        String input = "abcdef\n" +
                       "bababc\n" +
                       "abbcde\n" +
                       "abcccd\n" +
                       "aabcdd\n" +
                       "abcdee\n" +
                       "ababab";
        assertEquals(12, solveA(input));
    }

    @Test
    void testExampleTwo() {
        String input = "abcdef\n" +
                       "abbcde\n" +
                       "abcccd\n" +
                       "aabcdd\n" +
                       "abcdee\n" +
                       "ababab";
        assertEquals(6, solveA(input));
    }

    @Test
    void testContainsCombination_PairAndTriple() {
        String input = "bababc";
        assertTrue(containsCombination(input,2));
        assertTrue(containsCombination(input,3));
    }

    @Test
    void testContainsCombination_NoPair() {
        String input = "abcdef";
        assertFalse(containsCombination(input,2));
        assertFalse(containsCombination(input,3));

    }

    @Test
    void testContainsCombination_TwoPair() {
        String input = "aabcdd";
        assertTrue(containsCombination(input,2));
        assertFalse(containsCombination(input,3));
    }

    @Test
    void testContainsCombination_TwoTripleNoPair() {
        String input = "ababab";
        assertFalse(containsCombination(input,2));
        assertTrue(containsCombination(input,3));

    }

    @Test
    void testContainsCombination_Pair() {
        String input = "abcdee";
        assertTrue(containsCombination(input,2));
        assertFalse(containsCombination(input,3));

    }

    @Test
    void testIsMatch_Matches() {
        String inputA = "fghij";
        String inputB = "fguij";
        assertTrue(isMatch(inputA,inputB));
        assertTrue(isMatch(inputB,inputA));
    }

    @Test
    void testIsMatch_NoMatch() {
        String inputA = "abcde";
        String inputB = "axcye";
        assertFalse(isMatch(inputA,inputB));
        assertFalse(isMatch(inputB,inputA));
    }
    @Test
    void testIsMatch_ExactMatch() {
        String inputA = "abcde";
        String inputB = "abcde";
        assertFalse(isMatch(inputA,inputB));
        assertFalse(isMatch(inputB,inputA));
    }

    @Test
    void testFindCommonLetters() {
        String inputA = "fghij";
        String inputB = "fguij";
        assertEquals("fgij",findCommonLetters(inputA,inputB));
    }

    @Test
    void testFindCommonLettersTwo() {
        String inputA = "ABBA";
        String inputB = "ABCD";
        assertEquals("AB",findCommonLetters(inputA,inputB));
    }


    @Test
    void testExampleOnePartTwo() {
        String input = "abcde\n" +
                       "fghij\n" +
                       "klmno\n" +
                       "pqrst\n" +
                       "fguij\n" +
                       "axcye\n" +
                       "wvxyz";
        assertEquals("fgij", solveB(input));
    }
    @Test
    void testExampleOneMatchesAreAtTheEnd() {
        String input = "abcde\n" +
                       "klmno\n" +
                       "pqrst\n" +
                       "axcye\n" +
                       "fghij\n" +
                       "fguij";
        assertEquals("fgij", solveB(input));
    }
    @Test
    void puzzle() {
        String day = "02";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
        System.out.println("Day"+ day +"(a): " + solveA(input));
        System.out.println("Day"+ day +"(b): " + solveB(input));
    }
}