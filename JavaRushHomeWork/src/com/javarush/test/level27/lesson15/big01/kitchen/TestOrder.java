package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    protected List<Dish> initDishes() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        Collections.addAll(dishes, Dish.values());
        Collections.shuffle(dishes);
        return dishes;
    }
}
