package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);

        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        String summa = "";
        int sum;
        while (true)
        {
            summa = ConsoleHelper.readString();
            try
            {
                sum = Integer.parseInt(summa);

                if (!manipulator.isAmountAvailable(sum) || sum <= 0)
                {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));

                } else
                {
                    try
                    {
                        for (Map.Entry<Integer, Integer> element : manipulator.withdrawAmount(sum).entrySet())
                        {
                            ConsoleHelper.writeMessage("\t" + String.valueOf(element.getKey()) + " - " + String.valueOf(element.getValue()));
                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currency));

                        break;
                    }
                    catch (NotEnoughMoneyException e)
                    {
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                }
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
    }
}
