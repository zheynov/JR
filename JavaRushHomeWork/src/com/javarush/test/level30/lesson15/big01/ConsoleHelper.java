package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Вспомогательный класс, для чтения или записи в консоль
 */

public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString()
    {
        String result = "";
        while (true)
        {
            try
            {
                result = reader.readLine();
                break;
            }
            catch (IOException e)
            {
                writeMessage("Error during entering a text message. Try again.");
            }
        }
        return result;
    }

    public static int readInt()
    {
        int result = 0;
        while (true)
        {
            try
            {
                result = Integer.parseInt(readString());
                break;
            }
            catch (NumberFormatException e)
            {
                writeMessage("Error during entering a number. Try again.");
            }
        }
        return result;
    }
}
