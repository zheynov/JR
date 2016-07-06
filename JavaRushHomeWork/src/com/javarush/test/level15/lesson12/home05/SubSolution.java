package com.javarush.test.level15.lesson12.home05;


public class SubSolution extends Solution
{
    private SubSolution(char a) {
        super(a);
    }
    private SubSolution(char c, char b) {
        super(c, b);
    }
    private SubSolution(char c, char d, char e) {
        super(c,d,e);
    }


     SubSolution(float a, float b, int c)
    {
        super(a, b, c);
    }

     SubSolution(int a)
    {
        super(a);
    }

     SubSolution(long b)
    {
        super(b);
    }

    protected SubSolution(float a)
    {
        super(a);
    }

    protected SubSolution(float a, float b)
    {
        super(a, b);
    }

    protected SubSolution(float a, float b, float c)
    {
        super(a, b, c);
    }

    public SubSolution(String s)
    {
        super(s);
    }

    public SubSolution(String s, int a)
    {
        super(s, a);
    }

    public SubSolution(String s, float b)
    {
        super(s, b);
    }
}
