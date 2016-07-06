package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager
{
    private static StatisticManager instance = new StatisticManager();
    private static Set<Cook> set = new HashSet<>();

    private StatisticManager()
    {
    }
    private static StatisticStorage storageStatistic = new StatisticStorage();

    public Map<Date, Double> profit()
    {
        Map<Date, Double> map = new TreeMap<>((Collections.reverseOrder()));

        for (EventDataRow videoEvent : storageStatistic.getStatisticMap(EventType.SELECTED_VIDEOS))
        {
            VideoSelectedEventDataRow entryNew = (VideoSelectedEventDataRow) videoEvent;
            Date date = videoEvent.getDate();

            if (!map.containsKey(date))
            {
                map.put(date, ((double) entryNew.getAmount() * 0.01d));
            } else
            {
                map.put(date, (map.get(date) + ((double) entryNew.getAmount()) * 0.01d));
            }
        }
        return map;
    }

    public Map<Date, Map<String, Integer>> cookData()
    {
        Map<Date, Map<String, Integer>> map = new TreeMap<>((Collections.reverseOrder()));

        for (EventDataRow videoEvent : storageStatistic.getStatisticMap(EventType.COOKED_ORDER))
        {
            CookedOrderEventDataRow entryNew = (CookedOrderEventDataRow) videoEvent;
            Date date = videoEvent.getDate();

            if (entryNew.getTime() == 0)
                continue;

            if (!map.containsKey(entryNew.getDate()))
            {
                Map<String, Integer> cookMap = new TreeMap<>();

                cookMap.put(entryNew.getCookName(), entryNew.getTime() / 60);
                map.put(date, cookMap);
            } else
            {
                Map<String, Integer> cookMap = map.get(date);

                cookMap.put(entryNew.getCookName(), cookMap.get(entryNew.getCookName()) + entryNew.getTime() / 60);

                map.put(date, cookMap);
            }
        }

        return map;
    }

    private static class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> staticticMap;

        public List<EventDataRow> getStatisticMap(EventType type)
        {
            return staticticMap.get(type);
        }

        public StatisticStorage()
        {
            staticticMap = new HashMap<>();
            for (EventType eventDataRows : EventType.values())
            {
                staticticMap.put(eventDataRows, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data)
        {
            staticticMap.get(data.getType()).add(data);
        }
    }

    public static  StatisticManager getInstance()
    {
        return instance;
    }

    public void register(EventDataRow data)
    {
        storageStatistic.put(data);
    }

    public void register(Cook cook)
    {
        set.add(cook);
    }
}
