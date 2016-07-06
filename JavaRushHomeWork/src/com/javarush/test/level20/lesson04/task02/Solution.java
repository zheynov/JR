package com.javarush.test.level20.lesson04.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Как сериализовать JavaRush?
Сделайте так, чтобы сериализация класса JavaRush была возможной
*/
public class Solution {

    public static void main(String[] args) throws Exception
    {
        JavaRush jr = new JavaRush();
        jr.users.add(new User());
        jr.users.get(0).setCountry(User.Country.RUSSIA);
        jr.users.get(0).setFirstName("Valentin");
        jr.users.get(0).setBirthDate(new Date(88, 11, 11));


        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/1.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        outputStream.writeObject(jr);

        fileOutput.close();
        outputStream.close();

        FileInputStream fiStream = new FileInputStream("D:/JAVA/1.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Object objjj = objectStream.readObject();

        JavaRush newJr = (JavaRush) objjj;

        System.out.println(newJr.users.get(0).getFirstName());
        System.out.println(newJr.users.get(0).getCountry().getDisplayedName());
        System.out.println(newJr.users.get(0).getBirthDate());

        fiStream.close();
        objectStream.close();
    }

    public static class JavaRush implements Serializable{
        public List<User> users = new ArrayList<>();
    }
}
