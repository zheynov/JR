package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution
{
    public static void main(String... args)
    {
        Long t0 = System.currentTimeMillis();
        int[] bla = getNumbers(100_000_000);
        Long t1 = System.currentTimeMillis();
        System.out.println("time: " + (t1 - t0) / 1000d + " sec");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");

        for (int i = 0; i < bla.length; i++)
        {
            System.out.print(bla[i] + " ");
        }
    }

    public static int[] getNumbers(int N)
    {
        ArrayList<Integer> finalList = new ArrayList<>();

        for (int i = 1; i <= 9; i++)
        {
            finalList.add(i);
        }

        for (int i = 10; i < N; i++)
        {
            byte stepen = (byte) ((byte) Math.log10(i) + 1);
            byte[] temp = new byte[stepen];

            int o = i;
            int m = 0;
            while (i > 0)
            {
                int dig = i % 10;
                i = i / 10;
                temp[m] = (byte) dig;
                m++;
            }
            i = o;

            int count = 0;

            for (byte t = 0; t < temp.length - 1; t++)
            {
                if (temp[t] > temp[t + 1])
                    count++;
            }

            if (count != 0)
            {
                int digit = 0, chislo = 0, element = 0;

                int l = i;
                while (i > 0)
                {
                    digit = i % 10;
                    i = i / 10;
                    element = 1;
                    for (int k = 1; k <= stepen; k++)
                    {
                        element *= digit;
                    }
                    chislo = chislo + element;
                }
                i = l;

                if (i == chislo)
                {
                    finalList.add(i);
                }
            }
        }

        int[] result = new int[finalList.size()];

        for (byte i = 0; i < finalList.size(); i++)
        {
            result[i] = finalList.get(i);
        }
        return result;
    }
}
