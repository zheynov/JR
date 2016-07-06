package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class freeee
{

    public static void main(String[] args) throws InterruptedException    {

            N5 n5 = new N5();
            n5.start();

    }

    public static class N5 extends Thread
    {

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
