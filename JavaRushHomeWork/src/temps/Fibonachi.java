package temps;

/*      Ряд Фибоначчи.
        Написать код метода, в аргументах которому дают первые два элемента ряда и номер любого элемента.
        Метод возвращает этот элемент.       */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonachi
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите 1-й элемент ряда: ");
        long first = Integer.parseInt(reader.readLine());

        System.out.println("Введите 2-й элемент ряда: ");
        long second = Integer.parseInt(reader.readLine());

        System.out.println("Введите номер элемента, который требуется вычислить: ");
        int nomer = Integer.parseInt(reader.readLine());

        long l = fbNch(first, second, nomer);
        System.out.println("\n"+ nomer +"-й элемент равен " +l);
    }

    static long fbNch(long a, long b, int index)
    {
        long result;

        long[] resultMas = new long[index];
        resultMas[0]=a;
        resultMas[1]=b;

        for (int i=2; i<resultMas.length; i++)
        {
            resultMas[i] = resultMas[i-2] + resultMas[i-1];
        }

        return  result = resultMas[index-1];
    }
}
