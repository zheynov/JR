package com.javarush.test.level27.lesson15.big01.ad;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public Map<String, Integer> videos()
    {
        Map<String, Integer> map = new TreeMap<>(new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });

        for (Advertisement adv : storage.list())
        {
            if (map.containsKey(adv.getName()))
            {
                map.put(adv.getName(), map.get(adv.getName() + adv.getHits()));
            } else
                map.put(adv.getName(), adv.getHits());
        }
        return map;
    }

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }
}
