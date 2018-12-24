package day24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Day24 {
    static enum Faction {
        immune_system,
        infection;
    }
    static enum AttackType {
        slashing,
        cold,
        fire,
        radiation,
        bludgeoning;
    }
    static class Group implements Comparable<Group>  {
        Faction faction;
        Group target;
        boolean isBeingTargeted;
        int units;
        int hp;
        int attackPower;
        int initiative;
        boolean isDead;

        List<AttackType> weaknesses;
        List<AttackType> immunities;
        AttackType attackType;

        /*989 units each with 1274 hit points (immune to fire; weak to bludgeoning,
         slashing) with an attack that does 25 slashing damage at initiative 3*/
        Group(Faction faction, int units, int hp, int attackPower, AttackType attackType, List<AttackType> weaknesses, List<AttackType> immunities, int initiative) {
            this.faction=faction;
            this.units=units;
            this.hp=hp;
            this.attackPower=attackPower;
            this.attackType=attackType;
            this.weaknesses=weaknesses;
            this.immunities=immunities;
            this.initiative=initiative;
        }
        @Override
        public int compareTo(Group other){
            if (this.getEffectivePower()-other.getEffectivePower() != 0)
                return other.getEffectivePower()-this.getEffectivePower();
            return other.getInitiative()-this.getInitiative();
        }

        public void setTarget(Group target) {
            this.target=target;
        }
        public void setBeingTargeted(boolean beingTargeted) {
            this.isBeingTargeted=beingTargeted;
        }
        public void takeDamage(Group attackedBy) {
            int damage= attackedBy.getEffectivePower();
            damage*=(weaknesses.contains(attackedBy.getAttackType())?2:1);
            damage*=(immunities.contains(attackedBy.getAttackType())?0:1);
            int unitsKilled= Math.min(units, damage/hp);
            units -=unitsKilled;
            isDead = units<=0;
        }
        public Faction getFaction() {
            return faction;
        }
        public int getEffectivePower() {
            return units * attackPower;
        }

        public int getHp() {
            return hp;
        }
        public int getUnits() {
            return units;
        }

        public int getAttackPower() {
            return attackPower;
        }

        public int getInitiative() {
            return initiative;
        }
        public boolean isBeingTargeted() {
            return isBeingTargeted;
        }
        public boolean isDead() {
            return isDead;
        }
        public boolean isWeakTo(AttackType type) {
            return weaknesses.contains(type);
        }
        public boolean isImmuneTo(AttackType type) {
            return immunities.contains(type);
        }
        public Group findTarget() {
            //Exclude already targeted
            //Exclude immune targets
            //Find weakness
            //if tie, select effective power
            //lock on!
            List<Group> targetList;
            if (faction == Faction.immune_system) {
                targetList=infection.stream().filter(x -> !x.isBeingTargeted).filter(x->!x.isImmuneTo(attackType)).collect(Collectors.toList());
            } else {
                targetList=immuneSystem.stream().filter(x -> !x.isBeingTargeted).filter(x->!x.isImmuneTo(attackType)).collect(Collectors.toList());
            }
            if (targetList.isEmpty())
                return null;
            List<Group> preferedTargets= targetList.stream().filter(x->x.isWeakTo(attackType)).collect(Collectors.toList());
            if (!preferedTargets.isEmpty())
                return preferedTargets.get(0);
            return targetList.get(0);
        }

        public Group getTarget() {
            return target;
        }

        public AttackType getAttackType() {
            return attackType;
        }
    }
        static void setTargeting() {
            Comparator<Group> compareByEP = Comparator.comparing( Group::getEffectivePower);
            Comparator<Group> compareByInitiative = Comparator.comparing( Group::getInitiative);
            Collections.sort(allGroups, compareByEP.reversed().thenComparing(compareByInitiative.reversed()));
            Collections.sort(immuneSystem, compareByEP.reversed().thenComparing(compareByInitiative.reversed()));
            Collections.sort(infection, compareByEP.reversed().thenComparing(compareByInitiative.reversed()));

            for (Group g : allGroups) {
                Group target = g.findTarget();
                if (target!=null) {
                    g.setTarget(target);
                    target.setBeingTargeted(true);
                }
            }
        }
        static void resolveAttacks() {
            Comparator<Group> compareByInitiative = Comparator.comparing( Group::getInitiative);
            Collections.sort(allGroups, compareByInitiative);
            Collections.reverse(allGroups);
            for (Group g: allGroups) {
                Group target = g.getTarget();
                if (target!=null)
                    g.getTarget().takeDamage(g);
            }
        }
        static void resetAfterCombat() {
            for (Group g : allGroups) {
                g.setBeingTargeted(false);
                g.setTarget(null);
                if (g.isDead()) {
                    immuneSystem.remove(g);
                    infection.remove(g);
                }
            }
        }

        static List<Group> allGroups;
        static List<Group> immuneSystem;
        static List<Group> infection;

    static void createGroups(String input) {
        createGroups(input, 0);
    }
    static void createGroups(String input, int boost) {
        allGroups = new ArrayList<>();
        immuneSystem = new ArrayList<>();
        infection = new ArrayList<>();

        String[] factions = input.split("\r?\n\r?\n");


        for (String group : factions[0].split("\r?\n")) {
            if (group.length() > 20)
                immuneSystem.add(createGroup(Faction.immune_system, group, boost));
        }
        for (String group : factions[1].split("\r?\n")) {
            if (group.length() > 20)
              infection.add(createGroup(Faction.infection, group, 0));
        }
        allGroups.addAll(immuneSystem);
        allGroups.addAll(infection);
    }
    private static Group createGroup(Faction faction, String groupData, int boost) {
        int units = Integer.valueOf(groupData.split(" ")[0]);
        int hp = Integer.valueOf(groupData.split(" ")[4]);
        String theRest = groupData.split("attack that does ")[1];
        int attackPower = Integer.valueOf(theRest.split(" ")[0])+boost;
        AttackType attackType = AttackType.valueOf(theRest.split(" ")[1]);
        int initiative = Integer.valueOf(theRest.split(" ")[5]);
        List<AttackType> immunities = new ArrayList<>();
        List<AttackType> weaknesses = new ArrayList<>();
        if (groupData.contains("(")) {
            String[] attackTypes = groupData.substring(groupData.indexOf('(') + 1, groupData.indexOf(')')).split("; ");
            for (String type : attackTypes) {
                if (type.contains("immune to ")) {
                    String[] immunitiesStr = type.replaceAll("immune to ", "").split(", ");
                    for (String s : immunitiesStr) {
                        immunities.add(AttackType.valueOf(s));
                    }
                } else {
                    String[] weaknessesStr = type.replaceAll("weak to ", "").split(", ");
                    for (String s : weaknessesStr) {
                        weaknesses.add(AttackType.valueOf(s));
                    }
                }
            }
        }

        return new Group(faction, units, hp, attackPower, attackType, weaknesses, immunities, initiative);
    }
    static int solveA(String input) {
        createGroups(input);

        while(immuneSystem.size()>0 && infection.size()>0) {
            setTargeting();
            resolveAttacks();
            resetAfterCombat();
        }
        int unitsLeft=0;
        for (Group g: allGroups) {
            unitsLeft+=g.getUnits();
        }
        return unitsLeft;
    }

    static int solveB(String input) {
        for (int boost=1; ; boost++) {
            createGroups(input, boost);
            int unitsLeft=0;
            while(immuneSystem.size()>0 && infection.size()>0) {
                setTargeting();
                resolveAttacks();
                resetAfterCombat();

                int unitsAfterCombat=0;
                for (Group g : allGroups)
                    unitsAfterCombat+= g.getUnits();

                if (unitsAfterCombat==unitsLeft)
                    immuneSystem=new ArrayList<>();

                unitsLeft=unitsAfterCombat;
            }
            if (immuneSystem.size()>0)
                return unitsLeft;
        }
    }


}
