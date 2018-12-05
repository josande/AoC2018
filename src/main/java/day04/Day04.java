package day04;

import java.util.*;

public class Day04 {



    static int getGuardIdSleepCombination(String input) {


        ArrayList<String[]> sleepSchedule = makeSleepSchedule(input);


        //return the multiplication guardID * minute most asleep
        int guardId = getGuardId(sleepSchedule);
        return  guardId * getMostLikelyMinuteToSleep(sleepSchedule, guardId);
    }


    static int getMostLikelyGuardMinuteCombination(String input) {
        ArrayList<String[]> sleepSchedule = makeSleepSchedule(input);
        //return the multiplication guardID * minute most asleep
        List<Integer> guardIds = getAllGuardIds(sleepSchedule);
        return  getMostLikelyMinuteToSleepMultipliedByGuardId(sleepSchedule, guardIds);
    }

    static ArrayList<String[]> makeSleepSchedule(String input) {
        ArrayList<String[]> sleepSchedule = new ArrayList<>();
        ArrayList<String> rows = new ArrayList();
        rows.addAll(Arrays.asList(input.split("\n")));
        Collections.sort(rows);


        List<ArrayList<String>> allDays = new ArrayList<>();
        ArrayList<String> currentDay = new ArrayList<>();
        for (String row : rows) {
            String[] wordByWord = row.split(" ");
            if (wordByWord[2].equals("Guard") && !currentDay.isEmpty()) { // New guard on duty!
                allDays.add(currentDay);
                currentDay = new ArrayList<>();
            }
            currentDay.add(row);
        }
        allDays.add(currentDay);

        for (ArrayList<String> day : allDays) {
            String[] rowData = new String[62];
        //    boolean isSleeping = false;
            int fellAsleepAt = 0;

            for(int position=2; position < rowData.length; position++) {
                rowData[position] = ".";
            }

            for (String event : day) {
                String[] wordByWord = event.replace("[", "").replace("]", "").split(" ");

                if (wordByWord[2].equals("Guard")) { // Guard falls asleep
                    rowData[0] = "-";
                    rowData[1] = wordByWord[3].replaceAll("#", ""); //guard Id
                } else if (wordByWord[2].equals("falls")) { // Guard falls asleep
                    fellAsleepAt = Integer.valueOf(wordByWord[1].split(":")[1]);
      //              isSleeping = true;
                } else { //Guard wakes up
                    int wokeUpAt = Integer.valueOf(wordByWord[1].split(":")[1]);
                    for (int position = fellAsleepAt + 2; position < wokeUpAt + 2; position++) {
                        rowData[position] = "#";
                    }
    //                isSleeping = false;
                }
            }
/*            if (isSleeping) {  //Check if guard still sleeping at days end
                System.out.println("Sleeping at the end!");
                int wokeUpAt = 59;
                for (int position = fellAsleepAt + 2; position < wokeUpAt + 2; position++) {
                    rowData[position] = "#";
                }
            }*/
            sleepSchedule.add(rowData);
        }
        return sleepSchedule;
    }


    static List<Integer> getAllGuardIds (ArrayList<String[]> sleepSchedule) {
        List<Integer> allGuardIds = new ArrayList<>();
        for (String[] day : sleepSchedule) {
            int guardId = Integer.valueOf(day[1]);
            if (!allGuardIds.contains(guardId))
                allGuardIds.add(guardId);
        }
        return allGuardIds;
    }

    static String getDate(String date, String time) {
        if (time.startsWith("00")) {
            return date;
        }

        int dateOfMonth = Integer.valueOf(date.split("-")[2]);
        dateOfMonth++;

        return date.split("-")[0]+"-"+date.split("-")[1]+"-"+dateOfMonth;

    }

