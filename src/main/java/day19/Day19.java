package day19;

import java.util.ArrayList;
import java.util.Arrays;

class Day19 {
    //    addr (add register) stores into register C the result of adding register A and register B.
    private static int[] addr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] + state[b];
        return newState;
    }

    //    addi (add immediate) stores into register C the result of adding register A and value B.
    private static int[] addi(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] + b;
        return newState;
    }

    //    mulr (multiply register) stores into register C the result of multiplying register A and register B.
    private static int[] mulr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] * state[b];
        return newState;
    }

    //    muli (multiply immediate) stores into register C the result of multiplying register A and value B.
    private static int[] muli(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] * b;
        return newState;
    }

    //    banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
    private static int[] banr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] & state[b];
        return newState;
    }

    //    bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
    private static int[] bani(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] & b;
        return newState;
    }

    //    borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
    private static int[] borr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] | state[b];
        return newState;
    }

    //    bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
    private static int[] bori(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a] | b;
        return newState;
    }

    //    setr (set register) copies the contents of register A into register C. (Input B is ignored.)
    private static int[] setr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = state[a];
        return newState;
    }

    //    seti (set immediate) stores value A into register C. (Input B is ignored.)
    private static int[] seti(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        newState [c] = a;
        return newState;
    }

    //    gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
    private static int[] gtir(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(a > state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }


    //    gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
    private static int[] gtri(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(state[a] > b) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

    //    gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
    private static int[] gtrr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(state[a] > state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

    //    eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
    private static int[] eqir(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(a == state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

    //    eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
    private static int[] eqri(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(state[a] == b) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

    //    eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
    private static int[] eqrr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,state.length);
        if(state[a] == state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }
    private static int[] doOperation(String op, int[] state, int a, int b, int c) {
        switch(op) {
            case "addr" : return addr(state,a,b,c);
            case "addi" : return addi(state,a,b,c);
            case "mulr" : return mulr(state,a,b,c);
            case "muli" : return muli(state,a,b,c);
            case "banr" : return banr(state,a,b,c);
            case "bani" : return bani(state,a,b,c);
            case "borr" : return borr(state,a,b,c);
            case "bori" : return bori(state,a,b,c);
            case "setr" : return setr(state,a,b,c);
            case "seti" : return seti(state,a,b,c);
            case "gtir" : return gtir(state,a,b,c);
            case "gtri" : return gtri(state,a,b,c);
            case "gtrr" : return gtrr(state,a,b,c);
            case "eqir" : return eqir(state,a,b,c);
            case "eqri" : return eqri(state,a,b,c);
            case "eqrr" : return eqrr(state,a,b,c);
            default: return null;
        }
    }
    static int solveA(String input) {
        String[] rows = input.split("\r?\n");
        int ip = Integer.valueOf(rows[0].split(" ")[1]);
        ArrayList<String[]>commands = new ArrayList<>();
        for (int i=1; i<rows.length;i++) {
            commands.add(rows[i].split(" "));
        }
        int[] state = {0,0,0,0,0,0};
        while(state[ip]>=0 && state[ip]<commands.size() ) {
            state=doOperation(commands.get(state[ip])[0], state, Integer.valueOf(commands.get(state[ip])[1]),Integer.valueOf(commands.get(state[ip])[2]),Integer.valueOf(commands.get(state[ip])[3]));
            state[ip]++;
        }
        return state[0];
    }

    static int solveB(String input) {
        String[] rows = input.split("\r?\n");
        int ip = Integer.valueOf(rows[0].split(" ")[1]);
        ArrayList<String[]> commands = new ArrayList<>();
        for (int i = 1; i < rows.length; i++) {
            commands.add(rows[i].split(" "));
        }
        int[] state = {1, 0, 0, 0, 0, 0};
        while (state[ip] >= 0 && state[ip] < commands.size()) {
            state = doOperation(commands.get(state[ip])[0], state, Integer.valueOf(commands.get(state[ip])[1]), Integer.valueOf(commands.get(state[ip])[2]), Integer.valueOf(commands.get(state[ip])[3]));
            state[ip]++;
            int bigNumber = Arrays.stream(state).max().getAsInt();
            if (state[0] ==0 && bigNumber>1000000) {
                int result = 0;
                for (int i = 1; i <= Math.sqrt(bigNumber); i++) {
                    if (bigNumber % i == 0) {
                        result += i;
                        result += bigNumber/i;
                    }
                }
                return result;
            }
        }
        return state[0];
    }
}
