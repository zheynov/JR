package com.javarush.test.level06.lesson11.bonus01;
import java.io.*;

/* Нужно исправить программу, чтобы компилировалась и работала
Задача: Программа вводит два числа с клавиатуры и выводит их максимум в виде «Max is 25»
*/

public class Solution
{
    public static int max;
    public static void main(String[] args) throws IOException

    {
        BufferedReader  reader  = new BufferedReader(new InputStreamReader(System.in));

        String max = "Max is ";
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
         int max1 = a > b ? a : b;

        System.out.println(max + max1);
    }

}
