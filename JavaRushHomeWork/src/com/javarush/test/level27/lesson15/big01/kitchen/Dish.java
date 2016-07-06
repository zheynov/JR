package com.javarush.test.level27.lesson15.big01.kitchen;

public enum Dish
{
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int i)
    {
        duration = i;
    }

    public int getDuration()
    {
        return duration;
    }

    public static String allDishesToString()
    {
        String allTheDishes = "";

        for (int i = 0; i < Dish.values().length - 1; i++)
        {
            allTheDishes += Dish.values()[i] + ", ";
        }

        return allTheDishes + Dish.values()[Dish.values().length - 1];
    }
}
