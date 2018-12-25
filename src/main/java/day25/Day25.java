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

    static int solveA(String input) {
        knownStars = new ArrayList<>();
        int numberOfConstellations=0;

        for (String s : input.split("\r?\n")) {
            String[] values = s.replaceAll(" ","").split(",");

            int x=Integer.valueOf(values[0]);
            int y=Integer.valueOf(values[1]);
            int z=Integer.valueOf(values[2]);
            int t=Integer.valueOf(values[3]);

            Star star = new Star(x,y,z,t);


            for (Star otherStar : knownStars) {
                if (otherStar.isWithinDistance(star)) {
                    star.addConstellations(otherStar.getConstellations());
                }
            }
            if (star.getConstellations().isEmpty()) {
                numberOfConstellations++;
                star.setConstellation(numberOfConstellations);
            }

            knownStars.add(star);

        }
        boolean runAgain=true;
        while (runAgain) {
            Pair<Integer, Integer> p = null;
            for (Star star : knownStars) {
                if (star.getConstellations().size()>1) {
                    //if 3 and 5, set all 5's to 3
                    List<Integer> constellations = new ArrayList<>();
                    constellations.addAll(star.getConstellations());
                    Collections.sort(constellations);

                    p=new Pair(constellations.get(0), constellations.get(1));
                    break;
                }
            }
            if (p != null) {
                for (Star star : knownStars) {
                    star.changeConstellation(p.getValue(), p.getKey());
                }
            } else {
                runAgain=false;
            }
        }
        HashSet<Integer> constellationsAtTheEnd = new HashSet<>();

        for (Star star : knownStars) {
            constellationsAtTheEnd.addAll(star.getConstellations());
        }

        return constellationsAtTheEnd.size();
    }
}
