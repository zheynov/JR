package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet
{
    public void printAdvertisementProfit()
    {
        Map<Date, Double> map = StatisticEventManager.getInstance().profit();
        Double totalSum = 0d;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<Date, Double> entry : map.entrySet())
        {
            totalSum += entry.getValue();
            String s = dateFormat.format(entry.getKey());
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", s, entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalSum));
    }

    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> map = StatisticEventManager.getInstance().cookData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<Date, Map<String, Integer>> entry : map.entrySet())
        {
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
            Map<String, Integer> mapForCook = entry.getValue();

            for (Map.Entry<String, Integer> cooks : mapForCook.entrySet())
            {
                ConsoleHelper.writeMessage(String.format("%s - %d min", cooks.getKey(), cooks.getValue()));
            }
        }
        ConsoleHelper.writeMessage("");
    }

    public void printActiveVideoSet()
    {
        Map<String, Integer> map = StatisticAdvertisementManager.getInstance().videos();

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() > 0)
            {
                ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public void printArchivedVideoSet()
    {
        Map<String, Integer> map = StatisticAdvertisementManager.getInstance().videos();

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() <= 0)
            {
                ConsoleHelper.writeMessage(entry.getKey());
            }
        }
    }
}
