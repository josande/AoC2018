package day25;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Day25 {
    private static boolean inRange(int[] star, int[] otherStar){
        int d=0;
        for ( int i=0; i<4; i++) { d+=Math.abs(star[i]-otherStar[i]); }
        return d<=3;

    }
    static int solveA(String input) {
        List<int[]> allStars = new ArrayList<>();
        for (String s : input.split("\r?\n")) {
            String[] values = s.replaceAll(" ", "").split(",");
            allStars.add(Stream.of(values).mapToInt(Integer::parseInt).toArray());
        }
        int constellations = 0;
        while (allStars.size() > 0) {
            constellations++;
            List<int[]> closeStars = new ArrayList<>();
            closeStars.add(allStars.get(0));
            List<int[]> sameConstellation;
            while (closeStars.size()>0) {
                sameConstellation=new ArrayList<>();
                for (int[] star : closeStars) {
                    sameConstellation.addAll(findCloseStars(star, allStars));
                }
                closeStars=sameConstellation;
                allStars.removeAll(closeStars);
            }
        }
        return constellations;
    }
    private static List<int[]> findCloseStars(int[] star, List<int[]> allStars) {
        return allStars.stream().filter(s -> inRange(s, star)).collect(Collectors.toList());
    }
}
