package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        String s2 = reader.readLine();
        String s3 = reader.readLine();
        String s4 = reader.readLine();

        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int c = Integer.parseInt(s3);
        int d = Integer.parseInt(s4);

        if ((a > b) && (a > c) && (a > d))

            System.out.println(s1 + " максимальное число");

        else if ((b > c) && (b > d))
        {
            System.out.println(s2 + " максимальное число");
        }
        else
        {
            if ((c > d))
            {
                System.out.println(s3 + " максимальное число");
            }

            else
            {
                System.out.println(s4 + " максимальное число");
            }

        }

    }
}
