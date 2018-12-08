package day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;

import static day04.Day04.*;

class Day04Test {

    @Test
    void exampleOne() {
        String input =
                "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-01 00:25] wakes up\n" +
                "[1518-11-01 00:30] falls asleep\n" +
                "[1518-11-01 00:55] wakes up\n" +
                "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-05 00:55] wakes up";
        Assertions.assertEquals(240, getGuardIdSleepCombination(input));
        Assertions.assertEquals(4455,getMostLikelyGuardMinuteCombination(input));
    }

    @Test
    void exampleTwo() {
        String input = "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-05 00:55] wakes up";
        Assertions.assertEquals(99*45, getGuardIdSleepCombination(input));
    }

    @Test
    void printSleepScheduleNotSorted() {
        String input =
                "[1518-11-01 00:00] Guard #10 begins shift\n" +
                        "[1518-11-04 00:02] Guard #99 begins shift\n" +
                        "[1518-11-04 00:36] falls asleep\n" +
                        "[1518-11-04 00:46] wakes up\n" +
                        "[1518-11-05 00:03] Guard #99 begins shift\n" +
                        "[1518-11-01 00:05] falls asleep\n" +
                        "[1518-11-01 23:58] Guard #99 begins shift\n" +
                        "[1518-11-02 00:40] falls asleep\n" +
                        "[1518-11-02 00:50] wakes up\n" +
                        "[1518-11-03 00:05] Guard #10 begins shift\n" +
                        "[1518-11-03 00:24] falls asleep\n" +
                        "[1518-11-03 00:29] wakes up\n" +
                        "[1518-11-01 00:25] wakes up\n" +
                        "[1518-11-01 00:30] falls asleep\n" +
                        "[1518-11-01 00:55] wakes up\n" +
                        "[1518-11-05 00:45] falls asleep\n" +
                        "[1518-11-05 00:55] wakes up";
        ArrayList<String[]> sleepSchedule = makeSleepSchedule(input);
        Assertions.assertEquals(10, getGuardId(sleepSchedule));
    }

    @Test
    void puzzle() {
        String day = "04";
        String input=new Utils().readFile("Day"+ day +"Input.txt");
        System.out.println("Day"+ day +"(a): " + getGuardIdSleepCombination(input));
        System.out.println("Day"+ day +"(b): " + getMostLikelyGuardMinuteCombination(input));
    }
}