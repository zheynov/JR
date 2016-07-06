package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
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

        for (Map.Entry<String, Double> sss : map.entrySet())
        {
            System.out.println(sss.getKey() + " " + sss.getValue());
        }

        reader.close();
        frdr.close();
    }
}
