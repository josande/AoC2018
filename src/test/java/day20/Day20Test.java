package day20;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static day20.Day20.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day20Test {

    @Test
    void testGetAllPaths1() {
        String input = "^ENWWW(NEEE|SSE(EE|N))$";
        List<String> expected = new ArrayList<>();
        expected.add("ENWWWNEEE");
        expected.add("ENWWWSSEEE");
        expected.add("ENWWWSSEN");
        List<String> result = getAllPaths(input);

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
        assertEquals(16, getAllRooms(result).size());

    }

    @Test
    void testGetAllPaths2() {
        String input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
        List<String> expected = new ArrayList<>();
        expected.add("ENNWSWWSSSEENEENNN");
        expected.add("ENNWSWWNEWSSSSEENEENNN");
        expected.add("ENNWSWWNEWSSSSEENEESWENNNN");
        expected.add("ENNWSWWSSSEENWNSEEENNN");

        List<String> result = getAllPaths(input);

        assertTrue(result.containsAll(expected));
       // assertEquals(25, getAllRooms(input).size());

        assertEquals(25, getAllRooms(result).size());


    }



    @Test
    void example_A() {
        String input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
        assertEquals(18, solveA(input));
    }
    @Test
    void example_B() {
        String input = "";
        assertEquals("", solveB(input));
    }
    //@Test
    void puzzle() {
        String day = "20";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}