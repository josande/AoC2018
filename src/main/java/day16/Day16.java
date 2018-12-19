package day16;

import java.util.*;

class Day16 {

//    addr (add register) stores into register C the result of adding register A and register B.
    static int[] addr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] + state[b];
        return newState;
    }

//    addi (add immediate) stores into register C the result of adding register A and value B.
    static int[] addi(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] + b;
        return newState;
    }

//    mulr (multiply register) stores into register C the result of multiplying register A and register B.
    static int[] mulr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] * state[b];
        return newState;
    }

//    muli (multiply immediate) stores into register C the result of multiplying register A and value B.
    static int[] muli(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] * b;
        return newState;
    }

//    banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
    static int[] banr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] & state[b];
        return newState;
    }

//    bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
    static int[] bani(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] & b;
        return newState;
    }

//    borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
    static int[] borr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] | state[b];
        return newState;
    }

//    bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
    static int[] bori(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a] | b;
        return newState;
    }

//    setr (set register) copies the contents of register A into register C. (Input B is ignored.)
    static int[] setr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = state[a];
        return newState;
    }

//    seti (set immediate) stores value A into register C. (Input B is ignored.)
    static int[] seti(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        newState [c] = a;
        return newState;
    }

//    gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
    static int[] gtir(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(a > state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }


//    gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
    static int[] gtri(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(state[a] > b) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

//    gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
    static int[] gtrr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(state[a] > state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

//    eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
    static int[] eqir(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(a == state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

//    eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
    static int[] eqri(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(state[a] == b) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }

//    eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
    static int[] eqrr(int[] state, int a, int b, int c) {
        int[] newState = Arrays.copyOf(state,4);
        if(state[a] == state[b]) {
            newState [c] = 1;
        } else {
            newState[c] = 0;
        }
        return newState;
    }
    static HashSet<Integer> returnSetOfPossibleOperations(int[] state, int a, int b, int c, int[] output) {
        HashSet<Integer> possibleOps=new HashSet<>();

        for (int op=0; op<17; op++) {
            if (Arrays.equals(output, doOperation(op, state, a,b,c))) {
                possibleOps.add(op);
            }
        }
        return possibleOps;
    }
    private static int[] doOperation(int op, int[] state, int a, int b, int c) {
        switch(op) {
            case 0 : return addr(state,a,b,c);
            case 1 : return addi(state,a,b,c);
            case 2 : return mulr(state,a,b,c);
            case 3 : return muli(state,a,b,c);
            case 4 : return banr(state,a,b,c);
            case 5 : return bani(state,a,b,c);
            case 6 : return borr(state,a,b,c);
            case 7 : return bori(state,a,b,c);
            case 8 : return setr(state,a,b,c);
            case 9 : return seti(state,a,b,c);
            case 10 : return gtir(state,a,b,c);
            case 11 : return gtri(state,a,b,c);
            case 12 : return gtrr(state,a,b,c);
            case 13 : return eqir(state,a,b,c);
            case 14 : return eqri(state,a,b,c);
            case 15 : return eqrr(state,a,b,c);
            default: return null;
        }
    }

    private static List<int[]> getStates(String input) {
        String[] rows = input.split("\r?\n");
        List<int[]> states = new ArrayList<>();
        for(String row : rows) {
            if(row.startsWith("Before: ")) {
                int[] state = new int[4];
                state[0] = Integer.valueOf(row.substring(9,10));
                state[1] = Integer.valueOf(row.substring(12,13));
                state[2] = Integer.valueOf(row.substring(15,16));
                state[3] = Integer.valueOf(row.substring(18,19));
                states.add(state);
            }
        }
        return states;
    }

    private static List<int[]> getResults(String input) {
        String[] rows = input.split("\r?\n");
        List<int[]> results = new ArrayList<>();
        for(String row : rows) {
            if(row.startsWith("After: ")) {
                int[] result = new int[4];
                result[0] = Integer.valueOf(row.substring(9,10));
                result[1] = Integer.valueOf(row.substring(12,13));
                result[2] = Integer.valueOf(row.substring(15,16));
                result[3] = Integer.valueOf(row.substring(18,19));
                results.add(result);
            }
        }
        return results;
    }

    private static List<int[]> getInputs(String input) {
        String[] rows = input.split("\r?\n");
        List<int[]> inputs = new ArrayList<>();
        for(String row : rows) {
            if(!row.startsWith("After: ")
            && !row.startsWith("Before: ")
            && row.length() > 1) {
                int[] inputValues = getInputsForSingleRow(row);
                inputs.add(inputValues);
            }
        }
        return inputs;
    }
    private static int[] getInputsForSingleRow(String row) {
        int[] inputValues = new int[4];
        inputValues[0] = Integer.valueOf(row.split(" ")[0]);
        inputValues[1] = Integer.valueOf(row.split(" ")[1]);
        inputValues[2] = Integer.valueOf(row.split(" ")[2]);
        inputValues[3] = Integer.valueOf(row.split(" ")[3]);
        return inputValues;
    }

    static int solveA(String input) {
        List<int[]> states = getStates(input);
        List<int[]> inputs = getInputs(input);
        List<int[]> results= getResults(input);

       int opCounter=0;
        for (int i=0; i<states.size(); i++) {
            if (3 <= returnSetOfPossibleOperations(states.get(i),inputs.get(i)[1], inputs.get(i)[2], inputs.get(i)[3], results.get(i)).size()) {
               opCounter++;
            }
        }

        return opCounter;
    }

    static int solveB(String inputA, String inputB) {

        // This parts find the matching opCodes for our functions
        List<int[]> states = getStates(inputA);
        List<int[]> inputs = getInputs(inputA);
        List<int[]> results= getResults(inputA);
        HashMap<Integer,Integer> translationOpCodes = new HashMap<>();

        for (int i=0; i<states.size(); i++) {
            HashSet<Integer> possibleOpCodes = returnSetOfPossibleOperations(states.get(i), inputs.get(i)[1], inputs.get(i)[2], inputs.get(i)[3], results.get(i));
            possibleOpCodes.removeAll( translationOpCodes.values() );

            if (1 == possibleOpCodes.size()) {
                translationOpCodes.put(inputs.get(i)[0],(Integer) possibleOpCodes.toArray()[0]);
            }
        }
        //This next part will run the program with said codes!
        int[] state = {0,0,0,0};

        for (String row : inputB.split("\r?\n")) {
            //1 find the four ints
            int[] inputValues = getInputsForSingleRow(row);

            //2 translate int 0 to out op codes using the hashmap
            inputValues[0] = translationOpCodes.get(inputValues[0]);

            //3 set the ints and the current state to the opRunnerThingy, get the new state as result.
            state = doOperation(inputValues[0],state,inputValues[1],inputValues[2],inputValues[3]);
        }

        return state[0];
    }
}
