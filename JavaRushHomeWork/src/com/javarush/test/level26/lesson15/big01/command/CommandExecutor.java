package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor
{
    private CommandExecutor() {}

    private static Map<Operation, Command> opsAndComs;

    static
    {
        opsAndComs = new HashMap<>();
        opsAndComs.put(Operation.DEPOSIT, new DepositCommand());
        opsAndComs.put(Operation.EXIT, new ExitCommand());
        opsAndComs.put(Operation.INFO, new InfoCommand());
        opsAndComs.put(Operation.WITHDRAW, new WithdrawCommand());
        opsAndComs.put(Operation.LOGIN, new LoginCommand());
    }


    public static final void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException
    {
        opsAndComs.get(operation).execute();
    }

}
