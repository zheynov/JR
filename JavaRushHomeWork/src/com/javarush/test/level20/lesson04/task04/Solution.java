package com.javarush.test.level20.lesson04.task04;

import java.io.*;

/* Как сериализовать static?
Сделайте так, чтобы сериализация класса ClassWithStatic была возможной
*/
public class Solution
{
    public static void main(String... args) throws IOException, ClassNotFoundException
    {
        ClassWithStatic cws = new ClassWithStatic();
        cws.i=5;
        cws.j =33;
        cws.staticString="hello_world!";

        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/1.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        outputStream.writeObject(cws);

        fileOutput.close();
        outputStream.close();

        FileInputStream fiStream = new FileInputStream("D:/JAVA/1.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Object objjj = objectStream.readObject();
        ClassWithStatic newCws = (ClassWithStatic) objjj;

        fiStream.close();
        objectStream.close();

        System.out.println(newCws.i);
        System.out.println(newCws.j);
        System.out.println(new ClassWithStatic().staticString);
    }

    public static class ClassWithStatic implements Serializable
    {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
    }
}
