package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{



   public static void main(String[] args) throws Exception
    {
        removeTheFirstNameDuplicates(createMap());
        System.out.print(createMap());

    }

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

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while(iterator.hasNext())
        {
            String  s = iterator.next().getValue();
            removeItemFromMapByValue(createMap(), s);
    } }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
