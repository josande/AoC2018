package day16;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.Arrays;

import static day16.Day16.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day16Test {

    @Test
    void testAddr() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,3,1};
        assertTrue(Arrays.equals(expected, addr(state, a,b,c)));
    }
    @Test
    void testAddi() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,2,1};
        assertTrue(Arrays.equals(expected, addi(state, a,b,c)));
    }

    @Test
    void testMulr() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,2,1};
        assertTrue(Arrays.equals(expected, mulr(state, a,b,c)));
    }
    @Test
    void testMuli() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,1,1};
        assertTrue(Arrays.equals(expected, muli(state, a,b,c)));
    }

    @Test
    void testBanr() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, banr(state, a,b,c)));
    }
    @Test
    void testBani() {
        int[] state = {3, 2, 1, 1};
        int a = 1;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, bani(state, a,b,c)));
    }

    @Test
    void testBorr() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,3,1};
        assertTrue(Arrays.equals(expected, borr(state, a,b,c)));
    }
    @Test
    void testBori() {
        int[] state = {3, 2, 1, 1};
        int a = 1;
        int b = 7;
        int c = 2;
        int[] expected = {3,2,7,1};
        assertTrue(Arrays.equals(expected, bori(state, a,b,c)));
    }

    @Test
    void testSetr() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 1;
        int[] expected = {3,1,1,1};
        assertTrue(Arrays.equals(expected, setr(state, a,b,c)));
    }
    @Test
    void testSeti() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 7;
        int c = 2;
        int[] expected = {3,2,3,1};
        assertTrue(Arrays.equals(expected, seti(state, a,b,c)));
    }

    @Test
    void testGtirOne() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 3;
        int c = 2;
        int[] expected = {3,2,1,1};
        assertTrue(Arrays.equals(expected, gtir(state, a,b,c)));
    }
    @Test
    void testGtirTwo() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 0;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtir(state, a,b,c)));
    }
    @Test
    void testGtirThree() {
        int[] state = {3, 2, 1, 1};
        int a = 0;
        int b = 3;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtir(state, a,b,c)));
    }

    @Test
    void testGtriOne() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 3;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtri(state, a,b,c)));
    }
    @Test
    void testGtriTwo() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtri(state, a,b,c)));
    }
    @Test
    void testGtriThree() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 0;
        int c = 2;
        int[] expected = {3,2,1,1};
        assertTrue(Arrays.equals(expected, gtri(state, a,b,c)));
    }

    @Test
    void testGtrrOne() {
        int[] state = {3, 2, 0, 1};
        int a = 1;
        int b = 2;
        int c = 2;
        int[] expected = {3,2,1,1};
        assertTrue(Arrays.equals(expected, gtrr(state, a,b,c)));
    }
    @Test
    void testGtrrTwo() {
        int[] state = {3, 2, 0, 1};
        int a = 3;
        int b = 3;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtrr(state, a,b,c)));
    }
    @Test
    void testGtrrThree() {
        int[] state = {3, 2, 1, 1};
        int a = 3;
        int b = 0;
        int c = 2;
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(expected, gtrr(state, a,b,c)));
    }

    @Test
    void testEqirOne() {
        int[] state = {1, 2, 3, 4};
        int a = 1;
        int b = 0;
        int c = 2;
        int[] expected = {1,2,1,4};
        assertTrue(Arrays.equals(expected, eqir(state, a,b,c)));
    }
    @Test
    void testEqirTwo() {
        int[] state = {1, 2, 3, 4};
        int a = 2;
        int b = 0;
        int c = 2;
        int[] expected = {1,2,0,4};
        assertTrue(Arrays.equals(expected, eqir(state, a,b,c)));
    }

    @Test
    void testEqriOne() {
        int[] state = {1, 2, 3, 4};
        int a = 2;
        int b = 3;
        int c = 2;
        int[] expected = {1,2,1,4};
        assertTrue(Arrays.equals(expected, eqri(state, a,b,c)));
    }
    @Test
    void testEqriTwo() {
        int[] state = {1, 2, 3, 4};
        int a = 2;
        int b = 0;
        int c = 2;
        int[] expected = {1,2,0,4};
        assertTrue(Arrays.equals(expected, eqri(state, a,b,c)));
    }

    @Test
    void testEqrrOne() {
        int[] state = {2, 2, 3, 4};
        int a = 0;
        int b = 1;
        int c = 2;
        int[] expected = {2,2,1,4};
        assertTrue(Arrays.equals(expected, eqrr(state, a,b,c)));
    }
    @Test
    void testEqrrTwo() {
        int[] state = {1, 2, 3, 4};
        int a = 0;
        int b = 1;
        int c = 2;
        int[] expected = {1,2,0,4};
        assertTrue(Arrays.equals(expected, eqrr(state, a,b,c)));
    }

    @Test
    void testSeeHowManyOpCodeThisWorksFor() {
        int[] state = {3, 2, 1, 1};
        int a = 2;
        int b = 1;
        int c = 2;
        int[] expected = {3,2,2,1};
        assertEquals(3, returnSetOfPossibleOperations(state,a,b,c,expected).size());
    }

    @Test
    void testSolveA() {
        String input="Before: [3, 2, 1, 1]\n" +
                     "9 2 1 2\n" +
                     "After:  [3, 2, 2, 1]";
        assertEquals(1, solveA(input));
    }

    @Test
    void puzzle() {
        String day = "16";
        String inputA = new Utils().readFile("Day" + day + "InputA.txt");
        String inputB = new Utils().readFile("Day" + day + "InputB.txt");

        System.out.println("Day" + day + "(a): " + solveA(inputA));
        System.out.println("Day" + day + "(b): " + solveB(inputA, inputB));
    }

}