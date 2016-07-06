package com.javarush.test.level04.lesson16.home01;

/* Я никогда не буду работать за копейки
Используя цикл вывести на экран сто раз надпись:
«Я никогда не буду работать за копейки. Амиго»
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        String s = "Я никогда не буду работать за копейки. Амиго";

        for (int i=1; i<=100; i++)
        {
            System.out.println(s);
        }
    }
}
