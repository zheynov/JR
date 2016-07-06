package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException     {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        InputStream inStream = new FileInputStream(fileName1);

        StringBuilder sb = new StringBuilder();

        while (inStream.available() > 0)
        {
            int data = (char)inStream.read();
            sb.append((char) data);
        }

        String mainString = sb.toString();

        String[] lines = mainString.split(" ");

        OutputStream outputStream = new FileOutputStream(fileName2);

        for (String s : lines)
        {
            outputStream.write((String.valueOf(Math.round(Double.parseDouble(s))+" ")).getBytes());
        }

        reader.close();
        inStream.close();
        outputStream.close();
    }
}
