package day15;

import org.junit.jupiter.api.Test;
import utils.Utils;
import utils.Utils.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static day15.Day15.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day15Test {


    @Test
    void testDrawDungeon() {
        String input =  "#######\n" +
                        "#.G.E.#\n" +
                        "#E.G.E#\n" +
                        "#.G.E.#\n" +
                        "#######";
        HashSet<Coordinate> dungeonMap = mapWalls(input);
        assertEquals(20, dungeonMap.size());
        assertEquals(true,  dungeonMap.contains(new Coordinate(0,0)) );
        assertEquals(false, dungeonMap.contains(new Coordinate(1,1)) );
        assertEquals(true,  dungeonMap.contains(new Coordinate(4,4)) );
        assertEquals(false, dungeonMap.contains(new Coordinate(4,1)) );
    }
    @Test
    void testFindAllUnits() {
        String input =  "#######\n" +
                        "#.G.E.#\n" +
                        "#E.G.E#\n" +
                        "#.G.E.#\n" +
                        "#######";
        List<Unit> units = findAllUnits(input);

        assertEquals(7,  units.size());

        assertEquals(true,  units.contains(new Unit(2,1,false)));
        assertEquals(false, units.contains(new Unit(2,1,true)));

        assertEquals(true, units.contains(new Unit(3,2,false)));

        assertEquals(true,  units.contains(new Unit(4,1,true)));
        assertEquals(false, units.contains(new Unit(4,1,false)));
    }
    @Test
    void testSortTurnOrder() {
        List<Unit> units = new ArrayList<>();
        Unit u1 = new Unit(2,1,false);
        Unit u2 = new Unit(4,1,true);

        Unit u3 = new Unit(1,2,true);
        Unit u4 = new Unit(3,2,false);
        Unit u5 = new Unit(4,2,true);

        Unit u6 = new Unit(2,3,false);
        Unit u7 = new Unit(4,3,true);

        units.add(u2);
        units.add(u3);
        units.add(u6);
        units.add(u5);
        units.add(u1);
        units.add(u7);
        units.add(u4);

        assertEquals(u2, units.get(0));
        assertEquals(u3, units.get(1));
        assertEquals(u6, units.get(2));
        assertEquals(u5, units.get(3));
        assertEquals(u1, units.get(4));
        assertEquals(u7, units.get(5));
        assertEquals(u4, units.get(6));
        Collections.sort(units);
        assertEquals(u1, units.get(0));
        assertEquals(u2, units.get(1));
        assertEquals(u3, units.get(2));
        assertEquals(u4, units.get(3));
        assertEquals(u5, units.get(4));
        assertEquals(u6, units.get(5));
        assertEquals(u7, units.get(6));
    }


    @Test
     void getFreeNodesNextToNode() {
        ArrayList<Coordinate> expectedFreeNodes = new ArrayList<>();
        expectedFreeNodes.add(new Coordinate(3,1));
        expectedFreeNodes.add(new Coordinate(2,2));
        expectedFreeNodes.add(new Coordinate(4,2));
        expectedFreeNodes.add(new Coordinate(3,3));

        String input =  "#######\n" +
                        "#.G.E.#\n" +
                        "#E.G.E#\n" +
                        "#.G.E.#\n" +
                        "#######";
        List<Unit> units = findAllUnits(input);
        HashSet<Coordinate> walls = mapWalls(input);

        assertEquals(expectedFreeNodes, findFreeNodesNextToNode(units, walls, new Coordinate(3,2)));
        assertEquals(new ArrayList<>(), findFreeNodesNextToNode(units, walls, new Coordinate(1,1)));
        assertEquals(new ArrayList<>(), findFreeNodesNextToNode(units, walls, new Coordinate(2,2)));
    }

    @Test
    void testIsNextToEnemy() {
        String input =  ".......\n" +
                        "..G.E..\n" +
                        ".......\n" +
                        "#E....#\n" +
                        ".......";
        List<Unit> units = findAllUnits(input);
        assertTrue(isNextToEnemy(units, new Coordinate(2,2),false));
        assertFalse(isNextToEnemy(units, new Coordinate(1,1),true));

        assertFalse(isNextToEnemy(units, new Coordinate(5,3),false));
        assertFalse(isNextToEnemy(units, new Coordinate(5,3),true));
    }
    @Test
    void testFindDistanceToEnemy() {
        String input =  "#######\n" +
                        "#G....#\n" +
                        "###EE.#\n" +
                        "#.....#\n" +
                        "#######";
        HashSet<Coordinate> walls = mapWalls(input);
        List<Unit> units = findAllUnits(input);

        assertEquals(new Coordinate(2,1), getFinalDestination(units, walls, new Unit(2,1, true)).getKey());
        assertEquals(new Coordinate(3,1), getFinalDestination(units, walls, new Unit(3,1, false)).getKey());
        assertEquals(new Coordinate(3,3), getFinalDestination(units, walls, new Unit(1,3, false)).getKey());
        assertEquals(new Coordinate(2,1), getFinalDestination(units, walls, new Unit(1,3, true)).getKey());
    }
    @Test
    void testMoveUnit() {
        String input =  "#########\n" +
                        "#E.....E#\n" +
                        "#.......#\n" +
                        "#E...G.E#\n" +
                        "#########";
        HashSet<Coordinate> walls = mapWalls(input);
        List<Unit> units = findAllUnits(input);

        Unit e1= new Unit(2,1,true);
        Unit e2= new Unit(6,1,true);
        Unit e3= new Unit(2,3,true);
        Unit e4= new Unit(7,3,true);
        Unit g1= new Unit(6,3,false);

        resolveRound(units, walls);

        assertTrue(units.contains(e1));
        assertTrue(units.contains(e2));
        assertTrue(units.contains(e3));
        assertTrue(units.contains(g1));
        assertTrue(units.contains(e4));
    }

    @Test
    void testFindTarget() {
        String input  = "#######\n" +
                        "#G....#\n" +
                        "#..G..#\n" +
                        "#..EG.#\n" +
                        "#..G..#\n" +
                        "#...G.#\n" +
                        "#######";
        List<Unit> units = findAllUnits(input);
        assertEquals(units.get(1),findTarget(units, units.get(2)));
        units.get(1).setHp(10);
        units.get(4).setHp(4);
        assertEquals(units.get(4),findTarget(units, units.get(2)));

        units.get(3).setHp(3);
        assertEquals(units.get(3),findTarget(units, units.get(2)));


    }


    @Test
    void testGetFinalDestination() {
        String input="#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        List<Unit> units = findAllUnits(input);
        HashSet<Coordinate> walls = mapWalls(input);
        assertEquals(new Coordinate(3,1), getFinalDestination(units, walls, units.get(0)).getKey());
        assertEquals(2, (int) getFinalDestination(units, walls, units.get(0)).getValue());
    }
    @Test
    void example_A() {
        String input  = "#######\n" +
                        "#.G...#\n" +
                        "#...EG#\n" +
                        "#.#.#G#\n" +
                        "#..G#E#\n" +
                        "#.....#\n" +
                        "#######";
        assertEquals(27730, solveA(input));
    }
    @Test
    void example_A1() {
        String input  = "#######\n" +
                        "#G..#E#\n" +
                        "#E#E.E#\n" +
                        "#G.##.#\n" +
                        "#...#E#\n" +
                        "#...E.#\n" +
                        "#######";
        assertEquals(36334, solveA(input));
    }
    @Test
    void example_A2() {
        String input  = "#######  \n" +
                        "#E..EG#\n" +
                        "#.#G.E#\n" +
                        "#E.##E#\n" +
                        "#G..#.#\n" +
                        "#..E#.#\n" +
                        "#######";
        assertEquals(39514, solveA(input));
    }
    @Test
    void example_A3() {
        String input  = "#######\n" +
                        "#E.G#.#\n" +
                        "#.#G..#\n" +
                        "#G.#.G#\n" +
                        "#G..#.#\n" +
                        "#...E.#\n" +
                        "#######";
        assertEquals(27755, solveA(input));
    }


    @Test
    void example_A4() {
        String input =  "#######\n" +
                        "#.E...#\n" +
                        "#.#..G#\n" +
                        "#.###.#\n" +
                        "#E#G#G#\n" +
                        "#...#G#\n" +
                        "#######";
        assertEquals(28944, solveA(input));
    }

    @Test
    void example_A5() {
        String input  = "#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########";
        assertEquals(18740, solveA(input));
    }
    @Test
    void example_B1() {
        String input  = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        assertEquals(4988, solveB(input));
    }
    @Test
    void example_B2() {
        String input  = "#######  \n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";
        assertEquals(31284, solveB(input));
    }
    @Test
    void example_B3() {
        String input  = "#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######";
        assertEquals(3478, solveB(input));
    }


    @Test
    void example_B4() {
        String input =  "#######\n" +
                        "#.E...#\n" +
                        "#.#..G#\n" +
                        "#.###.#\n" +
                        "#E#G#G#\n" +
                        "#...#G#\n" +
                        "#######";
        assertEquals(6474, solveB(input));
    }

    @Test
    void example_B5() {
        String input  = "#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########";
        assertEquals(1140, solveB(input));
    }
    @Test
    void example_Dov1() {
        String input ="#######\n" +
                      "#...G##\n" +
                      "#..GEE#\n" +
                      "#######";
        List<Unit> units = findAllUnits(input);
        HashSet<Coordinate> walls = mapWalls(input);
        units.get(0).setHp(3);
        units.get(1).setHp(3);
        units.get(2).setHp(3);
        units.get(3).setHp(3);
        resolveRound(units,walls);
        assertEquals(2, units.size());
        assertFalse(units.get(0).isElf);
        assertFalse(units.get(1).isElf);
    }
    @Test
    void example_Dov2() {
        String input ="######\n" +
                      "##E###\n" +  //this is due to the prio of the targets open spaces.
                      "#....#\n" +
                      "#.##.#\n" +
                      "#.#..#\n" +
                      "#..G.#\n" +
                      "######";
        List<Unit> units = findAllUnits(input);
        HashSet<Coordinate> walls = mapWalls(input);
        resolveRound(units,walls);

        assertEquals(new Unit(2,2,true),units.get(0));
        assertEquals(new Unit(2,5,false),units.get(1));
    }


    @Test
    void puzzle() {
        String day = "15";
        String input = new Utils().readFile("Day" + day + "Input.txt");
        System.out.println("Day" + day + "(a): " + solveA(input));
        System.out.println("Day" + day + "(b): " + solveB(input));
    }
}