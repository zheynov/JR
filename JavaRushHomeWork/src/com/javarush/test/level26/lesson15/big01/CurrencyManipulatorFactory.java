package com.javarush.test.level26.lesson15.big01;

import java.util.*;

public class CurrencyManipulatorFactory
{
    private static Map<CurrencyManipulator, String> manipulators = new HashMap<>();

    private CurrencyManipulatorFactory()
    {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        CurrencyManipulator newManipulator;

        for (Map.Entry<CurrencyManipulator, String> cm : manipulators.entrySet())
        {
            if (cm.getKey().getCurrencyCode().equals(currencyCode))
                return cm.getKey();
        }

        newManipulator = new CurrencyManipulator(currencyCode);

        manipulators.put(newManipulator, currencyCode);

        return newManipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {

        return manipulators.keySet();

    }
}