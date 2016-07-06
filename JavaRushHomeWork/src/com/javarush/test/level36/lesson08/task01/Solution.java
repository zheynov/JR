package com.javarush.test.level36.lesson08.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        SortedSet<Character> result = new TreeSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String str;

        while ((str = reader.readLine()) != null) {
            String temp = str.replaceAll("\\W", "");

            temp = temp.replaceAll("_", "");

            char[] res = temp.toLowerCase().toCharArray();

            for (char symbol : res) {
                if (!result.contains(symbol))
                    result.add(symbol);
            }
        }

        if (result.size() >= 5) {
            for (int i = 0; i < 5; i++)
            {
                System.out.print(result.first());
                result.remove(result.first());
            }
        } else {
            Object[] chacha = result.toArray();

            for (int i = 0; i < chacha.length; i++)
            {
                System.out.print(chacha[i]);
            }
        }
        reader.close();
    }
}
