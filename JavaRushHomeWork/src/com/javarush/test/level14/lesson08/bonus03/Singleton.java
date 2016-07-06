package com.javarush.test.level14.lesson08.bonus03;


public class Singleton
{
    static Singleton singleton = new Singleton();

  public static Singleton getInstance()
    {
        return singleton;
    }

    private Singleton()
    {
    }
}