package com.javarush.test.level22.lesson13.task03;

import java.util.StringTokenizer;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution
{
    public static void main(String... args)
    {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("0501234565"));
        System.out.println(checkTelNumber("(050)1234565"));
        System.out.println(checkTelNumber("-1034567890"));
        System.out.println();
        System.out.println(checkTelNumber("-1034567890-"));
        System.out.println(checkTelNumber("-1034567890+"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber("05-01(234)567"));
        System.out.println(checkTelNumber("050123-4567c"));
        System.out.println(checkTelNumber("05--01234565"));
        System.out.println(checkTelNumber("+38-(050)1234567"));
        System.out.println(checkTelNumber("+38((050)1234567"));
        System.out.println(checkTelNumber("+5(0--5)1234567"));
        System.out.println(checkTelNumber("7-4-4123689-5"));
    }

    public static boolean checkTelNumber(String telNumber)
    {
        boolean finalvalue = true;

        if (telNumber.substring(0, 1).equals("+") && telNumber.length() > 12 || telNumber.length() < 17)
            finalvalue = true;

        if (telNumber.isEmpty())
        {
            finalvalue = false;
        }

        String buf1 = telNumber.replaceAll("\\w", "");
        if (buf1.length() > 5) finalvalue = false;


        String buf2 = telNumber.replaceAll("\\W", ""); // остаются только цифры

        if (buf2.length() < 10) finalvalue = false;
        if (telNumber.charAt(0) == '+' && buf2.length() != 12) finalvalue = false;
        if (telNumber.charAt(0) == '(' && buf2.length() != 10) finalvalue = false;

        if (telNumber.charAt(0) != '-' && telNumber.charAt(0) != '+' && telNumber.charAt(0) != '(' && buf2.length() != 10) finalvalue = false;

        if (telNumber.charAt(0) == '-' && buf2.length() > 13) finalvalue = false;


        if (telNumber.charAt(telNumber.length()-1)== '-' || telNumber.charAt(telNumber.length()-1)== '+'
                || telNumber.charAt(telNumber.length()-1)== '(' || telNumber.charAt(telNumber.length()-1)== ')' )  finalvalue = false;



        char[] bufMass = telNumber.toCharArray();
        int countTire = 0;
        int countBracesOpened = 0;
        int countBracesClosed = 0;
        int countPluses = 0;

        for (char chr : bufMass)
        {
            if (chr == '-') countTire++;
            if (chr == '(') countBracesOpened++;
            if (chr == ')') countBracesClosed++;
            if (chr == '+') countPluses++;
        }

        if (countTire > 2) finalvalue = false;
        if (countBracesOpened > 1) finalvalue = false;
        if (countBracesClosed > 1) finalvalue = false;
        if (countPluses > 1) finalvalue = false;

        if ((countBracesClosed == 1 && countBracesOpened == 0) ||
                (countBracesClosed == 0 && countBracesOpened == 1)) finalvalue = false;


        StringTokenizer tokenizer = new StringTokenizer(telNumber, "+()-1234567890");
        if (tokenizer.hasMoreTokens()) finalvalue = false;

        if (telNumber.replaceAll("\\w", "").contains("-(")) finalvalue = false;
        if (telNumber.replaceAll("\\w", "").contains("(-)")) finalvalue = false;

        if ((telNumber.indexOf(')') > telNumber.indexOf('(')) && (telNumber.contains("(") && telNumber.contains(")"))
                && telNumber.substring(telNumber.indexOf("("), telNumber.indexOf(")")).length() != 4)
            finalvalue = false;


        if ((telNumber.contains("(") && telNumber.contains(")")) && (telNumber.indexOf(')') < telNumber.indexOf('(')))
            finalvalue = false;

        if (telNumber.contains("-") && countTire > 1 && telNumber.substring(telNumber.indexOf("-") + 1, telNumber.lastIndexOf("-")).length() < 1)
            finalvalue = false;


        return finalvalue;


        /*        return ((telNumber.matches("^\\+[\\(\\-]?(\\d[\\(\\)\\-]?){11}\\d$") || telNumber.matches("^\\(?(\\d[\\-\\(\\)]?){9}\\d$"))
                && telNumber.matches("[\\+]?\\d*(\\(\\d{3}\\))?\\d*\\-?\\d*\\-?\\d*\\d$"));*/
    }
}
