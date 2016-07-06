package com.javarush.test.level04.lesson10.task66;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Solution
{
    public static void main(String[] args) throws Exception
    {

        int i = 1;

        while (i <= 10)
        {
            int j = 1;

                   while (j <10)
                   {
                       System.out.print(i*j + " ");
                    j++;
                 }

            System.out.println(i * j);
        i++;

    }

    }
}