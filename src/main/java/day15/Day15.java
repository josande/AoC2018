package day15;


import javafx.util.Pair;
import utils.Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class Day15 {

    static class Unit implements Comparable<Unit> {
        Coordinate coordinate;
        int hp=200;
        int attackPower;
        boolean isElf;
        Unit(int x, int y, boolean isElf) {
            this.coordinate=new Coordinate(x,y);
            this.attackPower=3;
            this.isElf = isElf;
        }
        Unit(int x, int y, int attackPower, boolean isElf) {
            this.coordinate=new Coordinate(x,y);
            this.attackPower=attackPower;
            this.isElf = isElf;
        }
        int getAttackPower() {return attackPower;}
        void takeDamage(int damage) {
            hp-=damage;
        }
        int getHP() {return hp;}
        void setHp(int hp) {
            this.hp=hp;
        }
        Coordinate getCoordinate() {return coordinate;}
        void setCoordinate(Coordinate coordinate) { this.coordinate=coordinate; }

        @Override
        public int compareTo(Unit other){
            return  this.getCoordinate().compareTo(other.getCoordinate());
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            if (!(other instanceof Unit))return false;

            Unit otherUnit = (Unit)other;
            return (this.getCoordinate().equals(otherUnit.getCoordinate()) && isElf==otherUnit.isElf);
        }
    }

    static HashSet<Coordinate> mapWalls(String input) {
        String[] rows = input.split("\r?\n");
        HashSet<Coordinate> walls = new HashSet<>();
        for(int y = 0; y < rows.length; y++) {
            for(int x = 0; x < rows[y].length(); x++) {
                if(rows[y].charAt(x) == '#') {
                    walls.add(new Coordinate(x,y));
                }
            }
        }
        return walls;
    }
    static List<Unit> findAllUnits(String input) {
        return findAllUnits(input, 3);
    }

    private static List<Unit> findAllUnits(String input, int attackPower) {
        String[] rows = input.split("\r?\n");
        List<Unit> units = new ArrayList<>();
        for(int y = 0; y < rows.length; y++) {
            for(int x = 0; x < rows[y].length(); x++) {
                if(rows[y].charAt(x) == 'E') {
                    units.add(new Unit(x,y,attackPower,true));
                }
                if(rows[y].charAt(x) == 'G') {
                    units.add(new Unit(x,y,3,false));
                }
            }
        }
        return units;
    }
    private static ArrayList<Coordinate> getNeighbours (Coordinate coordinate) {
            ArrayList<Coordinate> neighbouringNodes = new ArrayList<>();
            neighbouringNodes.add(coordinate.getUp());
            neighbouringNodes.add(coordinate.getLeft());
            neighbouringNodes.add(coordinate.getRight());
            neighbouringNodes.add(coordinate.getDown());
        return neighbouringNodes;
    }
    static ArrayList<Coordinate> findFreeNodesNextToNode(List<Unit> units, HashSet<Coordinate> walls, Coordinate coordinate) {
        ArrayList<Coordinate> neighbours = getNeighbours(coordinate);
        ArrayList<Coordinate> freeNodes = new ArrayList<>();
        for (Coordinate node : neighbours) {
            if (isFree(units, walls, node)) {
                freeNodes.add(node);
            }
        }
        return freeNodes;
    }
    private static boolean isFree(List<Unit> units, HashSet<Coordinate> walls, Coordinate coordinate) {
        if (walls.contains(coordinate))
            return false;
        for (Unit unit: units) {
            if (unit.getCoordinate().equals(coordinate)) {
                return false;
            }
        }
        return true;
    }
    static boolean isNextToEnemy(List<Unit> units, Coordinate location, boolean lookingForElves) {
        List<Coordinate> neighbouringNodes = getNeighbours(location);
        for (Unit otherUnit : units) {
            if (lookingForElves==otherUnit.isElf && neighbouringNodes.contains(otherUnit.getCoordinate())) {
                return true;
            }
        }
        return false;
    }

    static boolean resolveRound(List<Unit> units, HashSet<Coordinate> walls) {
        Collections.sort(units);
        List<Unit> copyOfUnits = new ArrayList<>(units);
        boolean isGameOver=true;
        for(Unit unit : copyOfUnits) {
            if (unit.getHP()<=0)
                continue;
            isGameOver=isGameOver(units);
            moveUnit(units, walls, unit);
            attack(units, unit);
        }

        return isGameOver;
    }
    static void print(List<Unit> units, HashSet<Coordinate> walls) {
        for (int y=0; y<32;y++) {
            for (int x=0; x<32;x++) {
                Coordinate p = new Coordinate(x,y);
                boolean unitHere=false;
                if (walls.contains(p))
                    System.out.print("#");
                else {
                    for (Unit u : units) {
                        if (u.getCoordinate().equals(p)) {
                            System.out.print(u.isElf?"E":"G");
                            unitHere=true;
                        }
                    }
                    if (!unitHere) {
                        System.out.print(".");
                    }
                }
            }
            System.out.print("\n");
        }
        Collections.sort(units);
        for (Unit u : units) {
            System.out.print((u.isElf?"E":"G")+" "+u.getHP()+", ");
        }
        System.out.print("\n\n");
    }
    private static boolean isGameOver(List<Unit> units) {
        boolean isElvesLeft=false;
        boolean isGoblinsLeft=false;
        for (Unit unit: units) {
            if (unit.isElf ) {
                isElvesLeft=true;
            } else {
                isGoblinsLeft=true;
            }
        }
        return !isElvesLeft || !isGoblinsLeft;

    }
    private static void attack(List<Unit>units, Unit unit) {
        Unit target = findTarget(units, unit);
        if (target!=null) {
            target.takeDamage(unit.getAttackPower());
            if (target.getHP()<=0) {
                units.remove(target);
            }
        }
    }
    static Unit findTarget(List<Unit>units, Unit unit) {
        List<Coordinate> neighbouringNodes = getNeighbours(unit.getCoordinate());

        int lowestHpSoFar=Integer.MAX_VALUE;
        Unit target=null;

        for (Unit otherUnit : units) {
            if (unit.isElf!=otherUnit.isElf
               && neighbouringNodes.contains(otherUnit.getCoordinate())
               && otherUnit.getHP() < lowestHpSoFar
               && otherUnit.getHP() > 0) {
                lowestHpSoFar=otherUnit.getHP();
                target = otherUnit;
            }
        }
        return target;
    }

    private static void moveUnit(List<Unit> units, HashSet<Coordinate> walls, Unit unit) {
        if (isNextToEnemy(units, unit.getCoordinate(), !unit.isElf)) {
            return;
        }
        Pair<Coordinate,Integer> finalDestination = getFinalDestination(units, walls, unit);

        if (finalDestination == null) return;
        for (Coordinate direction : findFreeNodesNextToNode(units, walls,unit.getCoordinate())) { //TODO See if the initial plan with 'walk out from unit' works if we hook on criteria to sort all matches at closest distance.
            int currentDistance = getDistance(direction, finalDestination.getKey(), units, walls);
            if (currentDistance==finalDestination.getValue()-1) {
                unit.setCoordinate(direction);
                return;
            }
        }
    }
    static Pair<Coordinate, Integer> getFinalDestination(List<Unit> units, HashSet<Coordinate> walls, Unit unit) {
        List<Unit> enemies = units.stream().filter(u->(u.isElf != unit.isElf)).collect(Collectors.toList());
        int shortestDistance=Integer.MAX_VALUE;
        List<Coordinate> closestCoordinates=new ArrayList<>();
        Coordinate start = unit.getCoordinate();
        for (Unit enemy : enemies ) {
            for ( Coordinate coordinate : findFreeNodesNextToNode(units, walls, enemy.getCoordinate())) {
                int distance=getDistance(start, coordinate, units, walls);
                if ( distance >= 0 ) {
                    if (distance < shortestDistance) {
                        shortestDistance=distance;
                        closestCoordinates=new ArrayList<>();
                        closestCoordinates.add(coordinate);
                    } if (distance == shortestDistance) {
                        closestCoordinates.add(coordinate);
                    }
                }
            }
        }

        if (closestCoordinates.isEmpty()) {
            return null;
        }
        Collections.sort(closestCoordinates);
        return new Pair<>(closestCoordinates.get(0), shortestDistance);
    }

    private static int getDistance(Coordinate start, Coordinate end, List<Unit> units, HashSet<Coordinate> walls) {
        HashSet<Coordinate> scanned = new HashSet<>();
        HashSet<Coordinate> queue = new HashSet<>();
        queue.add(start);
        for (int steps=0; ; steps++) {
            HashSet<Coordinate> newToAdd  = new HashSet<>();
            for (Coordinate current : queue) {
                if (scanned.contains(current))
                    continue;
                if (current.equals(end)) {
                    return steps;
                }
                scanned.add(current);
                newToAdd.addAll(findFreeNodesNextToNode(units, walls, current));
            }
            queue.addAll(newToAdd);
            queue.removeAll(scanned);

            if (queue.isEmpty())
                return -1;
        }
    }

    private static int getScore(List<Unit> units) {
        int score=0;
        for (Unit unit : units) {
            score+=unit.getHP();
        }
        return score;
    }

    static int solveA(String input) {
        HashSet<Coordinate> walls = mapWalls(input);
        List<Unit> units = findAllUnits(input,3);
        for (int i=0;  ; i++) {
            boolean gameOver = resolveRound(units,walls);
            if (gameOver) {
                return (i)*getScore(units);
            }
        }
    }
    private static Pair<Boolean, Integer> solveWithAp(String input, int ap) {
        HashSet<Coordinate> walls = mapWalls(input);
        List<Unit> units = findAllUnits(input,ap);
        int startElves=0;
        for (Unit u : units) {
            if (u.isElf)
                startElves++;
        }
        for (int i=0;  ; i++) {
            boolean gameOver = resolveRound(units,walls);
            int elvesAlive= 0;
            for (Unit u : units) {
                if (u.isElf && u.getHP()>0) {
                    elvesAlive++;
                }

            }
            if (elvesAlive < startElves) {
                return new Pair<>(false, -1);
            }

            if (gameOver) {
                return new Pair<>(true, i*getScore(units));
            }
        }
    }

    static int solveB(String input) {
        for (int ap=3;;ap++) {
            Pair<Boolean, Integer> result = solveWithAp(input, ap);
            if (result.getKey()) {
                return result.getValue();
            }
        }
    }
}
