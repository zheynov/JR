package com.javarush.test.level19.lesson03.task03;

/**
 * Created by ZheynovVV on 14.12.2015.
 */
public class temp
{
    public static void main(String args[])
    {

        String s = "501234567";

        while (s.length() < 10)
        {
            s = "0" + s;
        }

        String phone = "+" + String.valueOf(38) + "(" + s.substring(0, 3) + ")" + s.substring(3, 6) + "-" + s.substring(6, 8) + "-" + s.substring(8, s.length());

        System.out.println(phone);

    }
}
