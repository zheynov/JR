package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        if ((s.isEmpty() != true)&&(s.contains("?")))
        {
            String[] lines = s.substring(s.indexOf("?")+1, s.length()).split("&");

            List<String> list = new ArrayList<>();

            for (String line : lines)
            {
                String curPar;
                if (line.contains("="))
                {
                    curPar = line.substring(0, line.indexOf("="));

                    if (curPar.isEmpty() != true)
                        list.add(curPar);
                } else if ((line.isEmpty() == false)) list.add(line);
            }

            for (int i = 0; i < list.size() - 1; i++)

            {
                if (list.get(i).isEmpty() != true)
                    System.out.print(list.get(i) + " ");
            }
            System.out.print(list.get(list.size() - 1));

            List<String> listObj = new ArrayList<>();
            String URL2 = s.substring(s.indexOf("?") + 1, s.length());
            String[] lines2 = URL2.split("&");

            for (String line1 : lines2)
            {
                if ((line1.contains("obj")) && (line1.isEmpty() != true))
                {
                    if ((line1.substring(0, line1.indexOf("=")).equals("obj")))
                        listObj.add(line1.substring(line1.indexOf("=") + 1, line1.length()));
                }
            }

            for (String line2 : listObj)
            {
                System.out.println();
                try
                {
                    alert(Double.parseDouble(line2));
                }
                catch (Exception e)
                {
                    alert(line2);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}