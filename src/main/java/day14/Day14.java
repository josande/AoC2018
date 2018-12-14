package day14;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Integer> scoreboard=new ArrayList<>(Arrays.asList(3,7));
        ArrayList<Integer> inputList = new ArrayList<>();
        int scoreSize;
        int match;
        for (char c : input.toCharArray()) {
            inputList.add(c-48);
        }
        int pos1=0;
        int pos2=1;

        for (;;) {
            int sum=scoreboard.get(pos1)+scoreboard.get(pos2);
            if (sum>9) {
                scoreboard.add(sum/10);
                scoreSize=scoreboard.size();
                match=0;
                for (int i =1; i<scoreSize; i++) {
                    if (scoreboard.get(scoreSize-i) != inputList.get(input.length()-i)) {
                        break;
                    }
                    match++;
                    if (match==input.length())
                        return scoreSize-input.length();
                }
            }
            scoreboard.add(sum%10);
            scoreSize=scoreboard.size();
            match=0;
            for (int i =1; i<scoreSize; i++) {
                if (scoreboard.get(scoreSize-i) != inputList.get(input.length()-i)) {
                    break;
                }
                match++;
                if (match==input.length())
                    return scoreSize-input.length();
            }
            pos1=(pos1+scoreboard.get(pos1)+1)%scoreboard.size();
            pos2=(pos2+scoreboard.get(pos2)+1)%scoreboard.size();
        }
    }
}
