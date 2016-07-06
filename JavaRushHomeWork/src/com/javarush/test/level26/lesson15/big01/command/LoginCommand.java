package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    private ArrayList<String> cardNumbers = new ArrayList<>();
    private ArrayList<String> pins = new ArrayList<>();

    {
        Enumeration<String> keys = validCreditCards.getKeys();
        while (keys.hasMoreElements())
        {
            String sss = keys.nextElement();
            cardNumbers.add(sss);
            pins.add(validCreditCards.getString(sss));
        }
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        while (true)
        {
            String cn = ConsoleHelper.readString();

            while (cn.length() != 12)
            {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cn));
                cn = ConsoleHelper.readString();
            }

            String pn = ConsoleHelper.readString();

            while (pn.length() != 4)
            {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                pn = ConsoleHelper.readString();
            }

            if (pn.length() == 4 && cn.length() == 12)
            {
                if (!pins.contains(pn) || !cardNumbers.contains(cn))
                {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

                } else

                {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cn));
                    break;
                }
            }
        }
    }
}
