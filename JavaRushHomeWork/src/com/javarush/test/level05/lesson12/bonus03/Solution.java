package com.javarush.test.level05.lesson12.bonus03;

import java.io.*;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception

    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum =Integer.MIN_VALUE;
        int n = Integer.parseInt(reader.readLine());
        int i = 1;
        int a;

            while (i<=n)
            {
                a = Integer.parseInt(reader.readLine());
                i++;

                if (a >maximum)
                    maximum = a;
            }
            System.out.println(maximum);

    }

}