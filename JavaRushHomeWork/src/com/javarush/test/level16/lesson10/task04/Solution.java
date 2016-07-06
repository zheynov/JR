package com.javarush.test.level16.lesson10.task04;

/* А без interrupt слабо?
Разберись, как работает программа.
Сделай так, чтобы в методе ourInterruptMethod можно было сделать так, чтобы нить TestThread завершилась сама.
Нельзя использовать метод interrupt.
*/

public class Solution
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread t = new Thread(new TestThread());
        t.start();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod()
    {
        TestThread.isCancel = false;
    }

    public static class TestThread implements Runnable
    {

        public static boolean isCancel = true;

        public void run()
        {
            while (isCancel)
            {
                try
                {
                    System.out.println("he-he");
                    Thread.sleep(500);

                }
                catch (InterruptedException e)
                {

                }
            }
        }
    }
}
