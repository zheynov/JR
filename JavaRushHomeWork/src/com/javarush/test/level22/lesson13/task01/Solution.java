package com.javarush.test.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution
{
    public static void main(String[] args)
    {
        String[] s = getTokens("level22.lesson13.task01", ".");

        for (String sss : s)
        {
            System.out.println(sss);
        }
    }

    public static String[] getTokens(String query, String delimiter)
    {
        if (query==null) {return new String[0];}
        if (delimiter==null) {return new String[0];}

            StringTokenizer tokenizer = new StringTokenizer(query, delimiter);

        ArrayList<String> list = new ArrayList<>();

        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            list.add(token);
        }

        String[] s = new String[list.size()];

        for (int i = 0; i < list.size(); i++)
        {
            s[i] = list.get(i);
        }

        return s;
    }
}
