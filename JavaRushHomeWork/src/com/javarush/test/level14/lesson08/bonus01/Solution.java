package com.javarush.test.level14.lesson08.bonus01;


import java.util.*;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception


        try
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            exceptions.add(e);
        }

        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new FormatterClosedException();

        } catch (FormatterClosedException f)
        {
            exceptions.add(f);
        }

        try
            {
                throw new NoSuchFieldException("ss");
            }
            catch (NoSuchFieldException g)
            {
                exceptions.add(g);
            }

        try
        {
            throw new RuntimeException();
        }
        catch (RuntimeException a)
        {
            exceptions.add(a);
        }

        try
        {
            throw new StringIndexOutOfBoundsException();
        }
        catch (StringIndexOutOfBoundsException t)
        {
            exceptions.add(t);
        }

       //7

        try
        {
            throw new MissingFormatArgumentException("ff");
        }
        catch (MissingFormatArgumentException u)
        {
            exceptions.add(u);
        }

        try
    {
        throw new DuplicateFormatFlagsException("ff");
    }
    catch (DuplicateFormatFlagsException y)
    {
        exceptions.add(y);
    }

        try
    {

        throw new NoSuchElementException("g");
    }
    catch (NoSuchElementException z)
    {
        exceptions.add(z);
    }

        try
        {

            throw new EmptyStackException();
        }
        catch (EmptyStackException x)
        {
            exceptions.add(x);
        }

    }
}
