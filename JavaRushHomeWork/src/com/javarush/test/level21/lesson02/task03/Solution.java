package com.javarush.test.level21.lesson02.task03;

/* Все гениальное - просто!
Упростить. Переменные не переименовывать, комментарии не оставлять.
*/
public class Solution {

/*    public static void main (String... args)
    {

        boolean y = calculate(true, true, false, false);
        System.out.println(y);
    }*/


    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return c;
    }
}

/*
было:

public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
}
*/
