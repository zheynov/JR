package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        FileReader frdr = new FileReader(bf.readLine());
        FileWriter fwr = new FileWriter(bf.readLine());

        String s = "";

        while (frdr.ready())         {
            int data = frdr.read();
            s = s + (char)data;
        }

        String[] allDigits = s.toLowerCase().split(" ");


        for (String lines : allDigits )
        {
            try { fwr.write(Integer.parseInt(lines) + " "); }
            catch (Exception e)
            {}
        }

        frdr.close();
        fwr.close();
        bf.close();
    }
}
