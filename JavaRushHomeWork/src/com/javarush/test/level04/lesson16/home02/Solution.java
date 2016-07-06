package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {

        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

        String s1 = reader.readLine();
        String s2 = reader.readLine();
        String s3 = reader.readLine();

        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int c = Integer.parseInt(s3);

        if ((a>c)&&(a>b))
              {if (b>c)
                  System.out.println(s2);
                  else
                  System.out.println(s3);
               }
        else if ((b>c)&&(a<b))
               {if (a>c)
                   System.out.println(s1);
               else
                   System.out.println(s3);
               }
        else if ((b<c)&&(a<c))
        {if (a>b)
            System.out.println(s1);
        else
            System.out.println(s2);
        }


    }
}
