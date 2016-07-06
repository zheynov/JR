package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution
{
    public static Integer[] sort(Integer[] array)
    {
        //implement logic here

        Arrays.sort(array);

        double mediana = 0;

        for (int i = 0; i < array.length; i++)
        {


            if (array.length % 2 == 0)
            {
                mediana = (array[array.length / 2 - 1] + array[array.length / 2]) / 2.0;
            } else
            {
                mediana = array[array.length / 2];
            }
        }


        final double finalMediana = mediana;

        Arrays.sort(array, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int sortedvalue;

                int result1 = (int) Math.abs((o1 - finalMediana));
                int result2 = (int) Math.abs((o2 - finalMediana));

                sortedvalue =  result1 - result2;

                return sortedvalue;
            }
        });

        System.out.println("mediana is: " + finalMediana);

        return array;
    }

    public static void main(String[] args)
    {
        Integer[] mass = new Integer[]{3, 4, 7, -1, 5, 2, 1, -18, 11, 5, 48, 49, -23, 92, 94};
        sort(mass);

        for (Integer element : mass)
        {
            System.out.print(element + " ");
        }
    }
}
