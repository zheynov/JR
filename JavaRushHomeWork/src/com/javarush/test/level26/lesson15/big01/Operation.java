package com.javarush.test.level26.lesson15.big01;

public enum Operation
{
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i)
    {
        Operation code;

        if (i == 0)
            throw new IllegalArgumentException();
        else if (i == 1)
            code = INFO;
        else if (i == 2)
            code = DEPOSIT;
        else if (i == 3)
            code = WITHDRAW;
        else if (i == 4)
            code = EXIT;

        else throw new IllegalArgumentException();

        return code;
    }
}
