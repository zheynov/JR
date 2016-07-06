package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper
{
    public static String generateRandomString() // генерирует случайную строку
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public static void printMessage(String message)
    {
        System.out.println(message);
    }
}
