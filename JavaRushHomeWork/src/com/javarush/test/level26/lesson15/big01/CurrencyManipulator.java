package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public boolean hasMoney()
    {
        Boolean value = true;

        if (denominations.isEmpty() || denominations.size() == 0)
        {
            value = false;
        }

        return value;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return expectedAmount <= getTotalAmount();
    }


    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.putAll(denominations);

        Map<Integer, Integer> finalMap = new TreeMap<>(Collections.reverseOrder());

        int amount = expectedAmount;

        for (Map.Entry<Integer, Integer> element : map.entrySet())
        {
            int count = 0;
            if (amount >= element.getKey())
            {

                int newKey = element.getKey();
                int value = element.getValue();

                while (newKey <= amount && count <= value)
                {
                    amount = amount - newKey;
                    count++;
                }

                finalMap.put(element.getKey(), count);
            }

            if (count < element.getValue())
            {
                denominations.put(element.getKey(), element.getValue() - count);
            } else if (count == element.getValue())
            {
                denominations.remove(element.getKey());
            }
        }


        if (finalMap.isEmpty() || amount != 0)
        {
            throw new NotEnoughMoneyException();
        }
        return finalMap;
    }


    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int totalAmount = 0;

        for (Map.Entry<Integer, Integer> element : denominations.entrySet())
        {
            totalAmount += element.getKey() * element.getValue();
        }

        return totalAmount;
    }
}