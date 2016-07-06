package com.javarush.test.level38.lesson06.home01;


public class ExcFactory {

   public static Throwable createElement(Enum element) {

        if (element == ExceptionApplicationMessage.UNHANDLED_EXCEPTION)
            return new Exception("U"+"NHANDLED EXCEPTION".toLowerCase());
        else if (element == ExceptionApplicationMessage.SOCKET_IS_CLOSED)
            return new Exception("S"+"OCKET IS CLOSED".toLowerCase());

        else if (element == ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS)
            return new RuntimeException("N"+"OT ENOUGH CONNECTIONS".toLowerCase());
        else if (element == ExceptionDBMessage.RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT)
            return new RuntimeException("R"+"ESULT HAS NOT GOTTEN BECAUSE OF TIMEOUT".toLowerCase());

        else if (element== ExceptionUserMessage.USER_DOES_NOT_EXIST)
            return new Error("U"+"SER DOES NOT EXIST".toLowerCase());
        else if (element== ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS)
            return new Error("U"+"SER DOES NOT HAVE PERMISSIONS".toLowerCase());

        else return new IllegalArgumentException();
    }


/*   ВАРИАНТ с хелпа, покрасивее

 public static Throwable createElement(Enum message) {

        String msg = message.name().charAt(0) + "" + message.name().substring(1).toLowerCase().replace("_", " ");

        if (message == ExceptionApplicationMessage.UNHANDLED_EXCEPTION)
            return new Exception(msg);

        else if (message == ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS)
            return new RuntimeException(msg);

        else if (message == ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS)
            return new Error(msg);

        else return new IllegalArgumentException();
    }*/


}
