package com.javarush.test.level22.lesson09.task02;

import java.util.*;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution
{
    public static void main(String... args)
    {
        Map<String,String> params2 = new LinkedHashMap<>();
        params2.put("country", "Ukraine");
        params2.put(null, null);
        params2.put("1", "one");
        params2.put("2", "two");
        params2.put("3", "three");
        params2.put("4", "four");
        params2.put("5", "five");
        params2.put("6", "six");
        params2.put("7", "seven");
        params2.put("8", "eight");
        params2.put("9", "nine");
        params2.put("10", "ten");
        params2.put("11", "eleven");
        params2.put("12", "twelve");
        params2.put(null, null);
        params2.put("height", "185");

        System.out.println(getCondition(params2).toString());

    }

    public static StringBuilder getCondition(Map<String, String> params)
    {
        StringBuilder builder = new StringBuilder();

        if (params == null || params.isEmpty()) return new StringBuilder();

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, String> s = iterator.next();

            if (s.getValue() != null)
            {
                builder.append(s.getKey());
                builder.append(" = ");
                builder.append("\'" + s.getValue() + "\'");
                builder.append(" and ");
            }
        }

        builder.delete(builder.toString().length() - 5, builder.toString().length());

        return builder;
    }
}
