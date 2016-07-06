package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{

    public static HashMap<String, Date> createMap()
    {
        Map<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Van Damme", new Date("JULY 1 1980"));
        map.put("Иванов", new Date("JULY 1 1982"));
        map.put("Жердев", new Date("AUGUST 1 1980"));
        map.put("Сидоров", new Date("JUNE 2 1980"));
        map.put("Жердяев", new Date("AUGUST 1 1980"));
        map.put("Пересветов", new Date("JANUARY 1 1985"));
        map.put("Петров", new Date("JUNE 1 1980"));
        map.put("Жужень", new Date("SEPTEMBER 1 1980"));
        map.put("Савельев", new Date("AUGUST 1 1980"));

        return (HashMap<String, Date>) map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();


        while(iterator.hasNext())
        {
            int  s = iterator.next().getValue().getMonth();

            if ((s==5)||(s==6)||(s==7))

                iterator.remove();
        }
    }
}
