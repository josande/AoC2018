package day15;


import javafx.util.Pair;

import java.util.*;

class Day15 {

    static class Unit implements Comparable<Unit> {
        int x, y;
        int hp=200;
        int attackPower;
        boolean isElf;
        Unit(int x, int y, boolean isElf) {
            this.x=x;
            this.y=y;
            this.attackPower=3;
            this.isElf = isElf;
        }

        Unit(int x, int y, int attackPower, boolean isElf) {
            this.x=x;
            this.y=y;
            this.attackPower=attackPower;
            this.isElf = isElf;
        }
        int getX() {return x;}
        int getY() {return y;}
        int getHP() {return hp;}
        int getAttackPower() {return attackPower;}
        void takeDamage(int damage) {
            hp-=damage;
        }
        void setHp(int hp) {
            this.hp=hp;
        }
        Pair<Integer, Integer> getCoordinate() {return new Pair<>(x,y);}
        void setCoordinate(Pair<Integer, Integer> coordinate) {
            x=coordinate.getKey();
            y=coordinate.getValue();
        }
        @Override
        public int compareTo(Unit other){
            if (this.getY() == other.getY()) return this.getX()-other.getX();
            return  this.getY() - other.getY();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            if (!(other instanceof Unit))return false;

            Unit otherUnit = (Unit)other;
            return (x==otherUnit.getX() && y==otherUnit.getY() && isElf==otherUnit.isElf);
        }
    }

    static HashSet<Pair<Integer, Integer>> mapWalls(String input) {
        String[] rows = input.split("\r?\n");
        HashSet<Pair<Integer, Integer>> walls = new HashSet<>();
        for(int y = 0; y < rows.length; y++) {
            for(int x = 0; x < rows[y].length(); x++) {
                if(rows[y].charAt(x) == '#') {
                    walls.add(new Pair<>(x,y));
                }
            }
        }
        return walls;
    }
    static List<Unit> findAllUnits(String input) {
        return findAllUnits(input, 3);
    }

