package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
Используйте коллекции.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();

        String date = month + "1 2000";

        Date result = new Date(date);

        int gg = result.getMonth() + 1; //так как номера месяцев начинаются с нуля

        System.out.print(month + " is "+gg+ " month");



/*      List<String> list = new ArrayList<String>();

        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");

        if (s.equals(list.get(0))) System.out.println(s +" is 1 month");

         else if  (s.equals(list.get(1))) System.out.println(s +" is 2 month");
          else if  (s.equals(list.get(2))) System.out.println(s +" is 3 month");
           else if  (s.equals(list.get(3))) System.out.println(s +" is 4 month");
            else if  (s.equals(list.get(4))) System.out.println(s +" is 5 month");
             else if  (s.equals(list.get(5))) System.out.println(s +" is 6 month");
              else if  (s.equals(list.get(6))) System.out.println(s +" is 7 month");
               else if  (s.equals(list.get(7))) System.out.println(s +" is 8 month");
                else if  (s.equals(list.get(8))) System.out.println(s +" is 9 month");
                 else if  (s.equals(list.get(9))) System.out.println(s +" is 10 month");
                  else if  (s.equals(list.get(10))) System.out.println(s +" is 11 month");
                   else if  (s.equals(list.get(11))) System.out.println(s +" is 12 month");*/

    }

}