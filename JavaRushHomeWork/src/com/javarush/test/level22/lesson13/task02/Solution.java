package com.javarush.test.level22.lesson13.task02;

import java.io.*;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/ // тут просто переводим UTF-8 -> Windows-1251
public class Solution
{
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException
    {
        //args = new String[] {"D:/JAVA/111.txt", "D:/JAVA/222.txt"};

        FileInputStream inStream = new FileInputStream(args[0]);
        FileOutputStream writer = new FileOutputStream(args[1]);

        byte[] buffer = new byte[inStream.available()];
        inStream.read(buffer);

        //байты имеют свою кодировку, их нужно вычитать и сконвертить в
        String s = new String(buffer, "UTF-8");

        buffer = s.getBytes("Windows-1251");
        String s2 = new String(buffer);

        writer.write(s2.getBytes());

        inStream.close();
        writer.close();
    }
}
