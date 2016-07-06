package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution
{

    public final static class Constants
    {
        public final static String s1 = "Server is not accessible for now.";
        public static final String s2 = "User is not authorized.";
        public final static String s3 = "User is banned.";
        public final static String s4 = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception
    {
        public ServerNotAccessibleException()
        {
            super(Constants.s1);
        }

        public ServerNotAccessibleException(Throwable cause)
        {
            super(Constants.s1, cause);
        }
    }

    public class UnauthorizedUserException extends Exception
    {
        public UnauthorizedUserException()
        {
            super(Constants.s2);
        }

        public UnauthorizedUserException(Throwable cause)
        {
            super(Constants.s2, cause);
        }
    }

    public class BannedUserException extends Exception
    {
        public BannedUserException()
        {
            super(Constants.s3);
        }

        public BannedUserException(Throwable cause)
        {
            super(Constants.s3, cause);
        }
    }

    public class RestrictionException extends Exception
    {
        public RestrictionException()
        {
            super(Constants.s4);
        }

        public RestrictionException(Throwable cause)
        {
            super(Constants.s4, cause);
        }
    }
}
