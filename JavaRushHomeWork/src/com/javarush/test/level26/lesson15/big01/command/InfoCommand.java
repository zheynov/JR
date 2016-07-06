package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        ConsoleHelper.writeMessage(res.getString("before"));

        if (manipulators.isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));
        else
        {
            for (CurrencyManipulator curMan : manipulators)
            {
                if (!curMan.hasMoney() || curMan.getTotalAmount()==0)
                {
                    ConsoleHelper.writeMessage(res.getString("no.money"));
                } else

                    ConsoleHelper.writeMessage(curMan.getCurrencyCode() + " - " + curMan.getTotalAmount());

            }
        }
    }
}

