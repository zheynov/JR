package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream stream = new PrintStream(outputStream);

        System.setOut(stream);

        testString.printSomething();

        String result = outputStream.toString();

        long n1=0;
        long n2=0;

        if (result.contains("+"))
        {
            String s1 = (result.substring(0, result.indexOf("+")));
             n1 = Integer.parseInt(s1.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));
            String s2 = result.substring(result.indexOf("+") + 1, result.length());
             n2 = Integer.parseInt(s2.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));

            long res = n1+n2;
            result = n1+" "+"+"+" "+n2+" "+"="+" "+res;
        }
        else         if (result.contains("-"))
        {
            String s1 = (result.substring(0, result.indexOf("-")));
             n1 = Integer.parseInt(s1.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));
            String s2 = result.substring(result.indexOf("-") + 1, result.length());
             n2 = Integer.parseInt(s2.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));

            long res = n1-n2;
            result = n1+" "+"-"+" "+n2+" "+"="+" "+res;
        }

        else         if (result.contains("*"))
        {
            String s1 = (result.substring(0, result.indexOf("*")));
            n1 = Integer.parseInt(s1.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));
            String s2 = result.substring(result.indexOf("*") + 1, result.length());
            n2 = Integer.parseInt(s2.replaceAll("[a-zA-Z\\s\\-'\\p{Punct}\\p{Z}]", ""));

            long res = n1*n2;
            result = n1+" "+"*"+" "+n2+" "+"="+" "+res;
        }


        System.setOut(consoleStream);

        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("31 * 16 = ");
        }
    }
}

