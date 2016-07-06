package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.*;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //...
        ArrayList<String> allLines = new ArrayList<>();


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
            }
        }

        String[] massive = new String[allLines.size()];

        for (int i = 0; i < allLines.size(); i++)
        {
            massive[i] = allLines.get(i);
        }

        StringBuilder result = getLine(massive);
        System.out.println(result.toString());

        reader.close();
        mainReader.close();
    }

    public static boolean check(ArrayList<String> allLines)
    {
        boolean argue = false;
        int cout = 0;

        for (int i = 0; i < allLines.size(); i++)
        {
            for (int j = 1; j < allLines.size(); j++)
            {
                if (i < allLines.size() && j < allLines.size() && allLines.get(i).length() > 0 && allLines.get(j).length() > 0 && allLines.size() > 0

                        && allLines.get(i).toLowerCase().charAt(allLines.get(i).length() - 1) == allLines.get(j).toLowerCase().charAt(0))
                {
                    cout++;
                    i++;
                } else
                {
                    break;
                }
            }
        }

        if (cout == allLines.size() - 1)
        {
            argue = true;
        }

        return argue;
    }

    public static StringBuilder getLine(String... words)
    {
        if (words == null || words.length == 0) return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1) return new StringBuilder(words[0]);

        ArrayList<String> allLines = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        for (String sss : words)
        {
            allLines.add(sss);
        }

        while (!check(allLines))

        {
            Collections.shuffle(allLines);
        }

        for (String sss : allLines)
        {
            builder.append(sss + " ");
        }

        return builder;
    }
}
