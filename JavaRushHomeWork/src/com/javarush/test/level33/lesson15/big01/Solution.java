package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.*;

public class Solution
{
    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {      // для переданного множества строк возвращеат множество идентификаторов

        Set<Long> longsSet = new HashSet<>();

        for (String element : strings)
        {

            longsSet.add(shortener.getId(element));
        }
        return longsSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {     // возвращает множество строк, которое соответствует переданному множеству идентификаторов

        Set<String> strSet = new HashSet<>();

        for (Long key : keys)
        {

            strSet.add(shortener.getString(key));
        }
        return strSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    { // тестирует работу переданной стратегии на определенном количестве элементов elementsNumber

        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> randomStrings = new HashSet<>();

        for (int i = 0; i < elementsNumber; i++)
        {
            randomStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date date1 = new Date();
        Set<Long> getIds = getIds(shortener, randomStrings);
        Date date2 = new Date();
        long totalGetIDsTime = date2.getTime() - date1.getTime();

        Date date3 = new Date();
        Set<String> getStrings = getStrings(shortener, getIds);
        Date date4 = new Date();
        long totalGetStringsTime = date4.getTime() - date3.getTime();

        if (randomStrings.size() == getStrings.size())
        {
            Helper.printMessage("Тест пройден.");
        } else
        {
            Helper.printMessage("Тест не пройден.");
        }

    }
}
