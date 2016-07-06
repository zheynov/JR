package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{

    public static HashMap<String, String> createMap()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Петровский", "Иван");
        map.put("Иванов", "Василий");
        map.put("Жердев", "Гоги");
        map.put("Сидоров", "Руслан");
        map.put("Жердевский", "Игорь");
        map.put("Пересветов", "Челубей");
        map.put("Петров", "Александр");
        map.put("Жужень", "Терентий");
        map.put("Савельев", "Василий");
        map.put("Сидоровский", "Николай");

        return (HashMap<String, String>) map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int count=0;

        while(iterator.hasNext())
        {
            if (iterator.next().getValue().equals(name))
             count++;
        }
        return count;
    }


    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int count=0;

        while(iterator.hasNext())
        {
            if (iterator.next().getKey().equals(familiya))
                count++;
        }
        return count;

    }

    public static void main(String[] args) throws Exception
    {

        System.out.print(createMap());
    }

}
