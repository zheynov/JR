package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] mass = new int[5];

        for (int k = 0; k < 5; k++)
        {
            mass[k] = Integer.parseInt(reader.readLine());
        }

        int t;

        for (int i = 0; i < mass.length; i++)
            for (int j = i + 1; j < mass.length; j++)
                if (mass[j] < mass[i])
                {
                    t = mass[i];
                    mass[i] = mass[j];
                    mass[j] = t; }

                    for (int h = 0; h < 5; h++)
                    {
                        System.out.println(mass[h]);
                    }
                    }
}


