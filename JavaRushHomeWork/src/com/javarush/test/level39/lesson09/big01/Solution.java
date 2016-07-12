package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;

public class Solution
{
    public static void main(String[] args)
    {
        LogParser logParser = new LogParser(Paths.get("D:\\JAVA\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));

        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));

    }
}
