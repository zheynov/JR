package com.javarush.test.level14.lesson08.bonus02;
import java.io.*;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nod=0;

        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        if ((a==b)&&(a!=0)&&(b!=0))
        {
            nod = a;  System.out.println("\n"+ "Нод равен: " +nod);
        }

        else if ((a==0)||(b==0))
        {
            System.out.println("\n"+ "На ноль делить нельзя!");
        }

        else if ((a!=0)&&(b!=0))
        {
            nod = gcd(a, b);
            System.out.println("\n"+ "Нод равен: " +nod);
        }

    }

/*    public static int gcd(int c, int d)
    {
        while (c != 0 && d != 0) {
            if (c > d) c = c % d;
            else d = d % c;
        }
        return c + d;
    }*/

    public static int gcd(int m, int n)
        {

    while ((m!=n))
    {
        if (m>n) m=m-n;
            else  n=n-m;
    }
            return m;

        }
}


