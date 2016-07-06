package com.javarush.test.level07.lesson09.task01;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Три массива
1. Введи с клавиатуры 20 чисел, сохрани их в список и рассортируй по трём другим спискам:
Число делится на 3 (x%3==0), делится на 2 (x%2==0) и все остальные.
Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.
2. Метод printList должен выводить на экран все элементы списка с новой строки.
3. Используя метод printList выведи эти три списка на экран. Сначала тот, который для x%3, потом тот, который для x%2, потом последний.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> mainlist = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++)
        {
            mainlist.add(Integer.parseInt(reader.readLine()));
        }

        ArrayList<Integer> three = new ArrayList<Integer>();  //нечётные
        ArrayList<Integer> two = new ArrayList<Integer>();    //чётные
        ArrayList<Integer> other = new ArrayList<Integer>();  //остальные

        for (int i = 0; i < mainlist.size(); i++)
        {
            Integer x = mainlist.get(i);

            if ((x % 3 == 0)&&(x % 2 == 0))
            {three.add(x);
                two.add(x); }   // если число попадают в 2 массива. Например 12 или 6 делятся и на 3 и на 2.

            else if (x % 3 == 0)
                three.add(x);    // если делится на 3

            else if (x % 2 == 0)
                two.add(x);   // добавляем x в коллекцию четных чисел

            else other.add(x); // добавляем x в коллекцию остальных
        }

        printList(three);
        printList(two);
        printList(other);
    }
    public static void printList(List<Integer> list)
    {

        for (int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
}

