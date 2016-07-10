package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Solution {
    public static void main(String[] args) throws ParseException {

        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Redlaw\\IdeaProjects\\JR\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for(String s : logParser.getIPsForUser("Eduard Petrovich Morozko", dateFormat.parse("05.01.2021 20:22:55"), null))
            System.out.println(s + " \n --------forUser----------- \n" +
                    "146.34.15.5 " +
                    "\n");
        System.out.println(logParser.getNumberOfUniqueIPs(dateFormat.parse("30.08.2012 16:08:13"), null) + " \n -----------numberIps-------------- \n" +
                "4 \n");

        for(String s : logParser.getUniqueIPs(dateFormat.parse("14.10.2021 11:38:21"), null))
            System.out.println(s);

        System.out.println("\n---------------unique-ips------------\n" +
                "127.0.0.1" +
                "\n12.12.12.12" +
                "\n");

        for(String s : logParser.getIPsForEvent(Event.WRITE_MESSAGE, dateFormat.parse("11.12.2013 10:11:12"), dateFormat.parse("14.11.2015 07:08:01")))
            System.out.println(s);

        System.out.println("\n------------event--------------\n" +
                "146.34.15.5\n" +
                "127.0.0.1\n");

        for(String s : logParser.getIPsForStatus(Status.OK,dateFormat.parse("30.08.2012 16:08:13"), dateFormat.parse("14.10.2021 11:38:21")))
            System.out.println(s);

        System.out.println("\n---------------status---------------\n" +
                "127.0.0.1\n" +
                "192.168.100.2" +
                "\n146.34.15.5");


    }
}
