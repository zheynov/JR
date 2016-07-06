package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution
{
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null || string.isEmpty())
        {
            throw new TooShortStringException();
        }

        int countTabs = 0, index = 0, index2 = 0;

        char[] mass = string.toCharArray();
        for (int i = 0; i < mass.length; i++)
        {
            if (mass[i] == '\t')
                countTabs++;

            if (mass[i] == '\t' && countTabs == 1)
            {
                index = i;
            }

            if (mass[i] == '\t' && countTabs == 2)
            {
                index2 = i;
            }
        }


        if (countTabs < 2)
        {
            throw new TooShortStringException();
        }


        return string.substring(index + 1, index2);
    }

    public static class TooShortStringException extends Exception
    {
    }

    public static void main(String[] args) throws TooShortStringException
    {
       // System.out.println(getPartOfString("tab0\ttab\ttab1\t"));       //tab
        System.out.println(getPartOfString("\t\t"));                    //
       // System.out.println(getPartOfString("123\t123"));                //Exception
       // System.out.println(getPartOfString(null));                      //Exception
    }
}
