package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
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

        FileInputStream inputStream = new FileInputStream(fileName1);

        FileOutputStream outputStream2 = new FileOutputStream(fileName2);
        FileOutputStream outputStream3 = new FileOutputStream(fileName3);

        while (inputStream.available() > 0)
        {

            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);

            if (count % 2 == 0)  {

                outputStream2.write(buffer, 0, count/2);
                outputStream3.write(buffer, count/2, count/2);
            }
            else {

                outputStream2.write(buffer, 0, (count/2)+1);
                outputStream3.write(buffer, ((count/2)+1), count/2);
            }
        }

        inputStream.close();
        reader.close();
        outputStream2.close();
        outputStream3.close();
    }
}
