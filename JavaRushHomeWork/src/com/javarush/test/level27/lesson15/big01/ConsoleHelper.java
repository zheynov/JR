package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Choose a dish below:");
        writeMessage(Dish.allDishesToString());

        ArrayList<Dish> list = new ArrayList<>();
        Collections.addAll(list, Dish.values());



        while (true)
        {
            String s="";

            try
            {
                s = readString();

                if (s.toLowerCase().equals("exit"))
                {
                    break;
                }

                dishes.add(Dish.valueOf(s));
            }

            catch (IllegalArgumentException e)
            {
                writeMessage(s + " is not detected");
            }
        }
        return dishes;
    }
}
