/*
package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

*/
/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом, каждая строка должна заканчиваться пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*//*


public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String mmRamu = reader.readLine();

        char[] smBls = mmRamu.toCharArray();

        List<Character> smBls1 = new ArrayList<Character>();
        List<Character> smBls2 = new ArrayList<Character>();


        for (int i=0; i<smBls.length; i++)
        {
            if (isVowel(smBls[i]))

            {
                smBls1.add(smBls[i]);
                smBls1.add(' ');
            }

            else

             if (smBls[i]!=' ')
             {
                 smBls2.add(smBls[i]);
                 smBls2.add(' ');
             }
        }


        for (int i=0; i<smBls1.size(); i++)
        {
            System.out.print(smBls1.get(i));
        }

        System.out.println();

        for (int i=0; i<smBls2.size(); i++)
        {
            System.out.print(smBls2.get(i));
        }
    }

    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
*/