        /*


        for (String row : rows ) {
            String[] rowData = new String[62];
            String[] wordByWord = row.replaceAll("[", "").replaceAll("]","").split(" ");

            boolean isSleeping=false;
            int fellAsleepAt=0;

            for(int position=2; position < rowData.length; position++) {
                rowData[position] = ".";
            }
            if (wordByWord[2].equals("Guard")) { // New guard on duty!

                if(isSleeping) { //Still the old guard here!
                    int wokeUpAt = 59;
                    isSleeping=false;
                    for(int position = fellAsleepAt+2; position < wokeUpAt+2; position++) {
                        rowData[position] = "#";
                    }
                }


                rowData[0] = getDate(wordByWord[0], wordByWord[1]);
                rowData[1] = wordByWord[3].replaceAll("#", ""); //guard Id

            } else if (wordByWord[2].equals("falls")) { // Guard falls asleep
                fellAsleepAt = Integer.valueOf(wordByWord[1].split(":")[1]);
                isSleeping=true;
            } else { //Guard wakes up
                int wokeUpAt = Integer.valueOf(wordByWord[1].split(":")[1]);

                for(int position = fellAsleepAt+2; position < wokeUpAt+2; position++) {
                    rowData[position] = "#";
                }
                isSleeping=false;
            }
            sleepSchedule.add(rowData);

        }
        return sleepSchedule;
    }*/


    static int getGuardId(ArrayList<String[]> sleepSchedule) {
        HashMap<Integer, Integer> guardSleeps = new HashMap<>();
        for (String[] day : sleepSchedule) {
            int guardId = Integer.valueOf(day[1]);
            for (int position =2; position<day.length; position++) {
                if (day[position].equals("#")) {
                    guardSleeps.put(guardId, guardSleeps.getOrDefault(guardId, 0)+1);
                }
            }
        }
        int sleepsTheMost=-1;
        int mostSleepHours=-1;
        for( int id : guardSleeps.keySet()) {
            if (mostSleepHours < guardSleeps.get(id)) {
                sleepsTheMost=id;
                mostSleepHours = guardSleeps.get(id);
            }
        }

        return sleepsTheMost;
    }

    static int getMostLikelyMinuteToSleep(ArrayList<String[]> sleepSchedule, int guardID) {
        Integer[] sleptAt = new Integer[60];
        for(int i=0; i< 60; i++) {
            sleptAt[i]=0;
        }
        for (String[] day : sleepSchedule) {
            if (day[1].equals(String.valueOf(guardID))) {
                for (int position = 2; position < day.length; position++) {
                    if (day[position].equals("#")) {
                        sleptAt[position-2]++;
                    }
                }
            }
        }
        int mostTimesSlept=0;
        int mostCommonSleepTime=0;
        for (int position = 0; position < sleptAt.length; position++) {
            if (sleptAt[position]>mostTimesSlept) {
                mostTimesSlept = sleptAt[position];
                mostCommonSleepTime = position;
            }
        }
        return mostCommonSleepTime;
    }

    static int getMostLikelyMinuteToSleepMultipliedByGuardId(ArrayList<String[]> sleepSchedule, List<Integer> allGuardIds) {
        int guardIdWhoSleptTheMost = 0;
        int mostTimesSleptForAnyGuard = 0;
        int mostCommonSleepTimeForAnyGuard = 0;

        for (int guardId: allGuardIds) {
            Integer[] sleptAt = new Integer[60];
            for (int i = 0; i < 60; i++) {
                sleptAt[i] = 0;
            }
            for (String[] day : sleepSchedule) {
                if (day[1].equals(String.valueOf(String.valueOf(guardId)))) {
                    for (int position = 2; position < day.length; position++) {
                        if (day[position].equals("#")) {
                            sleptAt[position - 2]++;
                        }
                    }
                }
            }

            int mostCommonSleepTimeForThisGuard =-1;
            int mostTimesSleptForThisGuard =-1;
            for (int position = 0; position < sleptAt.length; position++) {
                if (sleptAt[position] > mostTimesSleptForThisGuard) {
                    mostTimesSleptForThisGuard = sleptAt[position];
                    mostCommonSleepTimeForThisGuard = position;
                }
            }
            if (mostTimesSleptForThisGuard > mostTimesSleptForAnyGuard ) {
                mostTimesSleptForAnyGuard = mostTimesSleptForThisGuard;
                mostCommonSleepTimeForAnyGuard = mostCommonSleepTimeForThisGuard;
                guardIdWhoSleptTheMost = guardId;
            }
        }

        return guardIdWhoSleptTheMost * mostCommonSleepTimeForAnyGuard;
    }

    static void printSleepArray(ArrayList<String[]> sleepSchedule) {
        System.out.println("*** Sleepschedule: ***");
        for(String[] row : sleepSchedule) {
            for (String word : row) {
                System.out.print(word+" ");
            }
            System.out.print("\n");
        }
        System.out.println("***  ***");
    }

}
