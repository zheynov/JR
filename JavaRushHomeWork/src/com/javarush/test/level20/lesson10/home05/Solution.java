package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        FileOutputStream fileOutput = new FileOutputStream("D:/JAVA/111.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        FileInputStream fiStream = new FileInputStream("D:/JAVA/111.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Person bOld = new Person("Вася", "Иванов", "Russia", Sex.MALE);
        outputStream.writeObject(bOld);
        outputStream.close();

        Person bNew = (Person) objectStream.readObject();
        System.out.println(bNew.firstName);
        System.out.println(bNew.lastName);
        System.out.println(bNew.fullName);
        System.out.println(bNew.country);
        System.out.println(bNew.greetingString);
        System.out.println(bNew.sex);
        System.out.println(bNew.logger);
        System.out.println(bNew.outputStream);

        objectStream.close();
    }

    public static class Person implements Serializable
    {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        private void writeObject(ObjectOutputStream stream) throws IOException
        {
            stream.defaultWriteObject();
        }

        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
        {
            stream.defaultReadObject();
            outputStream = System.out;
            logger = Logger.getLogger(String.valueOf(Person.class));
            fullName = String.format("%s, %s", lastName, firstName);
        }
    }

    enum Sex
    {
        MALE,
        FEMALE
    }
}
