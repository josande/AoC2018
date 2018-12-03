package day03;

public class Day03 {


    //This solves A
    static int getOverlappingFabric(String input) {

        //string into an array
        String[] inputRows = input.split("\n");

        //Create a 2D array with integers
        String[][] fabric = new String[1000][1000];

        for(int x=0;x<1000;x++) {
            for(int y=0;y<1000;y++) {
                fabric[x][y] = ".";
            }
        }


        fillFabricPattern(fabric, inputRows);


        //check how many inches are covered by X
        return numberOfOccurencies(fabric, "X");
    }
    private static String[][] fillFabricPattern(String[][] fabric, String[] inputRows) {
        for(String row : inputRows) {
            //split: starting position left, starting position top, total covered
            String startPositions = row.split(" ")[2];
            startPositions= startPositions.replaceAll(":", "");
            int startLeft = Integer.valueOf(startPositions.split(",")[0]);
            int startTop = Integer.valueOf(startPositions.split(",")[1]);

            String area = row.split(" ")[3];
            int width = Integer.valueOf(area.split("x")[0]);
            int heigth = Integer.valueOf(area.split("x")[1]);

            String id = row.split(" ")[0];
            id = id.replaceAll("#", "");
            int patternID = Integer.valueOf(id);

            //SetFabric() set the id to covered area, if covered already, set X
            for (int xCoordinate = startLeft; xCoordinate < startLeft + width; xCoordinate++) {
                for (int yCoordinate = startTop; yCoordinate < startTop + heigth; yCoordinate++) {
                    if (fabric[xCoordinate][yCoordinate].equals(".")) {
                        fabric[xCoordinate][yCoordinate] = "" + patternID;
                    } else if (fabric[xCoordinate][yCoordinate].equals("X")) {

                    } else {
                        fabric[xCoordinate][yCoordinate] = "X";
                    }
                }
            }
        }
        return fabric;
    }
//    /Part Two
static int getNonOverlappingFabric(String input) {

    //string into an array
    String[] inputRows = input.split("\n");

    //Create a 2D array with integers
    String[][] fabric = new String[1000][1000];

    for(int x=0;x<1000;x++) {
        for(int y=0;y<1000;y++) {
            fabric[x][y] = ".";
        }
    }


    fillFabricPattern(fabric, inputRows);


    for(String row : inputRows) {
        String area = row.split(" ")[3];
        int width = Integer.valueOf(area.split("x")[0]);
        int heigth = Integer.valueOf(area.split("x")[1]);
        int totalAreaOriginal = width * heigth;

        String id = row.split(" ")[0];
        id = id.replaceAll("#", "");
        int patternID = Integer.valueOf(id);

        if(numberOfOccurencies(fabric,""+patternID) == totalAreaOriginal) {
            return patternID;
        }
    }

     return -1;
}




    private static int numberOfOccurencies(String[][] fabric, String pattern) {
        int numberOfTimes = 0;
        for(int x = 0; x < 1000;x++) {
            for (int y = 0; y < 1000; y++) {
                if (fabric[x][y].equals(pattern))
                    numberOfTimes++;
            }
        }
        return numberOfTimes;
    }

    private static void printItOut(String[][] fabric) {
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                System.out.print(fabric[x][y]);
            }
            System.out.print("\n");
        }
    }
// #1 @ 1,3: 4x4
}
