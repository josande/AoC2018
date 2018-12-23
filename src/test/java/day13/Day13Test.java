package day13;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

import static day13.Day13.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {


    @Test
    void example_A() {
        String input = "/->-\\        \n" +
                       "|   |  /----\\\n" +
                       "| /-+--+-\\  |\n" +
                       "| | |  | v  |\n" +
                       "\\-+-/  \\-+--/\n" +
                       "  \\------/   ";
        assertEquals("7,3", solveA(input));
    }

    @Test
    void testSorting() {
        ArrayList<Cart> carts = new ArrayList<>();
        Cart a = new Cart(2,2, '<');
        Cart b = new Cart(1,1, '<');
        Cart c = new Cart(1,3, '<');
        Cart d = new Cart(0,2, '<');
        carts.add(a);
        carts.add(b);
        carts.add(c);
        carts.add(d);
        assertEquals(a, carts.get(0));
        assertEquals(b, carts.get(1));
        assertEquals(c, carts.get(2));
        assertEquals(d, carts.get(3));
        Collections.sort(carts);
        assertEquals(d, carts.get(0));
        assertEquals(b, carts.get(1));
        assertEquals(c, carts.get(2));
        assertEquals(a, carts.get(3));
    }
    @Test
    void example_B() {
        String input = "/>-<\\  \n" +
                       "|   |  \n" +
                       "| /<+-\\\n" +
                       "| | | v\n" +
                       "\\>+</ |\n" +
                       "  |   ^\n" +
                       "  \\<->/\n";
        assertEquals("6,4", solveB(input));
    }
    @Test
    void puzzle() {
        String day = "13";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}