package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable
{
    private String name;
    private LinkedBlockingQueue<Order> queue;

    private volatile boolean stopped = true;

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        while (stopped)
        {
            try
            {
                if (!queue.isEmpty())
                {
                    this.startCookingOrder(queue.take());
                }

                Thread.sleep(10);;
            }
            catch (InterruptedException ignore)
            {
            }

            if (queue.isEmpty())
            {
                stopped = false;
            }

        }
    }


    public void startCookingOrder(Order order)
    {
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        try
        {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException ignored)
        {
        }

        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, (order.getTotalCookingTime() * 60), order.getDishes()));

        setChanged();
        notifyObservers(order);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }


}
