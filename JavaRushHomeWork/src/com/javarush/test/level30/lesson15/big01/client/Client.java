package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        new Client().run();
    }

    protected Connection connection;
    private volatile boolean clientConnected = false;    // true, если клиент подсоединен к серверу, false в противном случае


    public class SocketThread extends Thread             // устанавливает сокетное соединение и читает сообщения сервера
    {
        public void run()
        {
            try
            {
                String address = getServerAddress();
                int port = getServerPort();
                Socket socket = new Socket(address, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException | ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + ": joined to chat");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + ": has left the chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            Client.this.clientConnected = clientConnected;

            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();

                if (message.getType() == MessageType.NAME_REQUEST)
                {
                    String userName = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, userName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED)
                {
                    notifyConnectionStatusChanged(true);
                    return;
                } else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT)
                {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED)
                {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED)
                {
                    informAboutDeletingNewUser(message.getData());
                } else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
    }


    public void run()
    {
        SocketThread additionalThread = getSocketThread();
        additionalThread.setDaemon(true);
        additionalThread.start();

        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Error during waiting");
        }

        if (clientConnected)
        {
            ConsoleHelper.writeMessage("Connection established. Enter 'exit' command if you want to exit");
        } else
        {
            ConsoleHelper.writeMessage("Error during the client's work");
        }
        while (clientConnected)
        {
            String s = ConsoleHelper.readString();

            if ((s.toLowerCase()).equals("exit"))
            {
                break;
            }
            if (shouldSentTextFromConsole())
            {
                sendTextMessage(s);
            }
        }
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Enter server address");
        String address;

        while (true)
        {
            address = ConsoleHelper.readString();
            if (address.matches("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)") || address.equals("localhost"))
            {
                return address;
            } else ConsoleHelper.writeMessage("Incorrect address format. Try again");
        }
    }

    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Enter port number");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Enter userName");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        Message textMessage = new Message(MessageType.TEXT, text);

        try
        {
            connection.send(textMessage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            clientConnected = false;
        }
    }
}
