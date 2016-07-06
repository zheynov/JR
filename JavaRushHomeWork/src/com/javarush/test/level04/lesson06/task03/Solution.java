package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
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

        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int c = Integer.parseInt(s3);

        if ((a > b) && (a > c))
        {
            if (b > c) System.out.println(s1 + "" + s2 + "" + s3);
            else System.out.println(s1 + "" + s3 + "" + s2);
        } else if ((b > a) && (b > c))
        {
            if (a > c) System.out.println(s2 + "" + s1 + "" + s3);
            else System.out.println(s2 + "" + s3 + "" + s1);
        } else if ((c > a) && (c > b))
        {
            if (a > b) System.out.println(s3 + "" + s2 + "" + s1);
            else System.out.println(s3 + "" + s1 + "" + s2);
        }
    }


}