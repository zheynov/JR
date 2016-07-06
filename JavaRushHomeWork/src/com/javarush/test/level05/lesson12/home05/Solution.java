package com.javarush.test.level05.lesson12.home05;
import java.io.*;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0, i=0;
        String s;

        while (!"сумма".equals(s = reader.readLine()))

        {
            i=Integer.parseInt(s);
            sum = i + sum;

             }

        System.out.print(sum);

    }
}
