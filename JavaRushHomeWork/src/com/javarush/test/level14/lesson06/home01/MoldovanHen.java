package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ZheynovVV on 19.10.2015.
 */
public class MoldovanHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 11;
    }

    String getDescription()
    {
        int N = getCountOfEggsPerMonth();
        String Sssss = Country.MOLDOVA;

        return super.getDescription() + " Моя страна - " + Sssss + ". Я несу " + N + " яиц в месяц.";
    }
}



