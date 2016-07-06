package com.javarush.test.level20.lesson04.task05;

import java.io.*;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution
{
    public static void main(java.lang.String... args) throws IOException, ClassNotFoundException
    {
        Object obj = new Object();

        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/1.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        outputStream.writeObject(obj);

        fileOutput.close();
        outputStream.close();

        FileInputStream fiStream = new FileInputStream("D:/JAVA/1.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        java.lang.Object objjj = objectStream.readObject();
        Object newObj = (Object) objjj;

        fiStream.close();
        objectStream.close();
    }

    public static class Object implements Serializable
    {
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static class String implements Serializable
    {
        private final int number;

        public String()
        {
            number = ++countStrings;
        }

        public void print()
        {
            System.out.println("string #" + number);
        }
    }
}
