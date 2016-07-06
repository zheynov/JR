package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * класс ящика
 */

public class Box extends CollisionObject implements Movable
{


    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(255, 165, 0));
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());

        graphics.drawLine(getX(), getY(), getX() + getWidth(), getHeight() + getY());
        graphics.drawLine(getX(), getHeight() + getY(), getWidth() + getX(), getY());

    }


    @Override
    public void move(int x, int y)
    {
        setX(this.getX() + x);
        setY(this.getY() + y);
    }
}
