package day06;

import org.junit.jupiter.api.Test;

import static day06.Day06.largestFiniteArea;
import static day06.Day06.regionWithTotalDistanceLessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    public void exampleOne() {
        String input = "1, 1\n" +
                        "1, 6\n" +
                        "8, 3\n" +
                        "3, 4\n" +
                        "5, 5\n" +
                        "8, 9";

        assertEquals(17, largestFiniteArea(input));
    }
    @Test
    public void exampleTwo() {
        String input = "1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9";

        assertEquals(16, regionWithTotalDistanceLessThan(input,32));
    }
    @Test
    void puzzle() {
        String input="152, 292\n" +
                "163, 90\n" +
                "258, 65\n" +
                "123, 147\n" +
                "342, 42\n" +
                "325, 185\n" +
                "69, 45\n" +
                "249, 336\n" +
                "92, 134\n" +
                "230, 241\n" +
                "74, 262\n" +
                "241, 78\n" +
                "299, 58\n" +
                "231, 146\n" +
                "239, 87\n" +
                "44, 157\n" +
                "156, 340\n" +
                "227, 226\n" +
                "212, 318\n" +
                "194, 135\n" +
                "235, 146\n" +
                "171, 197\n" +
                "160, 59\n" +
                "218, 205\n" +
                "323, 102\n" +
                "290, 356\n" +
                "244, 214\n" +
                "174, 250\n" +
                "70, 331\n" +
                "288, 80\n" +
                "268, 128\n" +
                "359, 98\n" +
                "78, 249\n" +
                "221, 48\n" +
                "321, 228\n" +
                "52, 225\n" +
                "151, 302\n" +
                "183, 150\n" +
                "142, 327\n" +
                "172, 56\n" +
                "72, 321\n" +
                "225, 298\n" +
                "265, 300\n" +
                "86, 288\n" +
                "78, 120\n" +
                "146, 345\n" +
                "268, 181\n" +
                "243, 235\n" +
                "262, 268\n" +
                "40, 60";
        System.out.println("Day06(a): "+largestFiniteArea(input));
        System.out.println("Day06(b): "+regionWithTotalDistanceLessThan(input,10000)); //40134
    }
}