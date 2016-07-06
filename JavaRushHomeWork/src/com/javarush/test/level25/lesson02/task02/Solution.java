package com.javarush.test.level25.lesson02.task02;

import java.util.*;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution
{

    public static enum Wheel
    {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car
    {
        protected List<Wheel> wheels;

        public Car()
        {
            //init wheels here

            wheels = new ArrayList<>();
            for (int i = 0; i < loadWheelNamesFromDB().length; i++)
            {
                if(Wheel.values().length ==loadWheelNamesFromDB().length &&
                        !loadWheelNamesFromDB()[i].isEmpty() && loadWheelNamesFromDB()[i] != null)

                    wheels.add(Wheel.valueOf(loadWheelNamesFromDB()[i]));
            }

        }

        protected String[] loadWheelNamesFromDB()
        {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
