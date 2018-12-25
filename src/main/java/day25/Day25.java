package day25;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Day25 {
    static class Star {
        int x,y,z,t;
        HashSet<Integer> constellations = new HashSet<>();
        Star(int x, int y, int z, int t) {
            this.x=x;
            this.y=y;
            this.z=z;
            this.t=t;
        }
        void setConstellation(int constellationNumber) {
            constellations.add(constellationNumber);
        }
        void changeConstellation(int oldNumber, int newNumber) {
            if (constellations.contains(oldNumber)) {
                constellations.remove(oldNumber);
                constellations.add(newNumber);
            }
        }
        void addConstellations(HashSet<Integer> constellations) {
            this.constellations.addAll(constellations);
        }

        HashSet<Integer> getConstellations() {
            return constellations;
        }
        boolean isWithinDistance(Star otherStar) {
            return(Math.abs(x-otherStar.x)
                  +Math.abs(y-otherStar.y)
                  +Math.abs(z-otherStar.z)
                  +Math.abs(t-otherStar.t)) <=3;
        }
    }

    static List<Star> knownStars;
    static HashSet<Integer> allConstellations;
    static int solveA(String input) {
        knownStars = new ArrayList<>();
        allConstellations=new HashSet<>();
        int numberOfConstellations=0;

        for (String s : input.split("\r?\n")) {
            String[] values = s.replaceAll(" ","").split(",");

            int x=Integer.valueOf(values[0]);
            int y=Integer.valueOf(values[1]);
            int z=Integer.valueOf(values[2]);
            int t=Integer.valueOf(values[3]);

            Star star = new Star(x,y,z,t);

            star.setConstellation(numberOfConstellations++);
            allConstellations.add(numberOfConstellations);

            knownStars.stream().filter(s1->s1.isWithinDistance(star))
                               .forEach(s1->{star.addConstellations(s1.getConstellations());});
            knownStars.add(star);
            while (star.getConstellations().size()>1) {
                List<Integer> constellations = new ArrayList<>(star.getConstellations());
                allConstellations.remove(constellations.get(1));
                knownStars.stream().filter(s1->s1.getConstellations()
                                                 .contains(constellations.get(1)))
                                                 .forEach(s1 ->{ s1.getConstellations().remove(constellations.get(1));
                                                                 s1.getConstellations().add(constellations.get(0)); });
            }
        }
        return allConstellations.size();
    }
}
