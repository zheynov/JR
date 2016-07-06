package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
       ArrayList<int[]> digits = new ArrayList<int[]>();

        digits.add(new int[]{1, 2, 3, 4, 5});
        digits.add(new int[]{4, 5});
        digits.add(new int[]{4, 7, 8, 5});
        digits.add(new int[]{1, 4, 7, 6, 8, 5, 2});
        digits.add(new int[]{});

        return digits;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
