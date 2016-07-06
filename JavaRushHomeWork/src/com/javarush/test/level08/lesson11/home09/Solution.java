package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        String date = "JANUARY 1 2000";
        System.out.println(date + " = " + isDateOdd(date));
    }

    public static boolean isDateOdd(String date)
    {
        Date newYear = new Date();
        newYear.setMonth(0);
        newYear.setDate(0);

        Date nowDate = new Date(date);

        long timeDistance = nowDate.getTime() - newYear.getTime();

        long msDay = 24 * 60 * 60 * 1000;
        int dayCount = (int) (timeDistance / msDay);

        if (dayCount % 2 != 0)
            return true;
        else
            return false;
    }
}


/*
public class Solution
{
    public static void main(String[] args)
    {
        String date = "JANUARY 2 2020";
        System.out.println(date +" = " + isDateOdd(date));
    }
    public static boolean isDateOdd(String date)
    {
        Date yearStartTime = new Date(date); //сюда запихиваем дату для обработки

        Date currentTime = new Date(); // сегодня
        currentTime.setMonth(0); // первый месяц года
        currentTime.setDate(0);

        long msTimeDistance  = currentTime .getTime() - yearStartTime.getTime(); //разница в мс между сегодня и какой-то датой
        long msDay = 24 * 60 * 60 * 1000;

        int dayCount = (int) (msTimeDistance /msDay); // разница в сутках

        if (dayCount%3==0) // условие задачи
            return true;
        else
            return false;
    }
}
*/

