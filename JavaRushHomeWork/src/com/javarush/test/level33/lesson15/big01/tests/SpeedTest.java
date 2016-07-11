package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        Date time1 = new Date();
        for (String str : strings)
        {
            ids.add(shortener.getId(str));
        }
        Date time2 = new Date();
        return time2.getTime() - time1.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        Date time1 = new Date();
        for (Long id : ids)
        {
            strings.add(shortener.getString(id));
        }
        Date time2 = new Date();
        return time2.getTime() - time1.getTime();
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> origIDs1 = new HashSet<>();
        Set<Long> origIDs2 = new HashSet<>();


        for (int i = 0; i < 10000; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }

        long v1 = getTimeForGettingIds(shortener1, origStrings, origIDs1);
        long v2 = getTimeForGettingIds(shortener2, origStrings, origIDs2);

        Assert.assertTrue(v1 > v2);

        long v3 = getTimeForGettingStrings(shortener1, origIDs1, origStrings);
        long v4 = getTimeForGettingStrings(shortener2, origIDs2, origStrings);

        Assert.assertEquals(v3, v4, 5);
    }
}
