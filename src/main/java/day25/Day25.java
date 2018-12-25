package day25;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Day25 {
    static class Star {
        int[] values;
        int constellation;
        Star(int[] values, int constellation) {
            this.values=values;
            this.constellation=constellation;
        }
        void setConstellation(int constellation) {
            this.constellation = constellation;
        }
        Integer getConstellation() {
            return constellation;
        }
        boolean isWithinDistance(Star otherStar) {
            int d=0;
            for ( int i=0; i<4; i++) { d+=Math.abs(values[i]-otherStar.values[i]); }
            return d<=3;
        }
    }

    static int solveA(String input) {
        List<Star> knownStars = new ArrayList<>();
        int numberOfConstellations=0;
        for (String s : input.split("\r?\n")) {
            String[] values = s.replaceAll(" ", "").split(",");
            Star star = new Star(Stream.of(values).mapToInt(Integer::parseInt).toArray(), numberOfConstellations++);
            knownStars.stream().filter( s1 -> s1.isWithinDistance(star) )
                               .forEach(s1 -> { star.setConstellation(s1.getConstellation()); });
            knownStars.add(star);
        }
        return (int) knownStars.stream().mapToInt(Star::getConstellation).distinct().count();
    }
}
