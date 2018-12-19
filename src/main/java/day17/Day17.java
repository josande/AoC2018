package day17;

import utils.Utils;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

class Day17 {

    static char[][] makeMap(String input) {
        HashSet<Point> walls = new HashSet<>();

        for (String string : input.split("\r?\n")) {
            if (string.startsWith("x=")) {
                int x=Integer.valueOf(string.split(", ")[0].split("=")[1]);
                int yMin=Integer.valueOf(string.split("=")[2].split("\\.\\.")[0]);
                int yMax=Integer.valueOf(string.split("=")[2].split("\\.\\.")[1]);
                for (int y=yMin; y<=yMax; y++) {
                    walls.add(new Point(x,y));
                }
            } else if (string.startsWith("y=")) {
                int y=Integer.valueOf(string.split(", ")[0].split("=")[1]);
                int xMin=Integer.valueOf(string.split("=")[2].split("\\.\\.")[0]);
                int xMax=Integer.valueOf(string.split("=")[2].split("\\.\\.")[1]);
                for (int x=xMin; x<=xMax; x++) {
                    walls.add(new Point(x,y));
                }
            }
        }
        Integer minY= (int) walls.stream().mapToDouble(Point::getY).min().getAsDouble();
        Integer maxY= (int) walls.stream().mapToDouble(Point::getY).max().getAsDouble();
        Integer minX= (int) walls.stream().mapToDouble(Point::getX).min().getAsDouble();
        Integer maxX= (int) walls.stream().mapToDouble(Point::getX).max().getAsDouble();
        int xRange=maxX-minX+3;
        char[][] map = new char[maxY+1][xRange+2];

        for (int y=0; y<=maxY; y++) {
            for (int x=0;x<xRange+1; x++) {
                if (walls.contains(new Point(x+minX-1,y))) {
                    map[y][x+1]='#';
                } else {
                    map[y][x+1]='.';
                }
            }
        }
        map[0][500-minX+2] = '+';
        return map;
    }
    static void print(char[][] map) {
        for (int y=0;y<map.length; y++) {
            for (int x=0; x<map[y].length; x++) {
                System.out.print(map[y][x]);
            }
            System.out.print("\n");
        }
    }
    static char[][] updateMap(char[][] map) {
        char[][] newMap = Arrays.copyOf(map, map.length);
        for (int y = 0; y < newMap.length-1; y++) {
            for (int x = 0; x < newMap[y].length-1; x++) {
                if (newMap[y][x] == '|' || newMap[y][x] == '+') {
                    if (newMap[y + 1][x] == '.') {
                        newMap[y + 1][x] = '|';
                    } else if (newMap[y + 1][x] == '~' || newMap[y + 1][x] == '#') {
                        if (newMap[y][x + 1] == '.') {
                            newMap[y][x + 1] = '|';
                        }
                        if (newMap[y][x - 1] == '.') {
                            newMap[y][x - 1] = '|';
                        }
                        if (newMap[y][x - 1] == '#') {
                            int w = 0;
                            while (newMap[y][x + w] == '|') {
                                w++;
                            }
                            if (newMap[y][x + w] == '#') {
                                for (int e = 0; e < w; e++) {
                                    newMap[y][x + e] = '~';
                                }
                            }
                        }
                    }
                }
            }
        }
        return newMap;
    }
    static int solveA(String input) {
        char[][] map = makeMap(input);
//        System.out.println("start:");
      //  print(map);
    //    System.out.println("Add water:");

        char[][] mapBefore = Utils.cloneArray(map);
        map=updateMap(map);
        while(!Arrays.deepEquals(map, mapBefore)) {
            mapBefore = Utils.cloneArray(map);
            map = updateMap(map);
        }
      //  print(map);
        int water = countWater(map);
        return water;
    }
    static int countWater(char[][] map) {
        int water=0;
        int minY=-1;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (minY<0 && map[y][x] == '#')
                    minY=y;
            }
        }

        for (int y = minY; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '|' || map[y][x]=='~')
                    water++;
            }
        }
        return water;
    }
    static int countStillWater(char[][] map) {
        int water=0;
        int minY=-1;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (minY<0 && map[y][x] == '#')
                    minY=y;
            }
        }

        for (int y = minY; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x]=='~')
                    water++;
            }
        }
        return water;
    }

    static int solveB(String input) {
        char[][] map = makeMap(input);
//        System.out.println("start:");
        //      print(map);
        //    System.out.println("Add water:");

        char[][] mapBefore = Utils.cloneArray(map);
        map=updateMap(map);;
        while(!Arrays.deepEquals(map, mapBefore)) {
            mapBefore = Utils.cloneArray(map);
            map=updateMap(map);
        }
        //  print(map);
        int stillWater = countStillWater(map);

        return stillWater;

    }
}
