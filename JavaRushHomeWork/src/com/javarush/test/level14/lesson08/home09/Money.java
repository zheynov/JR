package com.javarush.test.level14.lesson08.home09;

public abstract class Money
{
    private double perem;

    public Money(double amount)
    {
         perem = amount;
    }

    public double getAmount()
    {
        return perem;
    }

    public abstract String getCurrencyName();

}

