package com.javarush.test.level07.lesson04.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Максимальное среди массива на 20 чисел
1. В методе initializeArray():
1.1. Создайте массив на 20 чисел
1.2. Считайте с консоли 20 чисел и заполните ими массив
2. Метод max(int[] array) должен находить максимальное число из элементов массива
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int[] array = initializeArray();
        int max = max(array);
        System.out.println(max);
    }
    public static int[] initializeArray() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[20];

        for (int i = 0; i < numbers.length; i++)
        {
            String s = reader.readLine();
            numbers[i] = Integer.parseInt(s);
        }

        return numbers;
    }

    public static int max(int[] array) {

        int maximum = array[0];

        for (int i = 0; i < array.length; i++)
        {
           if (array[i] > maximum)
            maximum = array[i];
        }
        return maximum;
    }
}