    static List<Unit> findAllUnits(String input, int attackPower) {
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
    private static ArrayList<Pair<Integer, Integer>> getNeighbours (Pair<Integer, Integer> coordinate) {
            ArrayList<Pair<Integer, Integer>> neighbouringNodes = new ArrayList<>();
            Pair<Integer, Integer> west  = new Pair<>(coordinate.getKey()-1, coordinate.getValue());
            Pair<Integer, Integer> north = new Pair<>(coordinate.getKey(),   coordinate.getValue()-1);
            Pair<Integer, Integer> east  = new Pair<>(coordinate.getKey()+1, coordinate.getValue());
            Pair<Integer, Integer> south = new Pair<>(coordinate.getKey(),   coordinate.getValue()+1);
            neighbouringNodes.add(north);
            neighbouringNodes.add(west);
            neighbouringNodes.add(east);
            neighbouringNodes.add(south);
        return neighbouringNodes;
    }
    private static ArrayList<Pair<Integer, Integer>> findFreeNodesNextToNode(List<Unit> units, HashSet<Pair<Integer, Integer>> walls, Pair<Integer, Integer> coordinate) {
        ArrayList<Pair<Integer, Integer>> neighbours = getNeighbours(coordinate);
        ArrayList<Pair<Integer, Integer>> freeNodes = new ArrayList<>();
        for (Pair<Integer, Integer> node : neighbours) {
         //   System.out.println(node+" isFree " + isFree(units, walls, node));
            if (isFree(units, walls, node)) {
                freeNodes.add(node);
            }
        }
        return freeNodes;
    }
    private static boolean isFree(List<Unit> units, HashSet<Pair<Integer, Integer>> walls, Pair<Integer, Integer> coordinate) {
        if (walls.contains(coordinate))
            return false;
        for (Unit unit: units) {
            if (unit.getCoordinate().equals(coordinate)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isNextToEnemy(List<Unit> units, Pair<Integer, Integer> location, boolean lookingForElves) {
        List<Pair<Integer, Integer>> neighbouringNodes = getNeighbours(location);

        for (Unit otherUnit : units) {
            if (lookingForElves==otherUnit.isElf && neighbouringNodes.contains(otherUnit.getCoordinate())) {
                return true;
            }
        }
        return false;
    }

    static boolean resolveRound(List<Unit> units, HashSet<Pair<Integer, Integer>> walls) {
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
    static void print(List<Unit> units, HashSet<Pair<Integer, Integer>> walls) {
        for (int y=0; y<32;y++) {
            for (int x=0; x<32;x++) {
                Pair<Integer, Integer> p = new Pair<>(x,y);
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
        System.out.print("\n");

        System.out.print("\n");

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
    private static Unit findTarget(List<Unit>units, Unit unit) {
        List<Pair<Integer, Integer>> neighbouringNodes = getNeighbours(unit.getCoordinate());

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

    static void moveUnit(List<Unit> units, HashSet<Pair<Integer, Integer>> walls, Unit unit) {
        if (isNextToEnemy(units, unit.getCoordinate(), !unit.isElf)) {
            return;
        }
        Pair<Integer, Integer> bestCoordinate=null;
        int shortestWalk=Integer.MAX_VALUE;
        int sidePriority=4;
        for (Pair<Integer,Integer> direction : findFreeNodesNextToNode(units, walls,unit.getCoordinate())) { //TODO HANDLE SO THAT THIS CHOOSES RIGHT SIDE OF ENEMY
            Pair<Integer, Integer> result = getDistanceToEnemy(units, walls, direction, !unit.isElf);
            if (result.getKey() < shortestWalk) {
                shortestWalk = result.getKey();
                bestCoordinate = direction;
                sidePriority=result.getValue();
           //     System.out.println("New shortest distance ("+result.getKey()+")! Before: "+bestCoordinate+", New: "+direction+". SidePriority before: "+sidePriority+" Current: "+result.getValue());

            } else if (result.getKey() == shortestWalk) {
             //   System.out.println("Same distance("+result.getKey()+")! Before: "+bestCoordinate+", New: "+direction+". SidePriority before: "+sidePriority+" Current: "+result.getValue());
                if (result.getValue() < sidePriority) {
                    shortestWalk = result.getKey();
                    bestCoordinate = direction;
                    sidePriority=result.getValue();
                }
            }
        }
        if (bestCoordinate!=null)
            unit.setCoordinate(bestCoordinate);
    }
    static Pair<Integer, Integer> getDistanceToEnemy(List<Unit> units, HashSet<Pair<Integer, Integer>> walls, Pair<Integer,Integer> coordinate, boolean lookingForElves) {

        //TODO Rewrite
        //Loop for all enemies
          // Loop for all side of enemy
            // If best distance is tied
              //Choose enemy in order
                //choose side of that enemy in order

        HashSet<Pair<Integer, Integer>> scanned = new HashSet<>();
        HashSet<Pair<Integer, Integer>> queue = new HashSet<>();
        HashSet<Pair<Integer, Integer>> results = new HashSet<>();
        queue.add(coordinate);

        for (int i=0; ; i++) {
            HashSet<Pair<Integer, Integer>> newToAdd  = new HashSet<>();
            for (Pair<Integer, Integer> location : queue) {
                if (scanned.contains(location))
                    continue;
                if (isNextToEnemy(units, location, lookingForElves)) {
                    results.add(location);
                }
                scanned.add(location);
                newToAdd.addAll(findFreeNodesNextToNode(units, walls, location));
            }
            if (!results.isEmpty()) {
                int directionValue=4;
                for (Pair<Integer, Integer> result : results) {
                    directionValue = Math.min(directionValue, getDirectionalValue(units, lookingForElves, result));
                }
                return new Pair<>(i, directionValue);
            }
            queue.addAll(newToAdd);
            queue.removeAll(scanned);

            if (queue.isEmpty()) return new Pair<>(Integer.MAX_VALUE, 4);
        }
    }
    private static int getDirectionalValue(List<Unit> units,  boolean lookingForElves, Pair<Integer,Integer> coordinate) {
        ArrayList<Pair<Integer, Integer>> neighbours = getNeighbours(coordinate);
        int directionalValue=4;
        for (int i=3; i>=0; i--) {
            for (Unit unit : units) {
                if (lookingForElves== unit.isElf && unit.getCoordinate().equals(neighbours.get(i))) {
                    directionalValue=i;
                }
            }
        }
        return directionalValue;
    }
    private static int getScore(List<Unit> units) {
        int score=0;
        for (Unit unit : units) {
            score+=unit.getHP();
        }
        return score;
    }

    static int solveA(String input) {
        HashSet<Pair<Integer, Integer>> walls = mapWalls(input);
        List<Unit> units = findAllUnits(input,3);
        print(units, walls);
        for (int i=0;  ; i++) {
            boolean gameOver = resolveRound(units,walls);
            if (gameOver) {
                return (i)*getScore(units);
            }
        }
    }
    private static Pair<Boolean, Integer> solveWithAp(String input, int ap) {
        HashSet<Pair<Integer, Integer>> walls = mapWalls(input);
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
                System.out.println("Iterations: "+i+" Score: "+getScore(units)+" Ap:"+ap);
               // print(units,walls);
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
