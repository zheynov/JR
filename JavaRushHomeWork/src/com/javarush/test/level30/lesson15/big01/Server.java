package com.javarush.test.level30.lesson15.big01;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Основной класс сервера
 */

public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();  //  ключ - имя клиента, значение - соединение с ним

    public static void sendBroadcastMessage(Message message)
    {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
        {
            try
            {
                entry.getValue().send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Message hasn't been sent");
            }
        }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            String userName = null;
            try (Connection connection = new Connection(socket))
            {
                ConsoleHelper.writeMessage("New connection established with address " + connection.getRemoteSocketAddress());
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }

            catch (ClassNotFoundException | IOException e)
            {
                ConsoleHelper.writeMessage("Communication error ocures with address " + socket.getRemoteSocketAddress());
            }

            finally
            {
                if (userName != null && !userName.isEmpty())
                {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Connection with" + socket.getRemoteSocketAddress() + " has been closed");
                try
                {
                    socket.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String userName;

            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();

                if (answer.getType() == MessageType.USER_NAME)
                {
                    userName = answer.getData();

                    if (userName != null && !userName.isEmpty() && !connectionMap.containsKey(userName))
                    {
                        connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));

                        return userName;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (String mapUserName : connectionMap.keySet())
            {
                Message message = new Message(MessageType.USER_ADDED, mapUserName);

                if (!mapUserName.equals(userName))
                {
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT)
                {
                    String messageToSend = userName + ": " + receivedMessage.getData();
                    Message newMessage = new Message(MessageType.TEXT, messageToSend);
                    Server.sendBroadcastMessage(newMessage);

                } else
                {
                    ConsoleHelper.writeMessage("Message is not a text");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        ConsoleHelper.writeMessage("Enter a port number");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Server has started");

            Socket socket;

            while (true)
            {
                socket = serverSocket.accept();
                new Handler(socket).start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            serverSocket.close();
        }
    }
}
