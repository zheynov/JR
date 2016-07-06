package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.*;
import java.util.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        TreeMap<String,Double> map = new TreeMap<>();

        FileReader frdr = new FileReader(args[0]);

        BufferedReader reader = new BufferedReader(frdr);

        String line;

        while ((line = reader.readLine()) != null)
        {
            String[] massive = line.split(" ");

            if (map.containsKey(massive[0]))
            {
                map.put(massive[0], Double.parseDouble(massive[1]) + map.get(massive[0]));
            }

            else
            {
                map.put(massive[0], Double.parseDouble(massive[1]));
            }
        }

        reader.close();
        frdr.close();

        Double maxValue = 0.0;

        for (Map.Entry<String, Double> sss : map.entrySet())
        {
                if (sss.getValue()>maxValue) {
                    maxValue=sss.getValue();
                }
        }

        for (Map.Entry<String, Double> sss : map.entrySet())
        {
                if (sss.getValue()==maxValue)
                    System.out.println(sss.getKey());
        }

    }
}
