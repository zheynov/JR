package com.javarush.test.level04.lesson06.task04;

/* Сравнить имена
Ввести с клавиатуры два имени, и если имена одинаковые вывести сообщение «Имена идентичны». Если имена разные, но их длины равны – вывести сообщение – «Длины имен равны».
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

        String s1 = reader.readLine();
        String s2 = reader.readLine();

        String Str1 = new String(s1); // создаём экземпляр класса String чтобы воспользоваться методом length
        String Str2 = new String(s2); // метод length возвращает длину строки

        if (s1.equals(s2)) // Для сравнения объектов по их внутреннему содержанию используют метод equals.
            System.out.println("Имена идентичны");

        else

        if (Str1.length()==Str2.length())
        {  System.out.println("Длины имен равны"); }

        else
        {  System.out.println("Длины имен разные"); }

    }
}
