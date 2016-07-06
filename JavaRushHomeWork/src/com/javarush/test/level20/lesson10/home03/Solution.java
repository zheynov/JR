package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable
{

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/111.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        FileInputStream fiStream = new FileInputStream("D:/JAVA/111.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        B bOld = new Solution().new B("Вася");
        outputStream.writeObject(bOld);
        outputStream.close();
        System.out.println(bOld.name);

        B bNew = (B) objectStream.readObject();
        System.out.println(bNew.name);
        objectStream.close();
    }

    public static class A
    {
        protected String name = "A";

        public A()
        {
        }

        public A(String name)
        {
            this.name += name;
        }
    }

    public class B extends A implements Serializable
    {
        public B(String name)
        {
            super(name);
            this.name += name;
        }

        private void writeObject(ObjectOutputStream stream) throws IOException
        {
            stream.defaultWriteObject();
            stream.writeObject(this.name);
        }

        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
        {
            stream.defaultReadObject();
            name = (String) stream.readObject();
        }
    }
}
