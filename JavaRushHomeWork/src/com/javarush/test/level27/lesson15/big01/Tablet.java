package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet
{
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    private final int number;

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number)
    {
        this.number = number;
    }


    public void createOrder()
    {
        Order order;

        try
        {
            order = new Order(this);
            mmmmmmmm(order);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void mmmmmmmm(Order order)
    {
        AdvertisementManager advertisementManager;
        if (!order.isEmpty())
        {
            ConsoleHelper.writeMessage(order.toString());

            try
            {
                queue.put(order);
                advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }
            catch (NoVideoAvailableException e)
            {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
            catch (InterruptedException ignored)
            {

            }
        }
    }

    public void createTestOrder()
    {
        TestOrder order=null;

        try
        {
            order = new TestOrder(this);
            mmmmmmmm(order);
        }

        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }


    @Override
    public String toString()
    {
        return "Tablet{" + "number=" + number + '}';
    }
}
