package day22;

import utils.Utils;

import utils.Utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Day22 {
  /*  public static class CaveCoordinate extends Coordinate {
        long geologicIndex = -1;

        public CaveCoordinate(int x, int y) {
            super(x, y);
            geologicIndex = calculateGeologicIndex(this);
            caveCoordinates.add(this);
        }
        public void setGeologicIndex(int geologicIndex) {
            this.geologicIndex=geologicIndex;
        }
        public long getGeologicIndex() {
            return geologicIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof CaveCoordinate))return false;

            Coordinate other = (Coordinate) obj;
            return ( this.getX()==other.getX() &&
                    this.getY()==other.getY());
        }
    }
*/
    static private long calculateGeologicIndex(Coordinate coordinate) {
        if (coordinate.getX() == 0 && coordinate.getY() == 0)
            return 0;
        if (coordinate.equals(targetCoordinate))
            return 0;
        if (coordinate.getY() == 0)
            return coordinate.getX() * 16807;
        if (coordinate.getX() == 0)
            return coordinate.getY() * 48271;

        return getErosionLevel(coordinate.getUp())
             * getErosionLevel(coordinate.getLeft());
    }
    static long getErosionLevel(Coordinate coordinate) {
        if (erosionMap.containsKey(coordinate))
            return erosionMap.get(coordinate);
        long erosion=(calculateGeologicIndex(coordinate) + caveDepth) % 20183;
        erosionMap.put(coordinate, erosion);
        return erosion;
    }
    static int getRisk(Coordinate coordinate) {
        return (int) getErosionLevel(coordinate)%3;
    }

    static Coordinate targetCoordinate;
    static int caveDepth;
    static HashMap<Coordinate, Long> erosionMap;

    static int solveA(String input) {
        erosionMap=new HashMap<>();
        caveDepth = Integer.valueOf(input.split(("\r?\n"))[0].split(": ")[1]);
        targetCoordinate = new Coordinate(input.split(": ")[2]);
        int totalRisk=0;
        for (int y=0; y<=targetCoordinate.getY(); y++) {
            for (int x=0;x<=targetCoordinate.getX();x++) {
                Coordinate c = new Coordinate(x,y);
                long erosion=getErosionLevel(c);
                totalRisk+=getRisk(c);
            }
        }
        return totalRisk;
    }

    static int solveB(String input) {
        erosionMap=new HashMap<>();
        caveDepth = Integer.valueOf(input.split(("\r?\n"))[0].split(": ")[1]);
        targetCoordinate = new Coordinate(input.split(": ")[2]);
        State startState = new State(new Coordinate(0,0), Equipment.Torch);
        State endState =   new State(targetCoordinate, Equipment.Torch);

        return fastestPath(startState , endState);
    }

    static enum Equipment {
        Nothing,
        ClimbingGear,
        Torch;
    }

    static enum Action {
        equipNothing(7),
        equipClimbingGear(7),
        equipTorch(7),
        moveUp(1),
        moveDown(1),
        moveLeft(1),
        moveRight(1);
        int time;
        Action(int time) {
            this.time=time;
        }
        int getTime() {return time;}
    }
    static class State {
        Coordinate coordinate;
        Equipment equipment;
        State(Coordinate coordinate, Equipment equipment) {
            this.coordinate=coordinate;
            this.equipment=equipment;
        }
        List<Action> getPossibleActions() {
            List<Action>  possibleActions = new ArrayList<>();

            if (isLegalCombo(coordinate.getUp(), equipment))
                possibleActions.add(Action.moveUp);
            if (isLegalCombo(coordinate.getDown(), equipment))
                possibleActions.add(Action.moveDown);
            if (isLegalCombo(coordinate.getLeft(), equipment))
                possibleActions.add(Action.moveLeft);
            if (isLegalCombo(coordinate.getRight(), equipment))
                possibleActions.add(Action.moveRight);
            if (isLegalCombo(coordinate, Equipment.Nothing)
                    && !equipment.equals(Equipment.Nothing))
                possibleActions.add(Action.equipNothing);
            if (isLegalCombo(coordinate, Equipment.ClimbingGear)
                    && !equipment.equals(Equipment.ClimbingGear))
                possibleActions.add(Action.equipClimbingGear);
            if (isLegalCombo(coordinate, Equipment.Torch)
                    && !equipment.equals(Equipment.Torch))
                possibleActions.add(Action.equipTorch);

            return possibleActions;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof State))return false;

            State other = (State) obj;
            return this.getCoordinate().equals(other.getCoordinate()) &&
                   this.getEquipment().equals(other.getEquipment());
        }
        @Override
        public int hashCode() {
            int result = 3111 * getCoordinate().getY()+101*getCoordinate().getX() + getEquipment().ordinal();
            return result;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public Equipment getEquipment() {
            return equipment;
        }
    }
    static int fastestPath(State startState, State endState) {
        State startLocation = startState;

        HashMap<State, Integer> queue = new HashMap<>();
        HashMap<State, Integer> scanned = new HashMap<>();
        queue.put(startLocation, 0);

        int fastestKnownTime=2000;
        int iteration=0;
        while (!queue.isEmpty()){
            iteration++;
           // System.out.println(iteration+" Current queue:"+queue.size()+" Fastest time: "+fastestKnownTime);

            HashMap<State, Integer> newToAdd  = new HashMap<>();
            HashSet<State> toBeRemoved = new HashSet<>();
            for (State current : queue.keySet() ) {
                int elapsedTime = queue.get(current);
                int scannedTime=scanned.getOrDefault(current,Integer.MAX_VALUE);
             //   System.out.println(iteration+" "+current.getCoordinate()+" "+current.getEquipment()+" "+elapsedTime);

                if (current.equals(endState)) {
                    System.out.println("Found the friend! "+iteration+" "+elapsedTime);
                    fastestKnownTime = Math.min(fastestKnownTime, elapsedTime);
                }
                if (scannedTime <= elapsedTime)
                    continue;

                for (Action action : current.getPossibleActions()) {
                    State futureState=getNextState(current, action);
                    newToAdd.put(futureState, Math.min(queue.getOrDefault(futureState,Integer.MAX_VALUE), elapsedTime + action.getTime()));
                }
                scanned.put(current, elapsedTime);
            }


            for (State nextState : newToAdd.keySet() ) {
                if (scanned.getOrDefault(nextState,Integer.MAX_VALUE) >= newToAdd.get(nextState))
                    queue.put(nextState, newToAdd.get(nextState));
            }
            for (State current : queue.keySet()) {
                int elapsedTime = queue.get(current);
                if (elapsedTime >= fastestKnownTime)
                    toBeRemoved.add(current);
                if (scanned.getOrDefault(current,Integer.MAX_VALUE)<=elapsedTime)
                    toBeRemoved.add(current);
            }
            for (State tbr : toBeRemoved ) {
                queue.remove(tbr);
            }
        }
        System.out.println("Scanned: "+scanned.size());

        return fastestKnownTime;
    }
    static State getNextState(State current, Action action) {
        switch (action) {
            case moveUp: return new State(current.getCoordinate().getUp(), current.getEquipment());
            case moveDown: return new State(current.getCoordinate().getDown(), current.getEquipment());
            case moveLeft: return new State(current.getCoordinate().getLeft(), current.getEquipment());
            case moveRight: return new State(current.getCoordinate().getRight(), current.getEquipment());
            case equipNothing: return new State(current.getCoordinate(), Equipment.Nothing);
            case equipClimbingGear: return new State(current.getCoordinate(), Equipment.ClimbingGear);
            case equipTorch: return new State(current.getCoordinate(), Equipment.Torch);
            default: return null;
        }
    }
    /*
    Regions:
    0 Rocky
    1 Wet
    2 Narrow

    Tools:
    None: 0
    Climbing gear: 1
    Torch: 2

    Combinations:
    Rocky: 1,2
    Wet: 0,1
    Narrow:  0,2
    */

    static boolean isLegalCombo(Coordinate c, Equipment e) {
        if (c.getX()<0 || c.getY()<0) return false;
        if (getRisk(c)==0) return e == Equipment.ClimbingGear || e == Equipment.Torch;
        if (getRisk(c)==1) return e == Equipment.Nothing || e == Equipment.ClimbingGear;
        if (getRisk(c)==2) return e == Equipment.Nothing || e == Equipment.Torch;
        return false;
    }

}
