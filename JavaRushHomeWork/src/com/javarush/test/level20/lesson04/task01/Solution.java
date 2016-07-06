package com.javarush.test.level20.lesson04.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Как сериализовать?
Сделайте так, чтобы сериализация класса Human была возможной
*/
public class Solution
{

    public static void main(String[] args) throws Exception
    {
        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/1.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        Human human = new Human("Vasya", new Asset("ooooo"), new Asset("ttttt"));
        human.assets.get(0).setPrice(111d);
        human.assets.get(1).setPrice(222d);

        outputStream.writeObject(human);

        fileOutput.close();
        outputStream.close();

        FileInputStream fiStream = new FileInputStream("D:/JAVA/1.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Object objjj = objectStream.readObject();
        Human newHuman = (Human) objjj;

        System.out.println(newHuman.name);
        System.out.println(newHuman.assets.get(0).getName());
        System.out.println(newHuman.assets.get(0).getPrice());
        System.out.println(newHuman.assets.get(1).getName());
        System.out.println(newHuman.assets.get(1).getPrice());

        fiStream.close();
        objectStream.close();
    }

    public static class Human implements Serializable
    {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human()
        {
        }

        public Human(String name, Asset... assets)
        {
            this.name = name;
            if (assets != null)
            {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
    }
}
