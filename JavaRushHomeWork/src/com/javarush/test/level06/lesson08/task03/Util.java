package com.javarush.test.level06.lesson08.task03;

/* Класс Util
Реализовать статический метод double getDistance(x1, y1, x2, y2). Он должен вычислять расстояние между точками.
Используй метод double Math.sqrt(double a), который вычисляет квадратный корень переданного параметра
*/

public class Util


{
    public static double getDistance(int x1, int y1, int x2, int y2)
    {
        double x = ((x2 - x1) * (x2 - x1)); // Math.pow((x2 - x1),2)) 2 - вторая степень
        double y = ((y2 - y1) * (y2 - y1));
        double z = x + y;  // (Math.pow((x2 - x1),2))+(Math.pow((y2-y1),2))

        double s = Math.sqrt(z);  // можно проще: Math.sqrt( (Math.pow((x2 - x1),2))+(Math.pow((y2-y1),2)) );

        return s;

    }


}
