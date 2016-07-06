package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution
{
    public static void main(String... args) throws TooShortStringException
    {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string)
    {
        if (string == null)
        {
            throw new TooShortStringException();
        }

        int count = 0;

        int lastindex = 0;

        int lastindex2 = 0;

        char[] mass = string.toCharArray();

        for (int i = 0; i < mass.length; i++)
        {
            if (mass[i] == ' ')
                count++;

            if (mass[i] == ' ' && count == 5) lastindex2 = i;

            if (mass[i] == ' ' && count == 4)
            {
                lastindex = i;
            }
        }

        if (count < 4 || string.isEmpty())

        {
            throw new TooShortStringException();
        }

        String lastword = "";

        if (count > 4)
            lastword = string.substring(lastindex + 1, lastindex2);

        return string.substring(string.indexOf(" ") + 1, lastindex + 1) + lastword;
    }

    public static class TooShortStringException extends RuntimeException
    {
    }
}

