package com.javarush.test.level07.lesson04.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* Один большой массив и два маленьких
1. Создать массив на 20 чисел.
2. Ввести в него значения с клавиатуры.
3. Создать два массива на 10 чисел каждый.
4. Скопировать большой массив в два маленьких: половину чисел в первый маленький, вторую половину во второй маленький.
5. Вывести второй маленький массив на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] bigdata = new int[20];

        int[] small1 = new int[10];
        int[] small2 = new int[10];

        for (int i=0; i<bigdata.length; i++)
        {
            bigdata[i] = Integer.parseInt(reader.readLine());
        }

        small1 = Arrays.copyOf(bigdata,10); // копирование 10-ти элементов. Берутся с первого по 10-й

        small2 = Arrays.copyOfRange(bigdata, 10, 20); // копирование с 10-го по 20-й элементы


        for (int k=0; k<small2.length; k++)
        {
            System.out.println(small2[k]); // выводим содержимое второго массива
        }

    }
}
