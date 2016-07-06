package com.javarush.test.level04.lesson10.task03;

import java.io.*;

/* Хорошего не бывает много
Ввести с клавиатуры строку и число N.
Вывести на экран строку N раз используя цикл while.
Пример ввода:
абв
2
Пример вывода:
абв
абв
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

        String s1 = reader.readLine();
        String s2 = reader.readLine();

        int a = Integer.parseInt(s2);
        int i=1;

        while (i <= a)
        {
            System.out.println(s1);
            i++;
        }

    }
}
