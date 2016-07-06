package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        args = new String[]{"span"};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        String mainLine = "";

        BufferedReader lines = new BufferedReader(new FileReader(fileName));
        String s;

        while ((s = lines.readLine()) != null)
        {
            mainLine = mainLine + s;
        }
        mainLine = mainLine.substring(mainLine.indexOf("<"), mainLine.lastIndexOf(">") + 1);

        lines.close();
        reader.close();

        Pattern p1 = Pattern.compile("<" + args[0] + "\\s?\\n?\\w?\\r?\\p{Z}?>?"), p2 = Pattern.compile("</" + args[0] + ">");

        Matcher m1 = p1.matcher(mainLine), m2 = p2.matcher(mainLine);

        String temp = "";

        while (true)
        {
            if (m1.find() && m2.find())
            {
                temp = mainLine.substring(m1.start(), m2.end());

                if (temp.substring(2, temp.length() - 2).contains("<" + args[0]))
                {
                    int countm1 = 0, countm2 = 0;
                    Matcher z1 = p1.matcher(temp), z2 = p2.matcher(temp);

                    while (z2.find())
                    {
                        countm2++;
                    }
                    while (z1.find())
                    {
                        countm1++;
                    }

                    while (countm1 > countm2)
                    {
                        String ssss = mainLine.substring(mainLine.indexOf(temp) + temp.length(),
                                mainLine.indexOf("</" + args[0], mainLine.indexOf(temp) + temp.length()) + args[0].length() + 3);
                        temp = temp + ssss;
                        if (!ssss.contains("<" + args[0]))
                        {
                            countm1--;
                        }
                    }
                }
                if (!temp.substring(2, temp.indexOf("</" + args[0])).contains("<" + args[0]))
                {
                    temp = temp.substring(0, temp.indexOf("</" + args[0]) + args[0].length() + 3);
                }

                System.out.println(temp);
            } else
            {
                break;
            }
        }
    }
}


