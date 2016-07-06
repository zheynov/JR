package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {

    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {

        threads.add(new N1());
        threads.add(new N2());
        threads.add(new N3());
        threads.add(new N4());
        threads.add(new N5());
    }


    public static class N1 extends Thread {

        public void run() {

            while (true)
            {
            }
        }
    }

    public static class N2 extends Thread
    {

        public void run()
        {
            try
            {
                throw new InterruptedException();
            }

            catch (InterruptedException e)
            {
                System.out.println("InterruptedException");
            }
        }
    }

        public static class N3 extends Thread    {

        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public static class N4 extends Thread implements Message
    {
        @Override
        public  void showWarning()
        {
            this.interrupt();
            try
            {
                this.join();
            }

            catch(Exception e)
            {
            }
        }

        @Override
        public void run()
        {
            while(!Thread.currentThread().interrupted())
            {
            }
        }
    }


    public static class N5 extends Thread {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public void run() {
            int sum = 0;
           try {
                while (true)  {

                    String s = reader.readLine();

                    if (s.equals("N"))
                    {
                        break;
                    }

                    else

                    {
                        sum = sum + Integer.parseInt(s);
                    }
                }

                System.out.println(sum);
            }

           catch (NumberFormatException e1) {
               System.out.println(sum);
           }
           catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

