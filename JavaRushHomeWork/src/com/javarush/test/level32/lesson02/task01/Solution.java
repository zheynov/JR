package com.javarush.test.level32.lesson02.task01;


import java.io.IOException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");

        long fileLength = raf.length();
        long bytescount = Long.parseLong(args[1]);

        if (fileLength >= bytescount) {
            raf.seek(bytescount);
            raf.writeBytes(args[2]);
        } else {
            raf.seek(fileLength);
            raf.writeBytes(args[2]);
        }
        raf.close();
    }
}
