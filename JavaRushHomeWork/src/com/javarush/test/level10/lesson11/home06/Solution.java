package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String name;
        int age;
        int weight;
        String childrenName;
        String city;
        int houseNumber;


        public Human (int age, String childrenName, int weight, String name, int houseNumber, String city)
        {
            this.weight=weight;
            this.city=city;
            this.houseNumber=houseNumber;
            this.age=age;
            this.childrenName=childrenName;
            this.name=name;
        }

        public Human (int age, String city, int weight)
        {
            this.age=age;
            this.city=city;
            this.weight=weight;
        }

        public Human (String childrenName, int weight, String name, int houseNumber, String city)
        {
            this.weight=weight;
            this.name=name;
            this.houseNumber=houseNumber;
            this.childrenName=childrenName;
        }

        public Human (int weight, String name, int houseNumber, String city)
        {
            this.weight=weight;
            this.name=name;
            this.houseNumber=houseNumber;
            this.city=city;
        }

        public Human (String name)
        {
            this.name=name;
        }

        public Human (String name, int houseNumber, String childrenName)
        {
            this.name=name;
            this.houseNumber=houseNumber;
            this.childrenName=childrenName;
        }

        public Human (String name, int houseNumber)
        {
            this.name=name;
            this.houseNumber=houseNumber;
        }

        public Human (String childrenName, String city )
        {
            this.childrenName=childrenName;
            this.city=city;
        }

        public Human (int age, String city )
        {
            this.age=age;
            this.city=city;
        }

        public Human (int age, int weight)
        {
            this.age=age;
            this.weight=weight;
        }
    }
}
