package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ZheynovVV on 19.10.2015.
 */
public class UkrainianHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 55;
    }

    String getDescription()
    {
        int N = getCountOfEggsPerMonth();
        String Sssss = Country.UKRAINE;

       return super.getDescription() + " Моя страна - " + Sssss + ". Я несу "+N  + " яиц в месяц.";
    }
}