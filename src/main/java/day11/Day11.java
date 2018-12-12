package day11;

import javafx.util.Pair;

class Day11 {
    static String solveA(String input) {
        int[][] powerGrid = new int[300][300];
        for (int x=0; x<300; x++) {
            for (int y=0; y< 300; y++) {
                powerGrid[x][y]=(int) getPowerLevel(x+1, y+1, Integer.valueOf(input));
            }
        }
        return getMaxPowerSquare(powerGrid, 3).getKey();
    }
    private static Pair<String, Integer> getMaxPowerSquare(int[][] powerGrid, int size) {
        int maxPower=0;
        int maxX=-1;
        int maxY=-1;
        for (int x=0; x < 300-size; x++) {
            for (int y = 0; y < 300-size; y++) {
                int sum = 0;
                for (int sx = x; sx<x+size; sx++ ){
                    for (int sy = y; sy<y+size; sy++ ) {
                        sum+=powerGrid[sx][sy];
                    }
                }
                if (sum>maxPower) {
                    maxPower=sum;
                    maxX=x+1;
                    maxY=y+1;
                }
            }
        }
        return new Pair<>(maxX+","+maxY, maxPower);

    }

    static long getPowerLevel(int x, int y, int serialNumber) {
        long rackId = x+10L;
        long power = rackId*y+serialNumber;
        power*=rackId;
        return (power%1000/100-5);
    }

    static String    solveB(String input) {
        int[][] powerGrid = new int[300][300];
        for (int x=0; x<300; x++) {
            for (int y=0; y< 300; y++) {
                powerGrid[x][y]=(int) getPowerLevel(x+1, y+1, Integer.valueOf(input));
            }
        }
        int maxPower=Integer.MIN_VALUE;
        int bestSize=0;
        int bestX=0, bestY=0;
        for (int x=0; x<300; x++) {
            for (int y = 0; y < 300; y++) {
                Pair<Integer, Integer> p =getMaxSizeAndPower(powerGrid, x , y);
                if (p.getValue()>maxPower) {
                    maxPower=p.getValue();
                    bestSize=p.getKey();
                    bestX=x+1;
                    bestY=y+1;
                }
            }
        }
        return bestX+","+bestY+","+bestSize;
    }
    private static Pair<Integer, Integer> getMaxSizeAndPower(int[][] grid, int x, int y) {
        int maxSize=300-Math.max(x,y);
        int currentMax=Integer.MIN_VALUE;
        int bestSize=Integer.MIN_VALUE;
        int currentSum=0;

        //Get right side and bottom
        for (int size=0; size<maxSize; size++) {
            for (int i=0; i<size;i++) {
                currentSum+=grid[x+i][y+size];
                currentSum+=grid[x+size][y+i];
            }
            //Get corner
            currentSum+=grid[x+size][y+size];

            if (currentSum>currentMax) {
                currentMax=currentSum;
                bestSize=size+1;
            }
        }
        return new Pair<>(bestSize, currentMax);
    }

}
