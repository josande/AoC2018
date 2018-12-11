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

    static long getPowerLevel(int x, int y, int serialNumber) { //3,5 8
        long rackId = x+10L;
        long power = rackId*y+serialNumber;
        power*=rackId;
        return (power%1000/100-5);
    }

    static String solveB(String input) {
        int[][] powerGrid = new int[300][300];
        for (int x=0; x<300; x++) {
            for (int y=0; y< 300; y++) {
                powerGrid[x][y]=(int) getPowerLevel(x+1, y+1, Integer.valueOf(input));
            }
        }
        int maxPower=0;
        int maxSize=0;
        String coordinate="";
        for (int size=1; size <=300; size++) {
            Pair<String, Integer> result = getMaxPowerSquare(powerGrid, size);
            if  (result.getValue() > maxPower) {
                maxPower=result.getValue();
                coordinate=result.getKey();
                maxSize=size;
            }
        }
        return coordinate+","+maxSize;
    }
}
