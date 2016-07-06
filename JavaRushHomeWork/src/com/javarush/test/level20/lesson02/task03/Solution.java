package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution
{
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String... args) throws Exception
    {
        new Solution().fillInPropertiesMap();

        for (Map.Entry<String, String> pair : properties.entrySet())
        {
            String key = pair.getKey();
            String value = String.valueOf(pair.getValue());
            System.out.println(key + " " + value);
        }
    }


    public void fillInPropertiesMap() throws Exception
    {
        //implement this method - реализуйте этот метод

        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        String fileName = file.readLine();
        file.close();

        InputStream inStream = new FileInputStream(fileName);

        new Solution().load(inStream);
        inStream.close();
    }

    public void save(OutputStream outputStream) throws Exception
    {
        //implement this method - реализуйте этот метод

        Properties prop = new Properties();

        if (properties.size() > 0)
        {
            for (Map.Entry<String, String> element : properties.entrySet())
            {
                String key = element.getKey();
                String value = element.getValue();
                prop.setProperty(key, value);
            }

            prop.store(outputStream, null);
            outputStream.close();
        }
    }

    public void load(InputStream inputStream) throws Exception
    {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);

        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements())
        {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            properties.put(key, value);
        }

        inputStream.close();
    }
}
