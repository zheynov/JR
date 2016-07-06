package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
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
            if (data=='.') {
            fwr.write('!');}
            else  fwr.write(data);
        }

        frdr.close();
        fwr.close();
        bf.close();
    }
}
