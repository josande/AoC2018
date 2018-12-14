package day14;

import java.util.ArrayList;
import java.util.Collections;

class Day14 {
    static String solveA(String input) {
        ArrayList<Integer> scoreboard=new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        int pos1=0;
        int pos2=1;

        for (;;) {
            int sum=scoreboard.get(pos1)+scoreboard.get(pos2);
            if (sum>9) {
                scoreboard.add(sum/10);
            }
            scoreboard.add(sum%10);

            pos1=(pos1+scoreboard.get(pos1)+1)%scoreboard.size();
            pos2=(pos2+scoreboard.get(pos2)+1)%scoreboard.size();

            if (scoreboard.size()>=Integer.valueOf(input)+10) {
                StringBuilder result = new StringBuilder();
                for (int j : scoreboard.subList(Integer.valueOf(input),Integer.valueOf(input)+10)) {
                    result.append(j);
                }
                return result.toString();
            }
        }
    }

    static int solveB(String input) {
        ArrayList<Integer> scoreboard=new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        ArrayList<Integer> inputList = new ArrayList<>();
        for (char c : input.toCharArray()) {
            inputList.add(c-48);
        }
        int pos1=0;
        int pos2=1;

        for (;;) {
            int sum=scoreboard.get(pos1)+scoreboard.get(pos2);
            if (sum>9) {
                scoreboard.add(sum/10);
                if (scoreboard.size()>inputList.size() && scoreboard.subList(scoreboard.size()-input.length(), scoreboard.size()).equals(inputList)){
                    return Collections.indexOfSubList(scoreboard, inputList);
                }
            }
            scoreboard.add(sum%10);
            if (scoreboard.size()>inputList.size() && scoreboard.subList(scoreboard.size()-input.length(), scoreboard.size()).equals(inputList)){
                return Collections.indexOfSubList(scoreboard, inputList);
            }
            pos1=(pos1+scoreboard.get(pos1)+1)%scoreboard.size();
            pos2=(pos2+scoreboard.get(pos2)+1)%scoreboard.size();
        }
    }
}
