package com.javarush.test.level25.lesson05.home01;

class LoggingStateThread extends Thread
{
    Thread target;

    public LoggingStateThread(Thread target)
    {
        System.out.println(target.getState());
        setDaemon(true);
        this.target = target;

    }

    @Override
    public void run()
    {
        State state = target.getState();
        System.out.println(state);

        while (state != State.TERMINATED)
        {
            if (state != target.getState())
            {
                state = target.getState();
                System.out.println(state);
            }
        }
    }
}




