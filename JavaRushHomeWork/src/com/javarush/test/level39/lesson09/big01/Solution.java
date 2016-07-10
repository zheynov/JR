package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Redlaw\\IdeaProjects\\JR\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));

        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        System.out.println(logParser.getUniqueIPs(null, null));

        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));

        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));

        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));

    }
}
