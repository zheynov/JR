package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter extends FileWriter{


    public FileConsoleWriter(FileDescriptor fd)
    {
        super(fd);
    }

    public FileConsoleWriter(File file) throws IOException
    {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException
    {
        super(file, append);
    }

    public FileConsoleWriter(String fileName) throws IOException
    {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException
    {
        super(fileName, append);
    }

    @Override
    public void write(int c) throws IOException
    {
        super.write(c);
        System.out.println((char) c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        super.write(cbuf, off, len);
        String s = new String(cbuf, off, len);
        System.out.println(s);
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        super.write(str, off, len);
        String s = str.substring(off, len);
        System.out.println(s);
    }


    @Override
    public void write(char[] cbuf) throws IOException
    {
        super.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException
    {
        super.write(str);
    }

    public static void main (String... args) throws IOException
    {
        char[] temp = new char[]{'f','s','4'};
        String s = "F:/JAVA/333.txt";
        FileConsoleWriter fcWr = new FileConsoleWriter(s);
        fcWr.write(39);
        fcWr.close();
    }
}
