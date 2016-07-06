package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Игровой процесс можно представить как взаимодействие игровых объектов GameObject
 */

public abstract class GameObject
{
    private int x, y, width, height; /* Позиция и размер объекта для отрисовки.
    Все игровые объекты будут занимать одну ячейку игрового поля. Именно этот размер будет участвовать в расчёте движения и столкновений объектов.
    Размер, который будет храниться внутри объекта, будет использоваться только при его отрисовке.  */


    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
        height = Model.FIELD_SELL_SIZE;
        width = Model.FIELD_SELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics graphics); // Этот метод будет реализован в каждом типе игровых объектов по-своему


    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
