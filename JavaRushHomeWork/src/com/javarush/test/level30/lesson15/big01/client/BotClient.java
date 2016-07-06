package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client
{
    public static void main(String[] args)
    {
        new BotClient().run();
    }


    public class BotSocketThread extends SocketThread
    {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);

            String userName;
            String messageText;
            String[] elements = message.split(": ");

            if (elements.length > 1 && message.contains(":"))
            {
                userName = elements[0];
                messageText = elements[1];


                if (messageText.toLowerCase().equals("дата"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.YYYY");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("день"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("месяц"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("год"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("время"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("час"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("H");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("минуты"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("m");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }

                if (messageText.toLowerCase().equals("секунды"))
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("s");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    sendTextMessage("Информация для " + userName + ": " + date);
                }
            }
        }
    }


    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        int randDigit = (int) (Math.random() * 99);

        return "date_bot_" + randDigit;
    }
}
