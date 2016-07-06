package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
         args = new String[]{"F:/JAVA/111.txt", "F:/JAVA/222.txt"};
        ArrayList<String> list = new ArrayList();
        BufferedReader rdrLines = new BufferedReader(new FileReader(args[0]));
        FileWriter fwrtr = new FileWriter(args[1]);
        String s;

        while ((s = rdrLines.readLine()) != null)
        {
            String[] words = s.split(" ");

            for (String word : words)
            {
                if (word.length() > 6)
                    list.add(word);
            }
        }
        for (int i = 0; i < list.size() - 1; i++)
        {
            fwrtr.write(list.get(i) + ",");
        }
        fwrtr.write(list.get(list.size() - 1));

        rdrLines.close();
        fwrtr.close();
    }
}
