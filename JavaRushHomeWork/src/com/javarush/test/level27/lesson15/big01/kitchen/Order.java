package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

public class Order
{
    protected List<Dish> dishes;
    private Tablet tablet;

    public Tablet getTablet()
    {
        return tablet;
    }

    public Order(Tablet tablet) throws IOException
    {
        this.dishes =  initDishes();
        this.tablet = tablet;
    }

    protected List<Dish> initDishes() throws IOException
    {
       return ConsoleHelper.getAllDishesForOrder();
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime()
    {
        int timeSec = 0;

        for (Dish dish : dishes)
        {
            timeSec += dish.getDuration();
        }

        return timeSec;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    @Override
    public String toString()
    {
        if (dishes.isEmpty() || dishes.size() == 0)
        {
            return "";
        }

        return "Your order: " + dishes.toString() +" of " + tablet.toString();
    }
}
