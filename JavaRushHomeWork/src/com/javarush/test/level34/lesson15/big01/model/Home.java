package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Отвечает за места на игровом поле (дома) в которые нужно сдвинуть все ящики
 * Объекты этого типа не должны передвигаться по полю или сталкиваться с другими игровыми объектами.
 */

public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y, 2, 2);
    }


    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(255, 0, 0));
        graphics.drawOval(getX() + 10, getY() + 10, getWidth(), getHeight());
    }
}
