package com.javarush.test.level07.lesson12.home02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Переставить M первых строк в конец списка
Ввести с клавиатуры 2 числа N  и M.
Ввести N строк и заполнить ими список.
Переставить M первых строк в конец списка.
Вывести список на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> mainlist = new ArrayList<Integer>();

        int n =Integer.parseInt(reader.readLine());
        int m =Integer.parseInt(reader.readLine());

        for (int i=0; i<n; i++)
        {
            mainlist.add(Integer.parseInt(reader.readLine()));
        }


        for (int i=1; i<=m; i++)
        {
            int x = mainlist.remove(0);
            mainlist.add(n-1, x);
        }


        for (int i=0; i<mainlist.size(); i++)
        {
            System.out.println(mainlist.get(i));
        }
    }
}
