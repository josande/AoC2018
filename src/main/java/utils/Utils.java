package utils;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

public class Utils {
    public String readFile(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static char[][] cloneArray(char[][] src) {
        int length = src.length;
        char[][] target = new char[length][src[0].length];
        IntStream.range(0, length).forEach(i -> System.arraycopy(src[i], 0, target[i], 0, src[i].length));
        return target;
    }
    public static class Coordinate implements Comparable<Coordinate> {
        int x,y;
        public Coordinate (int x, int y) {
            this.x=x;
            this.y=y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        public Coordinate getUp() {
            return new Coordinate(x,y-1);
        }
        public Coordinate getDown() {
            return new Coordinate(x,y+1);
        }
        public Coordinate getLeft() {
            return new Coordinate(x-1,y);
        }
        public Coordinate getRight() {
            return new Coordinate(x+1,y);
        }
        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
        @Override
        public int compareTo(Coordinate other){
            if (this.getY() == other.getY()) return this.getX()-other.getX();
            return this.getY() - other.getY();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof Coordinate))return false;

            Coordinate other = (Coordinate) obj;
            return ( this.getX()==other.getX() &&
                     this.getY()==other.getY());
        }
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + this.getY()+47*this.getY();
            return hash;
        }
    }
}


