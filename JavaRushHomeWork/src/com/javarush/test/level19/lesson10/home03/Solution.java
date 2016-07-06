package com.javarush.test.level19.lesson10.home03;

import java.io.*;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {

    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException

    {
        //args = new String[]{"D:/JAVA/111.txt"};

        FileReader frdr = new FileReader(args[0]);

        BufferedReader reader = new BufferedReader(frdr);

        String line;

        while ((line = reader.readLine()) != null)
        {
            if (line.length()<1) {continue;}

            String name = line.replaceAll("[\\w]", "");
            name = name.substring(0, name.length()-3);

            String allDates = line.replaceAll("[\\D]", " ");
            String[] dates = allDates.split(" ");
            Date dateMain = new Date(Integer.parseInt(dates[dates.length-1])-1900, Integer.parseInt(dates[dates.length-2])-1, Integer.parseInt(dates[dates.length-3]));

            PEOPLE.add(new Person(name, dateMain));
        }

        reader.close();
        frdr.close();
    }
}
