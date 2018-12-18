package day18;

import java.util.HashMap;

class Day18 {

    private static char[][] getMap(String input) {
        String[] rows =input.split("\r?\n");
        char[][] map = new char[rows.length][rows[0].length()];
        for (int r=0; r<rows.length;r++) {
            map[r]=rows[r].toCharArray();
        }
        return map;
    }
    private static char[][] updateMap(char[][] map) {
        char[][] newMap= cloneMap(map);
        int maxY=map.length-1;
        int maxX=map[0].length-1;
        for (int y=0; y<=maxY; y++) {
            for (int x = 0; x <=maxX; x++) {
                int lumberyard = 0;
                int trees = 0;
                if (x > 0 && y > 0) {
                    if (map[x - 1][y - 1] == '|') {
                        trees++;
                    }
                    if (map[x - 1][y - 1] == '#') {
                        lumberyard++;
                    }
                }
                if (y > 0) {
                    if (map[x][y - 1] == '|') {
                        trees++;
                    }
                    if (map[x][y - 1] == '#') {
                        lumberyard++;
                    }
                }
                if (y > 0 && x < maxX) {
                    if (map[x + 1][y - 1] == '|') {
                        trees++;
                    }
                    if (map[x + 1][y - 1] == '#') {
                        lumberyard++;
                    }
                }

                if (x > 0) {
                    if (map[x - 1][y] == '|') {
                        trees++;
                    }
                    if (map[x - 1][y] == '#') {
                        lumberyard++;
                    }
                }
                if (x < maxX) {
                    if (map[x + 1][y] == '|') {
                        trees++;
                    }
                    if (map[x + 1][y] == '#') {
                        lumberyard++;
                    }
                }

                if (x > 0 && y < maxY) {
                    if (map[x - 1][y + 1] == '|') {
                        trees++;
                    }
                    if (map[x - 1][y + 1] == '#') {
                        lumberyard++;
                    }
                }
                if (y < maxY) {
                    if (map[x][y + 1] == '|') {
                        trees++;
                    }
                    if (map[x][y + 1] == '#') {
                        lumberyard++;
                    }
                }
                if (y < maxY && x < maxX) {
                    if (map[x + 1][y + 1] == '|') {
                        trees++;
                    }
                    if (map[x + 1][y + 1] == '#') {
                        lumberyard++;
                    }
                }

                if (map[x][y] == '.' && trees >= 3)
                    newMap[x][y] = '|';
                if (map[x][y] == '|' && lumberyard >= 3)
                    newMap[x][y] = '#';
                if (map[x][y] == '#') {
                    if (lumberyard >= 1 && trees >= 1)
                        newMap[x][y] = '#';
                    else
                        newMap[x][y] = '.';
                }
            }
        }
        return newMap;
    }
    private static char[][] cloneMap (char[][] map) {
        char [][] newMap = new char[map.length][];
        for(int i = 0; i < map.length; i++)
            newMap[i] = map[i].clone();
        return newMap;
    }
    private static void print (char[][] map) {
        for (char[] aMap : map) {
            System.out.println(aMap);
        }
    }
    static int solveA(String input) {
        char[][] map = getMap(input);
        return solve(map, 10);
    }
    private static int solve(char[][] map, int iterations) {
        for (int i=0; i<iterations;i++) {
            map = updateMap(map);
        }

        int value=findValue(map);
        return value;
    }
    private static int findValue(char[][] map) {
        int lumberyard=0;
        int trees=0;
        int maxY=map.length-1;
        int maxX=map[0].length-1;
        for (int y=0; y<=maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                if (map[y][x]=='|')
                    trees++;
                if (map[y][x]=='#')
                    lumberyard++;
            }
        }
        return lumberyard * trees;
    }

    static int solveB(String input) {
        char[][] map = getMap(input);
        HashMap<Integer, Integer> patterns = new HashMap<>();

        int[] cycleLengths={0,0,0};
        int i=0;
        for (;;i++) {
            map = updateMap(map);
            int value = findValue(map);
            if (patterns.containsKey(value)) {
                cycleLengths[0]=cycleLengths[1];
                cycleLengths[1]=cycleLengths[2];
                cycleLengths[2]=i-patterns.get(value);
                if (cycleLengths[0] == cycleLengths[1] && cycleLengths[1] == cycleLengths[2]) {
                    break;
                }
            }
            patterns.put(value, i);
        }
        for (int j=0;j<cycleLengths[0];j++) {
            if ((1000000000 -i -j ) % cycleLengths[0] == 0) {
                char[][] map2 = getMap(input);
                return solve(map2, i+j);
            }
        }

        return -1;
    }
}
