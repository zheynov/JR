package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла. D:\JAVA\111.txt
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;


public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        OutputStream outStream = new FileOutputStream(file);

        while (true)
        {
            String s = reader.readLine();
            if (("exit").equals(s))
            {
                outStream.write(s.getBytes());
                break;
            }

            else
            outStream.write(s.getBytes());
            outStream.write('\n');
        }

        outStream.close(); //закрываем поток
        reader.close(); //закрываем поток
    }
}

