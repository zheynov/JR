package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


public class Solution {

    public static void main(String[] args) throws IOException
    {
        args = new String[]{"D:/JAVA/111.txt"};

        InputStream inStream = new FileInputStream(args[0]);

        int count = 0;
        int mainCount = 0;
        while (inStream.available() > 0)
        {
            char symb = (char) inStream.read();
            if (symb == ' ') {  count++; }
            mainCount++;
        }

        double result = (double) count / mainCount * 100;


        System.out.println(roundUp(result, 2));
        inStream.close();
    }

    public static BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}