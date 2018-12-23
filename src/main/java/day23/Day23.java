package day23;

import java.util.ArrayList;
import java.util.List;

class Day23 {

    static class Nanobot {
        private int x,y,z,r;

        Nanobot(int x, int y, int z, int r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }
        @Override
        public String toString() {
            return ("("+x+","+y+","+z+")");
        }
        int getX() {
            return x;
        }
        int getY() {
            return y;
        }
        int getZ() {
            return z;
        }
        int getRadius() {
            return r;
        }
        boolean reachesOtherBot(Nanobot otherBot) {
            int distance =  Math.abs(otherBot.getX()-this.getX()) +
                            Math.abs(otherBot.getY()-this.getY()) +
                            Math.abs(otherBot.getZ()-this.getZ());
            return (this.getRadius() >= distance);
        }

        public int getDistance() {
            return Math.abs(this.getX()) +
                    Math.abs(this.getY()) +
                    Math.abs(this.getZ());
        }
    }
    static List<Nanobot> getAllNanobots (String input) {
        String[] rows = input.split("\r?\n");
        List<Nanobot> allBots = new ArrayList<>();
        for(int i = 0; i<rows.length;i++) {
            String row = rows[i].replaceAll("pos=<","")
                                .replaceAll(">, r=",",");
            int x = Integer.valueOf(row.split(",")[0]);
            int y = Integer.valueOf(row.split(",")[1]);
            int z = Integer.valueOf(row.split(",")[2]);
            int r = Integer.valueOf(row.split(",")[3]);
            Nanobot bot = new Nanobot(x,y,z,r);
            allBots.add(bot);
        }
        return allBots;
    }

    static Nanobot getStrongestBot(List<Nanobot> allBots) {
        Nanobot strongestBot = allBots.get(0);
        for(Nanobot bot : allBots) {
            if(bot.getRadius() > strongestBot.getRadius()) {
                strongestBot = bot;
            }
        }
        return strongestBot;
    }

    static List<Nanobot> botsInRangeOf(Nanobot strongestBot, List<Nanobot> allBots) {
        List<Nanobot> botsInRange = new ArrayList<>();
        for (Nanobot bot : allBots) {
            if(strongestBot.reachesOtherBot(bot)) {
                botsInRange.add(bot);
            }
        }
        return botsInRange;
    }



    static int solveA(String input) {
        List<Nanobot> allBots = getAllNanobots(input);
        return botsInRangeOf(getStrongestBot(allBots),allBots).size();
    }

    static int solveB(String input) {
        List<Nanobot> allBots = getAllNanobots(input);

        int xMin = allBots.stream().mapToInt(x -> x.getX()).min().getAsInt();
        int xMax = allBots.stream().mapToInt(x -> x.getX()).max().getAsInt();
        int yMin = allBots.stream().mapToInt(x -> x.getY()).min().getAsInt();
        int yMax = allBots.stream().mapToInt(x -> x.getY()).max().getAsInt();
        int zMin = allBots.stream().mapToInt(x -> x.getZ()).min().getAsInt();
        int zMax = allBots.stream().mapToInt(x -> x.getZ()).max().getAsInt();

        int maxMatches=0;
        maxMatches=0;
        List<Nanobot> bestProbes=new ArrayList<>();
        int bigNumber=10000000;
        Nanobot bestProbe=null;
        while (bigNumber>=1) {
            bestProbes=new ArrayList<>();
            for (int x=xMin; x<xMax; x+=bigNumber) {
                for (int y=yMin; y<yMax; y+=bigNumber) {
                    for (int z = zMin; z < zMax; z+=bigNumber) {
                        Nanobot probe = new Nanobot(x,y,z,0);
                        int inRangeOf=0;
                        for(Nanobot bot : allBots) {
                            inRangeOf += (bot.reachesOtherBot(probe) ? 1 : 0);
                        }
                        if (inRangeOf>maxMatches) {
                            bestProbes = new ArrayList<>();
                            bestProbes.add(probe);
                        }
                        if(inRangeOf==maxMatches) {
                            bestProbes.add(probe);
                        }
                        maxMatches=Math.max(maxMatches, inRangeOf);
                    }
                }
            }
            bestProbe = bestProbes.get(0);
            for (Nanobot probe : bestProbes) {
                if (probe.getDistance() < bestProbe.getDistance())
                    bestProbe=probe;
            }
            xMin=bestProbe.getX()-bigNumber;
            xMax=bestProbe.getX()+bigNumber;
            yMin=bestProbe.getY()-bigNumber;
            yMax=bestProbe.getY()+bigNumber;
            zMin=bestProbe.getZ()-bigNumber;
            zMax=bestProbe.getZ()+bigNumber;
            bigNumber=bigNumber/10;
        }
        return bestProbe.getDistance();
    }
}
