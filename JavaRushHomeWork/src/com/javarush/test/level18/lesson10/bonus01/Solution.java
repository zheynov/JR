package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
       //args = new String[]{"-e", "F:/JAVA/111.txt", "F:/JAVA/222.txt"};
       // args = new String[]{"-d", "F:/JAVA/222.txt", "F:/JAVA/333.txt"};

        InputStream fileName = new FileInputStream(args[1]);
        OutputStream fileOutputName = new FileOutputStream(args[2]);

        char b = 'b';

        if (args[0].equals("-e"))
        {
            while (fileName.available() > 0)
            {
                int data = (char) fileName.read();
                fileOutputName.write((char) (data) + b);
            }
        }

        else  if (args[0].equals("-d")) {
            while (fileName.available() > 0)
            {
                int data = (char) fileName.read();
                fileOutputName.write((char) (data) - b);
            }
        }

        fileName.close();
        fileOutputName.close();
    }
}
