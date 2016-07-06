package com.javarush.test.level18.lesson08.task03;

import java.io.*;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream {

    private FileOutputStream orig;

    public AmigoOutputStream (FileOutputStream inst)  throws FileNotFoundException
    {
        super(fileName);
        this.orig=inst;

    }

    public AmigoOutputStream(String name) throws FileNotFoundException
    {
        super(name);
    }

    public AmigoOutputStream(String name, boolean append) throws FileNotFoundException
    {
        super(name, append);
    }

    public AmigoOutputStream(File file) throws FileNotFoundException
    {
        super(file);
    }

    public AmigoOutputStream(File file, boolean append) throws FileNotFoundException
    {
        super(file, append);
    }

    public AmigoOutputStream(FileDescriptor fdObj)
    {
        super(fdObj);
    }


    public static String fileName = "C:/tmp/result.txt";


    public void write(int b) throws IOException {
        orig.write(b);
    }


    public void write(byte b[]) throws IOException {
        orig.write(b);
    }

    public void write(byte b[], int off, int len) throws IOException {
        orig.write(b, off, len);
    }

    public void close() throws IOException {

        orig.flush();
        orig.write("JavaRush © 2012-2013 All rights reserved.".getBytes());
        orig.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}