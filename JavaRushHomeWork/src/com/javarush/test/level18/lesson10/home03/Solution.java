package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();

        InputStream inStream2 = new FileInputStream(fileName2);
        InputStream inStream3 = new FileInputStream(fileName3);

        OutputStream outputStream1 = new FileOutputStream(fileName1);

        while (inStream2.available() > 0)
        {
            int data = inStream2.read();
            outputStream1.write(data);
        }

        while (inStream3.available() > 0)
        {
            int data = inStream3.read();
            outputStream1.write(data);
        }

        reader.close();
        inStream2.close();
        inStream3.close();
        outputStream1.close();
    }
}
