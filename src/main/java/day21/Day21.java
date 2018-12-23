package day21;

import utils.Bunnysembler;

import java.util.ArrayList;
import java.util.List;

class Day21 {
    private static ArrayList<String[]> getCommands(String input) {
        String[] rows = input.split("\r?\n");
        ArrayList<String[]> commands = new ArrayList<>();
        for (int i=1; i<rows.length;i++) {
            commands.add(rows[i].split(" "));
        }
        return commands;
    }
    static int solveA(String input) {
        int ip = Integer.valueOf(input.split("\r?\n")[0].split(" ")[1]);
        ArrayList<String[]> commands = getCommands(input);
        int[] state = {0,0,0,0,0,0};
        int compRow=-1;
        int compCol=-1;
        for (int i=0; i<commands.size(); i++) {
            if(commands.get(i)[0].equals("eqrr") && (commands.get(i)[1].equals("0") || commands.get(i)[2].equals("0"))) {
                compRow=i;
                if (commands.get(i)[1].equals("0")) {
                    compCol=Integer.valueOf(commands.get(i)[2]);
                } else {
                    compCol=Integer.valueOf(commands.get(i)[1]);
                }
            }
        }
        if (compRow<0 || compCol<0) {
            return -1;
        }
        while(state[ip]>=0 && state[ip]<commands.size()) {
            if (state[ip]==compRow) { return state[compCol]; }
            state= Bunnysembler.doOperation(commands.get(state[ip])[0], state, Integer.valueOf(commands.get(state[ip])[1]),Integer.valueOf(commands.get(state[ip])[2]),Integer.valueOf(commands.get(state[ip])[3]));
            state[ip]++;
        }
        return -1;
    }
    static int solveB(String input) {
        int ip = Integer.valueOf(input.split("\r?\n")[0].split(" ")[1]);
        ArrayList<String[]> commands = getCommands(input);
        int[] state = {0,0,0,0,0,0};
        int compRow=-1;
        int compCol=-1;
        for (int i=0; i<commands.size(); i++) {
            if(commands.get(i)[0].equals("eqrr") && (commands.get(i)[1].equals("0") || commands.get(i)[2].equals("0"))) {
                compRow=i;
                if (commands.get(i)[1].equals("0")) {
                    compCol=Integer.valueOf(commands.get(i)[2]);
                } else {
                    compCol=Integer.valueOf(commands.get(i)[1]);
                }
            }
        }
        if (compRow<0 || compCol<0) {
            return -1;
        }
        List<Integer> values=new ArrayList<>();
        while(state[ip]>=0 && state[ip]<commands.size()) {
            if (state[ip]==compRow) {
                if (values.contains(state[compCol])) {
                    return values.get(values.size()-1);
                }
                values.add(state[5]);
            }
            state= Bunnysembler.doOperation(commands.get(state[ip])[0], state, Integer.valueOf(commands.get(state[ip])[1]),Integer.valueOf(commands.get(state[ip])[2]),Integer.valueOf(commands.get(state[ip])[3]));
            state[ip]++;
        }
        return -1;
    }
}
