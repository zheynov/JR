package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        if (!Thread.currentThread().isInterrupted())
        {
            try
            {
                for (int i = 1; i <= 9; i++)
                {
                    String name = "ShareItem";
                    System.out.format("Элемент '%s-%d' добавлен%n", name, i);
                    queue.offer(new ShareItem(name + "-" + i, i));
                    Thread.sleep(100);
                    if (queue.hasWaitingConsumer())
                    {
                        System.out.println("Consumer в ожидании!");
                    }
                }
            }
            catch (InterruptedException ignored)
            {
            }
        }
    }
}
