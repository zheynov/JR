package com.javarush.test.level05.lesson09.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя конструкторами:
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    public String name;
    public int age;
    public String gender;

public   Friend (String name)
{
this.name=name;
}

    public   Friend (String name, int age)
    {
        this.name=name;
        this.age=age;
    }

    public   Friend (String name, int age, String gender)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
}