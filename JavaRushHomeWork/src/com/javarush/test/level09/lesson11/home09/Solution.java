package com.javarush.test.level09.lesson11.home09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        Map<String, Cat> map = new HashMap<String, Cat>();
        map.put("Котан1", new Cat("Кот1"));
        map.put("Котан2", new Cat("Кот2"));
        map.put("Котан3", new Cat("Кот3"));
        map.put("Котан4", new Cat("Кот4"));
        map.put("Котан5", new Cat("Кот5"));
        map.put("Котан6", new Cat("Кот6"));
        map.put("Котан7", new Cat("Кот7"));
        map.put("Котан8", new Cat("Кот8"));
        map.put("Котан9", new Cat("Кот9"));
        map.put("Котан10", new Cat("Кот10"));
        return  map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        Set<Cat> catsName = new HashSet<Cat>();

        Iterator<Map.Entry<String, Cat>> iterator = map.entrySet().iterator();

        while(iterator.hasNext())
        {
            catsName.add(iterator.next().getValue());
        }

        return catsName;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
