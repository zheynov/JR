package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticEventManager
{
    private static StatisticEventManager instance = new StatisticEventManager();

    private StatisticEventManager()
    {
    }

    private StatisticStorage storageStatistic = new StatisticStorage();

    public Map<Date, Double> profit()
    {
        Map<Date, Double> map = new TreeMap<>((Collections.reverseOrder()));

        for (EventDataRow video : storageStatistic.getStatisticMap(EventType.SELECTED_VIDEOS))
        {
            VideoSelectedEventDataRow entryNew = (VideoSelectedEventDataRow) video;
            Date date = dateToStringMidnight(video.getDate());

            if (!map.containsKey(date))
            {
                map.put(date, (0.01d * (double) entryNew.getAmount()));
            } else
            {
                map.put(date, (map.get(date) + (0.01d * (double) entryNew.getAmount())));
            }
        }
        return map;
    }

    public Map<Date, Map<String, Integer>> cookData()
    {
        Map<Date, Map<String, Integer>> map = new TreeMap<>((Collections.reverseOrder()));

        for (EventDataRow cookEvent : storageStatistic.getStatisticMap(EventType.COOKED_ORDER))
        {
            CookedOrderEventDataRow entryNew = (CookedOrderEventDataRow) cookEvent;
            Date date = dateToStringMidnight(cookEvent.getDate());

            int time = entryNew.getTime();
            if (time == 0)
                continue;

            if (time % 60 == 0) time = time / 60;
            else time = time / 60 + 1;

            if (!map.containsKey(date))
            {
                Map<String, Integer> cookMap = new TreeMap<>();
                cookMap.put(entryNew.getCookName(), time);
                map.put(date, cookMap);


            } else
            {
                Map<String, Integer> cookMap = map.get(date);

                if (cookMap.containsKey(entryNew.getCookName()))
                {
                    cookMap.put(entryNew.getCookName(), cookMap.get(entryNew.getCookName()) + time);
                } else
                {
                    cookMap.put(entryNew.getCookName(), time);
                }
            }
        }
        return map;
    }

    private Date dateToStringMidnight(Date date)
    {
        GregorianCalendar roundedDate = new GregorianCalendar();

        roundedDate.setTime(date);
        roundedDate.set(Calendar.HOUR_OF_DAY, 0);
        roundedDate.set(Calendar.MINUTE, 0);
        roundedDate.set(Calendar.SECOND, 0);
        roundedDate.set(Calendar.MILLISECOND, 0);

        return roundedDate.getTime();
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
            if (data == null) return;
            staticticMap.get(data.getType()).add(data);
        }
    }

    public static  StatisticEventManager getInstance()
    {
        return instance;
    }

    public void register(EventDataRow data)
    {
        if (data == null) return;
        storageStatistic.put(data);
    }
}
