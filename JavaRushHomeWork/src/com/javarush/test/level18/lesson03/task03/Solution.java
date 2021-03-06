package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(fileName);

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            list.add(data);
        }

        for (int i = 0; i < list.size(); i++)
        {
            int count;
            int s = list.get(i);

            count = Collections.frequency(list, s);
            map.put(s, count);
        }

        inputStream.close();

        int max = 0;

        for (Map.Entry<Integer, Integer> value : map.entrySet())
        {
            if (value.getValue() >= max)
            {
                max = value.getValue();
            }
        }

        for (Map.Entry<Integer, Integer> value : map.entrySet())
        {
            if(max==value.getValue())
                result.add(value.getKey());
        }

        for (Integer rrr : result)
            {
                System.out.print(rrr + " ");
            }
    }
}