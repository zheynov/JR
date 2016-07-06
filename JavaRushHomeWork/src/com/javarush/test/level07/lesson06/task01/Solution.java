package com.javarush.test.level07.lesson06.task01;

import java.util.ArrayList;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/
public class Solution
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> list = new ArrayList<String>();

        list.add("1111111");
        list.add("2222222");
        list.add("3333333");
        list.add("4444444");
        list.add("5555555");

        System.out.println(list.size());

        for (int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
        }

    }
}
