package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution
{
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        ArrayList<String> allLines = new ArrayList<>();
        ArrayList<String> allReversed = new ArrayList<>();

        BufferedReader mainReader = new BufferedReader(new InputStreamReader(System.in));
        String filename = mainReader.readLine();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while ((line = reader.readLine()) != null)
        {
            String[] str;
            if (line.isEmpty())
                continue;

            else str = line.split(" ");

            for (String stroka : str)
            {
                allLines.add(stroka);

                StringBuilder s2 = new StringBuilder(stroka);
                s2.reverse();

                allReversed.add(s2.toString());
            }
        }


        for (int i = 0; i < allLines.size(); i++)
        {
            for (int j = 0; j < allReversed.size(); j++)
            {
                if (i < allLines.size() && j < allReversed.size() && allLines.size() > 0 && allReversed.size() > 0 && i != j && allLines.get(i).equals(allReversed.get(j)))
                {
                    Pair pair = new Pair();
                    pair.first = allLines.get(i);
                    pair.second = new StringBuilder(allReversed.get(j)).reverse().toString();
                    result.add(pair);

                    allLines.remove(i);
                    allReversed.remove(j);
                    j--;


                } else j++;
            }
        }

        for (Pair ss: result)
        {
            System.out.println(ss);
        }

        reader.close();
        mainReader.close();

    }

    public static class Pair
    {
        String first;
        String second;

        @Override
        public String toString()
        {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;
        }
    }
}
