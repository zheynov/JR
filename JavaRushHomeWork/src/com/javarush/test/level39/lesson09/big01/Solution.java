package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Redlaw\\IdeaProjects\\JR\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));

       System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));

        //  System.out.println(logParser.getErrorEvents(null, null));
    }
}
