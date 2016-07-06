package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        FileReader frdr = new FileReader(bf.readLine());
        String s = "";

        while (frdr.ready())         {
            int data = frdr.read();
            s = s + (char)data;
        }

        s = s.replaceAll("[\\p{Punct}\\r\\n]", " ");

        String[] allWords = s.toLowerCase().split(" ");
        int count=0;

        for (String element : allWords) {
            if (element.equals("world"))
            count++;
        }
        System.out.println(count);

        frdr.close();
        bf.close();
    }
}
