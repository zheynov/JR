package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        InputStream inStream1 = new FileInputStream(fileName1);
        InputStream inStream2 = new FileInputStream(fileName2);


        byte[] buffer = new byte[1000];
        int count=0;

        while (inStream1.available() > 0)
        {
            count = inStream1.read(buffer);
        }

        OutputStream outputStream1 = new FileOutputStream(fileName1);

        while (inStream2.available() > 0)
        {
            int data2 = inStream2.read();
            outputStream1.write(data2);
        }
        outputStream1.write(buffer, 0, count);


        reader.close();
        inStream2.close();
        inStream1.close();
        outputStream1.close();
    }
}