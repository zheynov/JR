package com.javarush.test.level08.lesson11.home04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Минимальное из N чисел
1. Ввести с клавиатуры число N.
2. Считать N целых чисел и заполнить ими список - метод getIntegerList.
3. Найти минимальное число среди элементов списка - метод getMinimum.
*/

public class Solution
{
    public static void main(String[] args) throws Exception {

        List<Integer> list = getIntegerList();

        System.out.println(getMinimum(list));
    }

    public static int getMinimum(List<Integer> list) {

        int min = Integer.MAX_VALUE;

        for (int i=0; i<list.size(); i++)
        {
            if (list.get(i) < min)
                min = list.get(i);
        }

        return min;
    }

    public static List<Integer> getIntegerList() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<Integer> list = new ArrayList<Integer>();

        for (int i=0; i<n; i++)
        {
            list.add(Integer.parseInt(reader.readLine()));
        }

        return list;
    }
}