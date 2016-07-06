package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли. F:\JAVA\111.txt
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream inStream = new FileInputStream(reader.readLine());

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inStream));

        inStream.close(); //закрываем потоки
        reader.close();
        reader2.close();

     ArrayList<Integer> list = new ArrayList<>();

        String line;

        while ((line = reader2.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }

        Collections.sort(list);

        for (Integer digits : list)
        {
            if (digits%2==0)
                System.out.println(digits);
        }


    }
}
