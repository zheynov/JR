package com.javarush.test.level28.lesson06.home01;

public class MyThread extends Thread
{
    static int count = 0;

    public MyThread()
    {
        priorityMethod();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        priorityMethod();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        priorityMethod();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        priorityMethod();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        priorityMethod();
    }

    public MyThread(String name)
    {
        super(name);
        priorityMethod();
    }

    public MyThread(Runnable target)
    {
        super(target);
        priorityMethod();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        priorityMethod();
    }

    private void priorityMethod()
    {
        try
        {
            this.setPriority(Thread.MIN_PRIORITY + count);
        }
        catch (Exception ignored)
        {
        }

        if (count >= 0 && count < 10)
        {
            count++;
        }

        if (count == 10)
        {
            count = 0;
        }
    }
}
