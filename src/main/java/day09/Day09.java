package day09;

import java.util.Arrays;

class Day09 {
    static long solveA(String input) {
        int players = Integer.valueOf(input.split(" ")[0]);
        int maxMarble = Integer.valueOf(input.split(" ")[6]);
        return allTheMarbles(players,maxMarble);
    }
    static long solveB(String input) {
        int players = Integer.valueOf(input.split(" ")[0]);
        int maxMarble = Integer.valueOf(input.split(" ")[6]);
        return allTheMarbles(players,maxMarble*100);
    }
    private static long allTheMarbles(int players, int maxMarbleValue) {
        Marble startMarble = new Marble();

        Long[] scores = new Long[players];
        for (int i =0; i< players; i++) {
            scores[i]=0L;
        }

        int currentPlayer=0;
        Marble currentMarble = startMarble;

        for (int currentValue=1; currentValue <= maxMarbleValue; currentValue++) {
            if (currentValue%23 > 0) {
                currentMarble=currentMarble.getNextMarble();
                currentMarble=addMarble(currentValue, currentMarble);
            } else {
                scores[currentPlayer] += currentValue;
                currentMarble=currentMarble.getPreviousMarble()
                                           .getPreviousMarble()
                                           .getPreviousMarble()
                                           .getPreviousMarble()
                                           .getPreviousMarble()
                                           .getPreviousMarble()
                                           .getPreviousMarble();
                scores[currentPlayer]+=currentMarble.getValue();
                currentMarble=removeMarble(currentMarble);
            }
            currentPlayer=(currentPlayer+1)%players;
        }
        return Arrays.stream(scores).mapToLong(Long::valueOf).max().orElse(-1L);
    }

    private static Marble addMarble(int value, Marble currentMarble) {
        Marble newMarble = new Marble(currentMarble, currentMarble.getNextMarble(), value);
        newMarble.getPreviousMarble().setNextMarble(newMarble);
        newMarble.getNextMarble().setPreviousMarble(newMarble);
        return newMarble;
    }
    private static Marble removeMarble(Marble currentMarble) {
        currentMarble.getPreviousMarble().setNextMarble(currentMarble.getNextMarble());
        currentMarble.getNextMarble().setPreviousMarble(currentMarble.getPreviousMarble());
        return currentMarble.getNextMarble();
    }
    private static class Marble {
        private Marble previousMarble;
        private Marble nextMarble;
        final private int value;
        Marble () {
            value=0;
            nextMarble=this;
            previousMarble=this;
        }

        Marble(Marble previousMarble, Marble nextMarble, int value) {
            this.previousMarble=previousMarble;
            this.nextMarble=nextMarble;
            this.value=value;
        }

        Marble getPreviousMarble() {
            return previousMarble;
        }

        void setPreviousMarble(Marble previousMarble) {
            this.previousMarble=previousMarble;
        }

        Marble getNextMarble() {
            return nextMarble;
        }
        void setNextMarble(Marble nextMarble) {
            this.nextMarble=nextMarble;
        }
        int getValue() {
            return value;
        }

    }
}
