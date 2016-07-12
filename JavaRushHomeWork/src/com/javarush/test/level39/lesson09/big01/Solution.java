package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;

public class Solution
{
    public static void main(String[] args) throws ParseException
    {
        LogParser logParser = new LogParser(Paths.get("D:\\JAVA\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));
      //  System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, null));

        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));


    }
}
