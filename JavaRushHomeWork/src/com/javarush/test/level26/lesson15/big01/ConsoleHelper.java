package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void printExitMessage()
    {
        writeMessage("Peace!");
    }

    public static String readString() throws InterruptOperationException
    {
        String s = "";

        try
        {
            s = reader.readLine();
            if (s.toLowerCase().equals("exit"))
            {
                ConsoleHelper.writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        }
        catch (IOException ignored)
        {
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));

        String currency;

        while (true)
        {
            currency = readString();

            if (currency.length() != 3)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            } else if (currency.length() == 3)
            {
                break;
            }
        }
        currency = currency.toUpperCase();

        return currency;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] twoDigitsMassive;
        ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (true)
        {
            String s = readString();
            twoDigitsMassive = s.split(" ");
            int denomination = 0;
            int count = 0;

            try
            {
                denomination = Integer.parseInt(twoDigitsMassive[0]);
                count = Integer.parseInt(twoDigitsMassive[1]);
            }

            catch (Exception ignored)
            {
            }

            if (denomination <= 0 || count <= 0 || twoDigitsMassive.length > 2)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            } else if (twoDigitsMassive.length == 2 && denomination > 0 && count > 0)
            {
                break;
            }
        }
        return twoDigitsMassive;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("choose.operation"));

        int choise = 0;

        ConsoleHelper.writeMessage("1 - " + res.getString("operation.INFO"));
        ConsoleHelper.writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage("4 - " + res.getString("operation.EXIT"));

        while (true)
        {
            String s = readString();
            try
            {
                choise = Integer.parseInt(s);

                if (choise <= 0 || choise > 4)
                {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                } else break;
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
        return Operation.getAllowableOperationByOrdinal(choise);
    }
}
